package com.example.fragment_example

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment_example.databinding.FragmentListBinding

class ListFragment : Fragment() {
    var mainActivity : MainActivity?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainActivity) mainActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflater로 생성한 뷰를 바로 리턴
        //return inflater.inflate(R.layout.fragment_list, container, false)

        // 바인딩으로 생성한 후 레이아웃에 있는 bntNext버튼에 리스너를 등록한 후 binding.root를 리턴
        val binding = FragmentListBinding.inflate(inflater,container,false)
        binding.btnNext.setOnClickListener { mainActivity?.goDetail() }
        return binding.root
    }
}