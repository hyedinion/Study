package com.example.mvvm_di.ui

import androidx.recyclerview.widget.RecyclerView

class QuoteRecyclerAdapter : RecyclerView.Adapter<QuoteRecyclerAdapter.Holder>() {
    val QuoteList = mutableListOf<Music>()
    var mediaPlayer : MediaPlayer? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val music = musicList.get(position)
        holder.setMusic(music)
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    inner class Holder(val binding : ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        var musicUri : Uri? = null
        init {
            itemView.setOnClickListener {
                if(mediaPlayer!=null){
                    mediaPlayer?.release()//음악 종료
                    mediaPlayer = null
                }// 이거 안하면 클릭한 음악 전부 동시에 재생됨
                mediaPlayer = MediaPlayer.create(itemView.context,musicUri)//사용할 음원의 Uri
                mediaPlayer?.start()//음악 실행
            }
        }

        fun setMusic(music : Music){
            binding.run {
                imageAlbum.setImageURI(music.getAlbumUri())
                textArtist.text = music.artist
                textTitle.text = music.title

                val duration = SimpleDateFormat("mm:ss").format(music.duration)
                textDuration.text = duration
            }
            this.musicUri = music.getMusicUri()
        }
    }
}