package com.example.pmdm_p09_android3

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pmdm_p09_android3.Data.DataDbHelper
import com.example.pmdm_p09_android3.databinding.ActivityBodyBinding
import com.example.pmdm_p09_android3.models.Users
import com.google.android.material.bottomappbar.BottomAppBar

class BodyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBodyBinding
    private lateinit var user: Users
    lateinit var educodeDbHelper: DataDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        educodeDbHelper = DataDbHelper(this)
        binding = ActivityBodyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_body)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_user, R.id.navigation_test, R.id.navigation_results
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        user = intent.getParcelableExtra("userCon")!!
        //setup(user!!)

    }

    fun setup(user: Users){
        var textEmail: TextView = findViewById(R.id.textUserEmail)
        var textUser: TextView = findViewById(R.id.textUserName)
        var textPass: TextView = findViewById(R.id.textUserPass)

        textEmail.setText(user.email)
        textUser.setText(user.user)
        textPass.setText(user.pass)
    }

    fun getLogUser(): Users{
        return user
    }

    fun getDbHelper(): DataDbHelper{
        return educodeDbHelper
    }
}