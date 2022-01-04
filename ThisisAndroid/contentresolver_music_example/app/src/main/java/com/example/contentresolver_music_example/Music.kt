package com.example.contentresolver_music_example

import android.net.Uri
import android.provider.MediaStore

class Music (id : String, title : String?, artist : String?, albumId: String?, duration : Long?) {
    var id : String = ""
    var title: String?
    var artist : String?
    var albumId : String?
    var duration : Long?

    init {
        this.id = id
        this.title = title
        this.artist = artist
        this.albumId = albumId
        this.duration = duration
    }

    fun getMusicUri(): Uri{// 음원URI는 기본 MediaStore의 주소와 음원 ID를 조합해서 만들기 때문에 메서드로 만드는게 좋음
        return Uri.withAppendedPath( MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,id)
    }

    fun getAlbumUri() : Uri{ //음원 파일별로 썬네일을 지정할 수 있음, 앨범 이미지를 사용하며 이것을 앨범아트라고 함
        return Uri.parse("content://media/external/audio/albumart/"+albumId)
    }
}