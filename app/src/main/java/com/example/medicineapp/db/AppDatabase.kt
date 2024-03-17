package com.example.medicineapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.medicineapp.db.entities.ProblemEntity


@Database(entities = [ProblemEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListTypeConvertor::class,StringListConvertor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun problemDao(): ProblemDao

}