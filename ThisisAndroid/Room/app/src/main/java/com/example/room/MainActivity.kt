package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    var helper : RoomHelper? = null //interface이기 때문에 databaseBuilder를 통해 생성해야 함
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Room은 기본적으로 서브 스레드에서 동작하도록 설계되어 있음
        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_memo")
            .allowMainThreadQueries().build()

        val adapter = RecyclerAdapter()
        adapter.helper = helper
        adapter.listData.addAll(helper?.roomMemoDao()?.getAll()?: listOf())
        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        for (data in adapter.listData){
            Log.d("dbtest", "no = ${data.no}, content = ${data.content}")
        }

        binding.btnSave.setOnClickListener {
            if (binding.editMemo.text.toString().isNotEmpty()){
                val memo = RoomMemo(binding.editMemo.text.toString(), System.currentTimeMillis())
                helper?.roomMemoDao()?.insert(memo)
                adapter.listData.clear()
                adapter.listData.addAll(helper?.roomMemoDao()?.getAll()?: listOf())
                adapter.notifyDataSetChanged()
                binding.editMemo.setText("")
            }
        }

    }
}