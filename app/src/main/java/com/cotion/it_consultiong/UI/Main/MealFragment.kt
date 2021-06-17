package com.cotion.it_consultiong.ui.main

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cotion.it_consultiong.databinding.FragmentMealBinding
import com.cotion.it_consultiong.mvvm.models.MealViewModel

@RequiresApi(Build.VERSION_CODES.O)
class MealFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentMealBinding? = null
    private val binding get() = _binding!!

    private val mealViewViewModel: MealViewModel by viewModels()

    private lateinit var breakButton: Button
    private lateinit var launchButton: Button
    private lateinit var dinnerButton: Button
    private lateinit var mealText: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealBinding.inflate(inflater, container, false)


        breakButton = binding.breakBtn
        launchButton = binding.launchBtn
        dinnerButton = binding.dinnerBtn
        mealText = binding.mealText


        mealViewViewModel.Retrofit(1, mealText)

        binding.dayText.text = mealViewViewModel.formatted_custon

        breakButton.setOnClickListener(this)
        launchButton.setOnClickListener(this)
        dinnerButton.setOnClickListener(this)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v) {
            breakButton -> mealViewViewModel.Retrofit(1, mealText)
            launchButton -> mealViewViewModel.Retrofit(2, mealText)
            dinnerButton -> mealViewViewModel.Retrofit(3, mealText)
        }
    }

}
