package com.example.pmdm_p09_android3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pmdm_p09_android3.Data.DataDbHelper

class MainActivity : AppCompatActivity() {

    lateinit var educodeDbHelper: DataDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        educodeDbHelper = DataDbHelper(this)
        educodeDbHelper.chargeTest()

        val btnLogin: Button = this.findViewById(R.id.btnLogin)
        val btnSignup: Button = this.findViewById(R.id.btnSignup)

        btnLogin.setOnClickListener(){
            startBody()
        }

        btnSignup.setOnClickListener(){
            val intent: Intent =Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    fun startBody(){
        val textEmail: TextView = this.findViewById(R.id.textEmailuser)
        val textPassword: TextView = this.findViewById(R.id.textPassword)

        if(educodeDbHelper.checkUser(textEmail.text.toString(),textPassword.text.toString())){
            val intent: Intent =Intent(this, BodyActivity::class.java)
            var user = educodeDbHelper.getUser(textEmail.text.toString())
            intent.putExtra("userCon", user)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Usuario no registrado.", Toast.LENGTH_LONG).show()
        }
    }
}