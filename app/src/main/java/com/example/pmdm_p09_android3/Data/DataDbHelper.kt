package com.example.pmdm_p09_android3.Data


import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.example.pmdm_p09_android3.BodyActivity
import com.example.pmdm_p09_android3.MainActivity

import com.example.pmdm_p09_android3.models.Users
import com.example.pmdm_p09_android3.ui.home.UserFragment

class DataDbHelper(context: Activity): SQLiteOpenHelper(context, "educode.db", null, 1)  {

    override fun onCreate(db: SQLiteDatabase?) {
        val createUsers = "CREATE TABLE users (email TEXT PRIMARY KEY, name TEXT, password TEXT)"
        db!!.execSQL(createUsers)

        val createResults = "CREATE TABLE results (email TEXT PRIMARY KEY, successes INTEGER, errors INTEGER)"
        db!!.execSQL(createResults)

        val createTest = "CREATE TABLE test (num INTEGER, question TEXT, answer1 TEXT, answer2 TEXT, answer3 TEXT, correct TEXT)"
        db!!.execSQL(createTest)
    }



    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p1 != p2){
            val deleteUsers = "DROP TABLE IF EXISTS users"
            db!!.execSQL(deleteUsers)
            onCreate(db)
        }
    }

    fun addUser(user: Users, context: Context){
        val data = ContentValues()
        data.put("email", user.email)
        data.put("name", user.user)
        data.put("password", user.pass)

        val reslt = ContentValues()
        reslt.put("email",user.email)
        reslt.put("successes",0)
        reslt.put("errors",0)


        val db = this.writableDatabase
        db.insert("users", null, data)
        db.insert("results",null,reslt)
        db.close()
    }

    fun checkUser(email: String, password: String): Boolean {
        val db: SQLiteDatabase = this.readableDatabase
        val data = arrayOf(email, password)
        val cursor = db.rawQuery("SELECT * FROM users WHERE email=? AND password=?",data)
        var access = false
        if (cursor.count == 1) {
            access = true
        }
        db.close()
        return access
    }

    fun getUser(email: String): Users{
        val db: SQLiteDatabase = this.readableDatabase
        val data = arrayOf(email)
        val cursor = db.rawQuery("SELECT * FROM users WHERE email=?",data)
        cursor.moveToFirst()
        val user = Users(cursor.getString(0),cursor.getString(1),cursor.getString(2))
        db.close()
        return user
    }

    fun getResult(email: String): IntArray{
        val db: SQLiteDatabase = this.readableDatabase
        val data = arrayOf(email)
        val cursor = db.rawQuery("SELECT successes, errors FROM results WHERE email=?",data)
        cursor.moveToFirst()
        val result = IntArray(2)
        result[0]=cursor.getInt(0)
        result[1]=cursor.getInt(1)
        db.close()

        return result
    }

    fun chargeTest(){
        val quest1 = ContentValues()
        quest1.put("num",1)
        quest1.put("question","¿En qué lugar se ejecuta el código PHP?")
        quest1.put("answer1","Servidor")
        quest1.put("answer2","Cliente")
        quest1.put("answer3","Servidor Cloud")
        quest1.put("correct","Servidor")

        val quest2 = ContentValues()
        quest2.put("num",2)
        quest2.put("question","¿En qué atributo de formulario indicamos la página a la que enviamos los datos?")
        quest2.put("answer1","name")
        quest2.put("answer2","file")
        quest2.put("answer3","action")
        quest2.put("correct","action")

        val quest3 = ContentValues()
        quest3.put("num",3)
        quest3.put("question","Dos de las formas de pasar parámetros entre páginas PHP son:")
        quest3.put("answer1","Get y Put")
        quest3.put("answer2","Post y Get")
        quest3.put("answer3","Into e Include")
        quest3.put("correct","Post y Get")

        val db: SQLiteDatabase = this.writableDatabase
        db.insert("test", null, quest1)
        db.insert("test", null, quest2)
        db.insert("test", null, quest3)
        db.close()
    }

    fun getQuestion(question: Int): Array<String>{
        val db: SQLiteDatabase = this.readableDatabase
        val data = arrayOf(question.toString())
        val cursor = db.rawQuery("SELECT question, answer1, answer2, answer3, correct, num FROM test WHERE num="+question,null)
        cursor.moveToFirst()
        println(cursor.getString(0))
        val results = arrayOf(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4))
        db.close()
        return results
    }

    fun updateResults(email: String, success: Int, errors: Int){
        val reslt = ContentValues()
        reslt.put("successes",success)
        reslt.put("errors",errors)

        val db: SQLiteDatabase = this.writableDatabase
        db.update("results",reslt,"email=?", arrayOf(email))
        db.close()
    }
}