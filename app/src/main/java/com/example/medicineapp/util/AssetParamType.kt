package com.example.medicineapp.util

import android.os.Bundle
import androidx.navigation.NavType
import com.example.medicineapp.db.entities.ProblemEntity
import com.example.medicineapp.response.Problem
import com.google.gson.Gson

class AssetParamType : NavType<ProblemEntity>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ProblemEntity? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ProblemEntity {
        return Gson().fromJson(value, ProblemEntity::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ProblemEntity) {
        bundle.putParcelable(key, value)
    }
}