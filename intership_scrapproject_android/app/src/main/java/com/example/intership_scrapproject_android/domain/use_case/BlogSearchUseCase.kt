package com.example.intership_scrapproject_android.domain.use_case

import android.database.sqlite.SQLiteException
import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH
import androidx.annotation.RequiresApi
import androidx.paging.PagingData
import androidx.paging.map
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.data.local.Resource
import com.example.intership_scrapproject_android.data.local.Status
import com.example.intership_scrapproject_android.data.remote.BlogSearchRepository
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BlogSearchUseCase(
    private val blogSearchRepository: BlogSearchRepository,
    private val postRepository : PostRepository
) {
    @RequiresApi(Build.VERSION_CODES.N)
    operator fun invoke(
        query : String
    ) : Resource<Flow<PagingData<BlogSearchItem>>> {

        var error = false
        var errorMessage = ""

        val result = blogSearchRepository.getBlogSearchResult(query).map { pagingData ->
            pagingData.map { blogSearchItem ->
                blogSearchItem.postdate = postDateFormat(blogSearchItem.postdate)
                blogSearchItem.title = htmlToString(blogSearchItem.title)
                blogSearchItem.description = htmlToString(blogSearchItem.description)

                val getPostByLinkResult = postRepository.getPostByLink(blogSearchItem.link)
                if (getPostByLinkResult.status==Status.SUCCESS) {
                    if(getPostByLinkResult.data!=null) {
                        blogSearchItem.linkExist = true
                    }
                }
                else{
                    errorMessage = getPostByLinkResult.message.toString()
                    error = true
                }
                blogSearchItem
            }
        }

        return if (error){
            Resource.error(errorMessage,result)
        } else{
            Resource.success(result)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun htmlToString(htmlStr : String): String {
        return Html.fromHtml(htmlStr,FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH).toString()
    }

    fun postDateFormat(postdate : String): String {
        var formattedPostDate = postdate
        if (postdate.length>6) {
            formattedPostDate = postdate.substring(0..3) + "." +
                    postdate.substring(4..5) + "." +
                    postdate.substring(6..7)
        }
        return formattedPostDate
    }
}