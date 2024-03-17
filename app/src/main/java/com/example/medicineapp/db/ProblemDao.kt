package com.example.medicineapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medicineapp.db.entities.ProblemEntity

@Dao
interface ProblemDao {

    @Query("SELECT * FROM table_problems")
    fun getAllProblem() : LiveData<List<ProblemEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProblems(problems:List<ProblemEntity>)

    @Query("DELETE FROM table_problems")
    suspend fun deleteAll()
}