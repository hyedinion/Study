package com.example.fragment_example

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment_example.databinding.FragmentDetailBinding
import com.example.fragment_example.databinding.FragmentListBinding

class DetailFragment : Fragment() {

    lateinit var mainActivity:MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity //타입캐스팅
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater,container,false)
        binding.btnBack.setOnClickListener { mainActivity.goBack()}
        return binding.root
    }


}