package com.example.activity_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activity_example.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {
    val binding by lazy { ActivityRecyclerBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data:MutableList<Memo> = loadData()
        Log.d("reccycler","수행되는 중 ${data.get(5)}")
        var adapter = CustomAdapter()
        adapter.listData = data
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun loadData() : MutableList<Memo>{
        val data : MutableList<Memo> = mutableListOf() //아무 데이터 없는 mutableList생성
        for (no in 1..100){
            val title = "이것이 안드${no}"
            val date = System.currentTimeMillis()
            val memo = Memo(no,title,date)
            data.add(memo)
        }
        return data
    }
}