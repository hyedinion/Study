package com.example.database_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.database_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    val helper = SqliteHelper(this, "memo",1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = RecyclerAdapter()
        adapter.helper = helper //삭제 버튼을 위해 리사이클러뷰 어댑터에 dbHelper를 전달해줬음
        adapter.listData.addAll(helper.selectMemo()) //db에서 데이터 가져와서 adapter의 listData에 추가해줌
        binding.recyclerMemo.adapter = adapter //리사이클러뷰 어댑터 연결
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        for (data in adapter.listData){
            Log.d("dbtest", "no = ${data.no}, content = ${data.content}")
        }


        binding.btnSave.setOnClickListener {
            if (binding.editMemo.text.toString().isNotEmpty()){
                val memo = Memo(null,binding.editMemo.text.toString(), System.currentTimeMillis())
                helper.insertMemo(memo)
                adapter.listData.clear()
                adapter.listData.addAll(helper.selectMemo())//어댑터 데이터 다 지우고 다시 추가
                adapter.notifyDataSetChanged()//데이터가 바꼈다고 알려주기, recyclerview 다시 구성함
                binding.editMemo.setText("")
            }
        }

    }
}