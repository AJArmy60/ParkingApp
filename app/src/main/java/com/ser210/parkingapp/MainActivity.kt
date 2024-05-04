package com.ser210.parkingapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.fragment.NavHostFragment
import com.ser210.parkingapp.data.Space
import com.ser210.parkingapp.data.SpaceRoomDatabase
import com.ser210.parkingapp.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private var isBackgroundDark = false
    private var isTextDark = true
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment2) as NavHostFragment
        val navController = navHostFragment.navController
        val drawer = binding.drawerLayout
        val builder = AppBarConfiguration.Builder(navController.graph)
        builder.setOpenableLayout(drawer)
        val appBarConfiguration = builder.build()

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        val navView = binding.navView
        NavigationUI.setupWithNavController(navView, navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.settingsButton -> {
                val rootView = binding.root
                if (isBackgroundDark) {
                    rootView.setBackgroundColor(android.graphics.Color.WHITE)
                } else {
                    rootView.setBackgroundColor(android.graphics.Color.DKGRAY)
                }
                isBackgroundDark = !isBackgroundDark
                val sharedPreferences = getSharedPreferences("appPreferences", MODE_PRIVATE)
                val isTextDark = sharedPreferences.getBoolean("isTextDark", false)
                with (sharedPreferences.edit()) {
                    putBoolean("isTextDark", !isTextDark)
                    apply()
                }
                true
            }

            R.id.helpFragment -> {
                val navController = findNavController(R.id.nav_host_fragment2)
                navController.navigate(R.id.helpFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
