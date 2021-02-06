package com.sam.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val  navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment)
        navController = navHostFragment!!.findNavController()
        val toolbarVal = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbarVal)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}