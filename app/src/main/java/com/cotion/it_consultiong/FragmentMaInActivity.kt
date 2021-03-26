package com.cotion.it_consultiong

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.cotion.it_consultiong.FrgamentHome
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.zip.Inflater


class FragmentMaInActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)

        binding.navView.setOnNavigationItemSelectedListener(this)


        supportFragmentManager.beginTransaction().add(R.id.fragment_layout, FrgamentHome()).commit()
    }






    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.miHome -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout, FrgamentHome())
                    .commitAllowingStateLoss()
                return true
            }


//            R.id.miSearch -> {
//                supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, TVFragment())
//                    .commitAllowingStateLoss()
//                return true
//            }
//            R.id.miProfile -> {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_layout, CalendarFragment()).commitAllowingStateLoss()
//                return true
//            }
//            R.id.miSettings -> {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_layout, CalendarFragment()).commitAllowingStateLoss()
//                return true
//            }
        }

        return false
    }


}
