package com.example.pmdm_p09_android3.models

import android.os.Parcel
import android.os.Parcelable

class Users : Parcelable {
    var user: String
    var email: String
    var pass: String
    var success: Int = 0
    var error: Int = 0

    constructor(email: String, user: String, pass: String){
        this.user = user
        this.email = email
        this.pass = pass
    }

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()

    ) {
    }

    @JvmName("setSuccess1")
    fun setSuccess(sueccess: Int){
        this.success = success
    }

    @JvmName("getSuccess1")
    fun getSuccess(): Int{
        return success
    }

    @JvmName("setError1")
    fun setError(error: Int){
        this.error = error
    }

    @JvmName("getError1")
    fun getError(): Int{
        return error
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(email)
        p0?.writeString(user)
        p0?.writeString(pass)
    }

    companion object CREATOR : Parcelable.Creator<Users> {
        override fun createFromParcel(parcel: Parcel): Users {
            return Users(parcel)
        }

        override fun newArray(size: Int): Array<Users?> {
            return arrayOfNulls(size)
        }
    }

}