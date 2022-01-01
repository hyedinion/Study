package com.example.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var listData = mutableListOf<RoomMemo>() //Memo 클래스를 RoomMemo클래스로 바꿈
    var helper : RoomHelper? = null //RoomHelper로 바꿈
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setRoomMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder(val binding : ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        var mRoomMemo : RoomMemo? = null
        init {
            binding.btnDelete.setOnClickListener {
                helper?.roomMemoDao()?.delete(mRoomMemo!!)//db에서 (helper를 통해)해당 mRoomMemo삭제
                listData.remove(mRoomMemo)
                notifyDataSetChanged()
            }
        }

        fun setRoomMemo(memo : RoomMemo){
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.textDatetime.text = "${sdf.format(memo.datetime)}"
            this.mRoomMemo = memo
        }
    }
}
