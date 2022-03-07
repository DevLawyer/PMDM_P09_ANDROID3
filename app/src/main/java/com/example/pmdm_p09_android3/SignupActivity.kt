package com.example.pmdm_p09_android3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pmdm_p09_android3.Data.DataDbHelper
import com.example.pmdm_p09_android3.models.Users

class SignupActivity : AppCompatActivity() {

    lateinit var educodeDbHelper: DataDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        educodeDbHelper = DataDbHelper(this)

        var textEmail: TextView = this.findViewById(R.id.textSignupEmail)
        var textUser: TextView = this.findViewById(R.id.textSignupUser)
        var textPass: TextView = this.findViewById(R.id.textSignupPass)
        val btnSignup: Button = this.findViewById(R.id.btnSignupSignup)
        val btnCancel: Button = this.findViewById(R.id.btnSignupCancel)

        btnSignup.setOnClickListener(){
            if(textEmail.text.isNotBlank() && textUser.text.isNotBlank() && textPass.text.isNotBlank()){
                val newUser: Users = Users(textEmail.text.toString(), textUser.text.toString(), textPass.text.toString())

                educodeDbHelper.addUser(newUser, this)

                textEmail.setText("")
                textUser.setText("")
                textPass.setText("")

                Toast.makeText(this, "Usuario creado.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Error al guardar los datos.", Toast.LENGTH_LONG).show()
            }
        }

        btnCancel.setOnClickListener(){
            finish()
        }
    }
}