package com.example.retrofit_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_example.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = CustomAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())//컬렉션으로 변환해줌
            .build()

        //retrofit을 이용해 데이터를 불러옴
        binding.btnRequest.setOnClickListener {
            val githubService = retrofit.create(GithubService::class.java) //GithubService 인터페이스를 이용해 실행 가능한 서비스 객체 생성
            //create메서드가 users()메서드 안에 비동기 통신으로 데이터를 가져오는 enqueue() 메서드를 추가해 놓음
            githubService.users().enqueue(object : Callback<Repository>{//enqueue()가 호출되면 통신이 시작됨
                //서버로 부터 응답 받으면 콜백 인터페이스가 작동함
                override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                    adapter.userList = response.body() as Repository //데이터를 꺼낸 후 형변환
                    adapter.notifyDataSetChanged()//리사이클러뷰에 반영
                }

                override fun onFailure(call: Call<Repository>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }

    }
}

interface GithubService{
    @GET("users/Kotlin/repos") //호출방식, 주소
    fun users(): Call<Repository> //데이터 형식
}