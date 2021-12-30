package com.example.activity_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.activity_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var data = listOf("-선택하세요-","1월","2월","3월")
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //TODO("Not yet implemented")
                binding.result.text = data.get(p2)
                //p1 : view
                //p2 : position
                //p3 : id
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }
        }

        val intent = Intent(this, SubActivity::class.java)
        intent.putExtra("from1","hello")
        intent.putExtra("from2",2021)
        //binding.button.setOnClickListener { startActivity(intent)}
        binding.button.setOnClickListener { startActivityForResult(intent,99)}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //requestCode : startActivityForResult에서 호출했는지 확인
        //resultCode : 서브 액티비티에서 입력한 코드 OK,CANCELED
        //data : intent
        if (resultCode == RESULT_OK){
            when (requestCode) {
                99 -> {
                    val message = data?.getStringExtra("returnValue")
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}