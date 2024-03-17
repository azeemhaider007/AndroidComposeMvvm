package com.example.medicineapp.response


import android.os.Parcelable
import com.example.medicineapp.db.entities.DrugEntity
import com.example.medicineapp.db.entities.ProblemEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Problem(
    @Json(name = "drugs")
    val drugs: List<Drug> = listOf(),
    @Json(name = "labs")
    val labs: List<String> = listOf(),
    @Json(name = "name")
    val name: String = ""
): Parcelable {
    companion object {
        fun mapData(problem: Problem): ProblemEntity {
            val drugs = problem.drugs.map { drug ->
                DrugEntity(
                    dose = drug.dose,
                    drug = drug.drug,
                    strength = drug.strength
                )
            }

            val labs = problem.labs

            return ProblemEntity(
                name = problem.name,
                drugs = drugs,
                labs = labs
            )

        }
    }
}