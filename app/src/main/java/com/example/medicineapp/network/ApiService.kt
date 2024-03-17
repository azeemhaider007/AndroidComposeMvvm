package com.example.medicineapp.network

import com.example.medicineapp.response.MedicineResponse
import retrofit2.http.GET

interface ApiService {

    @GET("34293a07-be25-46e6-933e-570b66351cb5")
    suspend fun getDiseases(): MedicineResponse
}