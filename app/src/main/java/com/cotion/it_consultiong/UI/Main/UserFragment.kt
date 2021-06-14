package com.cotion.it_consultiong.ui.main

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cotion.it_consultiong.databinding.FragmentUpdateBoardBinding
import com.cotion.it_consultiong.databinding.FragmentUserInformationBinding

class UserFragment : Fragment() {
    private var _binding: FragmentUserInformationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserInformationBinding.inflate(inflater, container, false)


        return binding.root


    }
}