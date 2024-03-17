package com.example.medicineapp.db

import androidx.room.TypeConverter
import com.example.medicineapp.db.entities.DrugEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object ListTypeConvertor {
    @TypeConverter
    fun fromString(value: String): List<DrugEntity> {
        val listType = object : TypeToken<List<DrugEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: List<DrugEntity>): String {
        val gson = Gson()
        return gson.toJson(value)
    }
}