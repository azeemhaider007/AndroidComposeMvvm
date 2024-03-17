package com.example.medicineapp.db.entities

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class DrugEntity(
    var dose: Int = 0,
    var drug: String = "",
    var strength: String = ""
) : Parcelable{
   constructor():this(0,"","")
}
