package com.example.medicineapp.response


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Drug(
    @Json(name = "dose")
    val dose: Int = 0,
    @Json(name = "drug")
    val drug: String = "",
    @Json(name = "strength")
    val strength: String = ""
) : Parcelable