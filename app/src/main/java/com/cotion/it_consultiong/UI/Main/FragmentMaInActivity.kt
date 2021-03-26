package com.cotion.it_consultiong.UI.Main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.UI.FragmentChat
import com.cotion.it_consultiong.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class FragmentMaInActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private val TAG = "FragmentMaInActivity"
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private lateinit var fragmentBoard : FragmentBoard
    private lateinit var fragmentHome: FragmentHome


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)

//        binding.navView.setsele(this)

        Log.d(TAG, "FragmentMaInActivity - onCreate() called")


        fragmentHome = FragmentHome.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, fragmentHome).commit()

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mHome -> {
                fragmentHome = FragmentHome.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, fragmentHome).commit()
            }
            R.id.mBoard -> {
                fragmentBoard = FragmentBoard.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, fragmentBoard).commit()
            }
//            R.id.miProfile-> {
//                replaceFragment(FragmentBoard())
//                return true
//            }
//            R.id.miSettings-> {
//                replaceFragment(FragmentBoard())
//                return true
//            }
        }
        return true
    }

//    private fun replaceFragment(fragment: Fragment) {
//        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragment_layout, fragment)
//        fragmentTransaction.commitAllowingStateLoss()
//    }
//
//    fun onFabClicked(view: View) {
//
//        replaceFragment(FragmentChat())
//    }


}








