package com.example.medicineapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medicineapp.db.entities.ProblemEntity
import com.example.medicineapp.ui.theme.BlackLight

@Composable
fun ProblemDetailScreen(navController: NavController, problem: ProblemEntity?) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = problem?.name ?: "", style = MaterialTheme.typography.displayMedium.copy(fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White),

                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2196F3))
                    .padding(16.dp),
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Text(
                text = "Available Medication:",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.displaySmall.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White),
                modifier = Modifier.fillMaxWidth().background(Color(0xFF2196F3)).padding(8.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
            )
            problem?.drugs?.forEach { drug ->
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
                Text(text = drug.drug + ": " + drug.strength, style = MaterialTheme.typography.displaySmall.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal, color = BlackLight))

            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Text(
                text = "Lab Tests:",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.displaySmall.copy(fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White),
                modifier = Modifier.fillMaxWidth().background(Color(0xFF2196F3)).padding(8.dp)
                )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
            )
            problem?.labs?.forEach { lab ->
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                )
                Text(text = lab, style = MaterialTheme.typography.displaySmall.copy(fontSize = 16.sp, fontWeight = FontWeight.Normal, color = BlackLight))

            }


        }

    }

}
