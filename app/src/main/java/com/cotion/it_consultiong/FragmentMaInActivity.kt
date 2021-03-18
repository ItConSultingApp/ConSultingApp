package com.cotion.it_consultiong

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cotion.it_consultiong.databinding.ActivityChooseMajorBinding
import com.cotion.it_consultiong.databinding.ActivityLoginMainBinding
import com.cotion.it_consultiong.databinding.FragmentHomeBinding
import com.cotion.it_consultiong.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class FragmentMaInActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)

        binding.navView.setOnNavigationItemSelectedListener(this)


        val fragmentHome=FrgamentHome()
        supportFragmentManager.beginTransaction().add(R.layout.fragment_main,fragmentHome::class.java).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
4

        return true
    }
}