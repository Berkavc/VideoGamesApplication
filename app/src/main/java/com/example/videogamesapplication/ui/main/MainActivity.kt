package com.example.videogamesapplication.ui.main

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.videogamesapplication.R
import com.example.videogamesapplication.databinding.ActivityDetailsBinding
import com.example.videogamesapplication.databinding.ActivityMainBinding
import com.example.videogamesapplication.ui.BaseActivity
import com.example.videogamesapplication.ui.details.DetailsViewModel
import com.example.videogamesapplication.utils.observe
import com.example.videogamesapplication.utils.viewModel

class MainActivity : BaseActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private var currentItemTitle: CharSequence? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mainViewModel = viewModel(viewModelFactory) {

        }
        arrangeUI()
    }

    private fun arrangeUI() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        currentItemTitle = navView.menu.findItem(navView.selectedItemId).title
        binding.navView.setOnNavigationItemSelectedListener { item ->
            if (currentItemTitle != item.title) {
                currentItemTitle = item.title
                when (item.title) {
                    this.resources.getString(R.string.title_home) -> navController.navigate(R.id.navigation_home)
                    this.resources.getString(R.string.title_like) -> navController.navigate(R.id.navigation_like)
                }
            }
            true
        }
    }
}