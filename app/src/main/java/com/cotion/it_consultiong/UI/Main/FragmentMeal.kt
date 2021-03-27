package com.cotion.it_consultiong.UI.Main

import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cotion.it_consultiong.UI.Main.FragmentBoard.Companion.TAG
import com.cotion.it_consultiong.databinding.FragmentMealBinding
import com.cotion.it_consultiong.mvvm.viewmodel.MealViewModel
import com.example.school_cafeteria.Model.meal_model
import com.example.school_cafeteria.Ui.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class FragmentMeal : Fragment(), View.OnClickListener {
    private var _binding: FragmentMealBinding? = null
    private val binding get() = _binding!!

    private val mealViewModel: MealViewModel by viewModels()

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


        mealViewModel.Retrofit(1, mealText)

        binding.dayText.text = mealViewModel.formatted_custon

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
            breakButton -> mealViewModel.Retrofit(1, mealText)
            launchButton -> mealViewModel.Retrofit(2, mealText)
            dinnerButton -> mealViewModel.Retrofit(3, mealText)
        }
    }

}
