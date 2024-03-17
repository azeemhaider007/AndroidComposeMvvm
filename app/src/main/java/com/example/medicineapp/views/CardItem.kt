package com.example.medicineapp.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicineapp.db.entities.ProblemEntity
import com.example.medicineapp.response.Problem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(problem: ProblemEntity, onItemClick: (ProblemEntity) -> Unit) {

    Box( modifier = Modifier
        .fillMaxWidth().padding(5.dp)) {

        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onItemClick.invoke(problem)
            }
        ) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                Text(text = problem.name, style = MaterialTheme.typography.displaySmall.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF2196F3)))
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp))

                Text(text = "Available Medication:", style = MaterialTheme.typography.displaySmall.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF424242)))
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp))
                problem.drugs.forEach {
                        drug ->
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp))
                    Text(text = drug.drug+": "+drug.strength, style = MaterialTheme.typography.displaySmall.copy(fontSize = 12.sp, fontWeight = FontWeight.Normal, color = Color(0xFF666565)))

                }

            }


        }
    }



}


@Preview
@Composable
fun CardItemPreview() {

}