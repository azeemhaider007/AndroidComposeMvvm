package com.example.medicineapp.db.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = ProblemEntity.TABLE_NAME)
data class ProblemEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",

    var drugs: List<DrugEntity>,

    var labs: List<String>,

    ) : Parcelable {

    companion object {
        const val TABLE_NAME = "table_problems"
    }
}