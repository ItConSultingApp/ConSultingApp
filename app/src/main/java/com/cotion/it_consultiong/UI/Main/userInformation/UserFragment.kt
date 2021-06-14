package com.cotion.it_consultiong.UI.Main.userInformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cotion.it_consultiong.UI.Main.userInformation.adapter.UserInformationAdapter
import com.cotion.it_consultiong.databinding.FragmentUserInformationBinding
import com.google.android.material.tabs.TabLayoutMediator

class UserFragment : Fragment() {
    private var _binding: FragmentUserInformationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserInformationBinding.inflate(inflater, container, false)


        val adapter = childFragmentManager?.let { UserInformationAdapter(it, lifecycle) }
        binding.mealViewPager2.adapter = adapter


        TabLayoutMediator(binding.mealTabLayout, binding.mealViewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "1학년"
                }
                1 -> {
                    tab.text = "2학년"
                }
                2 -> {
                    tab.text = "3학년"
                }
            }
        }.attach()
        return binding.root


    }
}