package com.example.baseactivity_example

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.example.baseactivity_example.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            requirePermissions(arrayOf(Manifest.permission.CAMERA),10)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 99){
            if (resultCode == Activity.RESULT_OK){
                Log.d("카메라","촬영성공")
            }else{
                Log.d("카메라","촬영실패")
            }
        }
    }

    override fun permissionGranted(requestCode: Int) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 99)//카메라 앱 실행
    }

    override fun permissionDenied(reauestCode: Int) {
        Toast.makeText(baseContext,"권한 거부됨",Toast.LENGTH_LONG).show()
    }
}