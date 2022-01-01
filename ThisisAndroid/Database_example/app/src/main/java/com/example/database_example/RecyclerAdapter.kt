package com.example.database_example

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.database_example.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var listData = mutableListOf<Memo>()
    var helper : SqliteHelper? = null //삭제를 위한 helper property
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder(val binding : ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        var mMemo : Memo? = null
        init {
            binding.btnDelete.setOnClickListener {
                //db에서 지우고
                helper?.deleteMemo(mMemo!!) //deleteMemo는 null을 허용하지 않는데 mMemo는 null을 허용하기 때문에 !!키워드로 강제화해야함
                //listData에서 지우고
                listData.remove(mMemo)
                notifyDataSetChanged() //바뀐거 알려줌
            }
        }

        fun setMemo(memo : Memo){
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.textDatetime.text = "${sdf.format(memo.datetime)}"
            this.mMemo = memo
        }
    }
}
