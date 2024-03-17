package com.example.medicineapp.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MedicineResponse(
    @Json(name = "problems")
    val problems: List<Problem> = listOf()
)