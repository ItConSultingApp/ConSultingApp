package com.cotion.it_consultiong.UI

import android.os.Bundle
<<<<<<< HEAD
import android.view.View
=======
import android.util.Log
>>>>>>> aed244012055c48ca335f17593cd8cade4cb41d8
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.cotion.it_consultiong.R
<<<<<<< HEAD
=======
import com.cotion.it_consultiong.data.data_model.signInUserInfo
>>>>>>> aed244012055c48ca335f17593cd8cade4cb41d8
import com.cotion.it_consultiong.databinding.FragmentMainBinding


import com.google.android.material.bottomnavigation.BottomNavigationView


class FragmentMaInActivity : AppCompatActivity() {

    private val TAG = "FragmentMaInActivity"
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

<<<<<<< HEAD
=======

>>>>>>> aed244012055c48ca335f17593cd8cade4cb41d8
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_home_navi, R.id.fragment_board_navi, R.id.fragment_profile_navi
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
<<<<<<< HEAD
        initNavigation()

    }

=======


    }
>>>>>>> aed244012055c48ca335f17593cd8cade4cb41d8
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
<<<<<<< HEAD

    private fun initNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.fragment_board_navi || destination.id == R.id.fragment_profile_navi || destination.id == R.id.fragment_home_navi|| destination.id == R.id.fragment_home_navi) {
                binding.navView.visibility = View.VISIBLE
            } else {
                binding.navView.visibility = View.GONE
            }
        }
    }
=======
>>>>>>> aed244012055c48ca335f17593cd8cade4cb41d8
}








