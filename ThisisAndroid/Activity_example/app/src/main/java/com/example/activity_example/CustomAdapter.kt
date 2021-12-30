package com.example.activity_example

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.activity_example.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class CustomAdapter : RecyclerView.Adapter<Holder>(){
    var listData = mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        //한 화면에 그려지는 아이템 개수만큼 레이아웃 생성
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //생성된 아이템 레이아웃에 값 입력 후 목록에 출력
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {
        //목록에 보여줄 아이템의 개수
        return listData.size
    }
}

class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
    init{
        binding.root.setOnClickListener{//context는 binding.root에서 꺼낼 수 있음
            Toast.makeText(binding.root.context, "클릭된 아이템 = ${binding.Title.text}", Toast.LENGTH_LONG).show()
            val intent = Intent(binding.root.context, SubActivity::class.java)
            binding.root.context.startActivity(intent)
        }
    }

    fun setMemo(memo : Memo){
        binding.textNo.text = "${memo.no}"
        binding.Title.text = memo.title
        var sdf = SimpleDateFormat("yyyy/MM/dd")
        var formattedDate = sdf.format(memo.timestamp)
        binding.textDate.text = formattedDate
    }

}