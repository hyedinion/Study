package com.example.intership_scrapproject_android.presentation.blog_search.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.example.intership_scrapproject_android.util.TestTags

@Composable
fun BlogSearchErrorSnackBar(
    e : LoadState.Error,
    retry : () -> Unit,
) {
    Snackbar(
        action = {
            Button(onClick = {retry()}) {
                Text("retry")
            }
        },
        modifier = Modifier
            .padding(8.dp)
            .testTag(TestTags.blogSearchErrorSnackBar)
    ) {
        Text(
            text = e.error.localizedMessage!!,
            color = Color.Black
        )
    }
}