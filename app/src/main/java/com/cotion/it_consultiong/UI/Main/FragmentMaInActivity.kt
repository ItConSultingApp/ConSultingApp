package com.cotion.it_consultiong.UI.Main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.cotion.it_consultiong.R
import com.cotion.it_consultiong.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class FragmentMaInActivity : AppCompatActivity() {

    private val TAG = "FragmentMaInActivity"
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }



    @RequiresApi(Build.VERSION_CODES.O)
    private val mOnNavigationiItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when(item.itemId){
            R.id.mBoard -> {
                Log.d(TAG,"FragmentMaInActivity - () called")
                replaceFragment(FragmentBoard())
                return@OnNavigationItemSelectedListener true
            }

            R.id.mHome -> {
                Log.d(TAG,"FragmentMaInActivity - () called")
                replaceFragment(FragmentHome())
                return@OnNavigationItemSelectedListener true
            }

            R.id.miProfile -> {
                Log.d(TAG,"FragmentMaInActivity - () called")
                println("info pressed")
                replaceFragment(FragmentMeal())
                return@OnNavigationItemSelectedListener true
            }

            else -> false
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        replaceFragment(FragmentHome())
        binding.navView.setOnNavigationItemSelectedListener(mOnNavigationiItemSelectedListener)
        Log.d(TAG, "FragmentMaInActivity - onCreate() called")
        supportFragmentManager.beginTransaction().add(R.id.fragment_layout, FragmentHome()).commit()


    }




    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_layout, fragment)
        fragmentTransaction.commitAllowingStateLoss()
    }
//
//    fun onFabClicked(view: View) {
//
//        replaceFragment(FragmentChat())
//    }


}








