package com.example.retrofit_example

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_example.databinding.ItemRecyclerBinding

class CustomAdapter : RecyclerView.Adapter<Holder>(){
    var userList : Repository? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        //한 화면에 그려지는 아이템 개수만큼 레이아웃 생성
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //생성된 아이템 레이아웃에 값 입력 후 목록에 출력
        val user = userList?.get(position)
        holder.setUser(user)
    }

    override fun getItemCount(): Int {
        //목록에 보여줄 아이템의 개수
        return userList?.size?:0
    }
}


class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
    fun setUser(user : RepositoryItem?){
        binding.textName.text = user?.name
        binding.textId.text = user?.node_id
        Glide.with(binding.imageView).load(user?.owner?.avatar_url).into(binding.imageView)
    }

}