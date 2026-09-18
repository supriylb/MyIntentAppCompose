package com.example.myintentappcompose

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String = "",
    val age: Int = 0,
    val email: String = "",
    val city: String = "",
) : Parcelable
