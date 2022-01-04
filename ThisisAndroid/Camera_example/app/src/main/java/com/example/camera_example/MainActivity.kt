package com.example.camera_example

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.camera_example.databinding.ActivityMainBinding
import java.io.IOError
import java.io.IOException
import java.text.SimpleDateFormat

class MainActivity : BaseActivity() {
    val PERM_STORAGE = 99 //외부 저장소 권한 처리
    val PERM_CAMERA = 100 //카메라 권한 처리
    val REQ_CAMERA = 101 //카메라 촬영 요청
    val REQ_STORAGE = 102 //갤러리 호출
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    var realUri : Uri? = null//uri 저장할 프로퍼티

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERM_STORAGE)

    }

    //촬영한 이미지를 저장할 Uri를 미디어스토어에 생성
    fun createImageUri(filename : String, mimeType : String) : Uri?{
        var values = ContentValues()//contentValue클래스를 사용해서 contentResolver로 저장할 수 있음
        values.put(MediaStore.Images.Media.DISPLAY_NAME,filename)
        values.put(MediaStore.Images.Media.MIME_TYPE,mimeType)
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)
    }

    fun setViews(){
        binding.btnCamera.setOnClickListener { //권한이 승인 되었을 때만 onClickListener를 달아줌
            requirePermissions(arrayOf(Manifest.permission.CAMERA),PERM_CAMERA)
        }
        //갤러리 가져오기
        binding.btnGallery.setOnClickListener {
            openGallery()
        }
    }
    fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK)//Action_pick을 사용하면 intent.type에서 설정한 종류의 데이터를 불러와 목록으로 나열한 후 선택할 수 있는 뷰가 나옴
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent,REQ_STORAGE)
    }
    fun openCamera(){ //권한이 승인 되었을 때만 카메라 오픈
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        createImageUri(newFileName(),"image/jpeg")?.let { uri ->//파일명 생성 후 카메라 오픈
            realUri = uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT,realUri)
            startActivityForResult(intent,REQ_CAMERA)
        }

    }
    fun newFileName() : String {//파일명을 만들어 주는 메서드
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "$filename"
    }
    fun loadBitmap(photoUri: Uri) : Bitmap?{//Uri를 받아서 결과값을 Bitmap으로 반환
        var image : Bitmap? = null
        try {
            image = if (Build.VERSION.SDK_INT > 27){//API 버전별 처리
                //ImageDecoder 사용
                val source : ImageDecoder.Source = ImageDecoder.createSource(this.contentResolver,photoUri)
                ImageDecoder.decodeBitmap(source)
            }else{
                MediaStore.Images.Media.getBitmap(this.contentResolver,photoUri)//27이하면 getBitmap메서드 사용
            }
        }catch (e : IOException){
            e.printStackTrace()
        }
        return image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            when (requestCode){
                REQ_CAMERA -> {
                    realUri?.let{ uri -> //realUri에 값이 저장되었으면
                        val bitmap = loadBitmap(uri)
                        binding.imageView.setImageBitmap(bitmap)
                        realUri = null
                    }
//                    if (data?.extras?.get("data") != null){
//                        val bitmap = data?.extras?.get("data") as Bitmap
//                        binding.imageView.setImageBitmap(bitmap)
//                    }
                }

                REQ_STORAGE -> {
                    data?.data?.let { uri ->
                        binding.imageView.setImageURI(uri)
                    }
                }
            }
        }
    }

    override fun permissionGranted(requestCode: Int) {
        when (requestCode){
            PERM_STORAGE -> setViews()
            PERM_CAMERA -> openCamera()
        }
    }

    override fun permissionDenied(requestCode: Int) {
        when (requestCode){
            PERM_STORAGE -> {
                Toast.makeText(baseContext, "외부 저장소 권한을 승인해야 앱을 사용할 수 있습니다.", Toast.LENGTH_LONG).show()
                finish()
            }
            PERM_CAMERA -> {
                Toast.makeText(baseContext, "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }
}