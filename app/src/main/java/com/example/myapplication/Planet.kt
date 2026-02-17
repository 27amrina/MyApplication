package com.example.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Planet(
    val name: String,
    val description: String,
    val photo: Int,
    val type: String,
    val distanceFromSun: String,
    val dayLength: String,
    val yearLength: String,
    val avgTemp: String,
    val funFacts: String
) : Parcelable
