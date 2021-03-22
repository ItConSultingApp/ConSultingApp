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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)

        binding.navView.setOnNavigationItemSelectedListener(this)

        Log.d(TAG, "FragmentMaInActivity - onCreate() called")


        replaceFragment(FragmentHome())


    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mHome -> {
                replaceFragment(FragmentHome())
                return true
            }
            R.id.mBoard -> {
                replaceFragment(FragmentBoard())
                return true
            }
        }
        return false
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_layout, fragment)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun onFabClicked(view: View) {

        replaceFragment(FragmentChat())
    }


}








