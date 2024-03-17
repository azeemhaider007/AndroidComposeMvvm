package com.example.medicineapp.ui.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medicineapp.navigation.Screen
import com.example.medicineapp.ui.theme.Blue
import com.example.medicineapp.viewmodel.MedicineViewModel
import com.example.medicineapp.views.CardItem
import com.example.medicineapp.views.CardShimmerEffect
import com.google.gson.Gson


@Composable
fun MedicineListScreen(name: String?, viewModel: MedicineViewModel, navController: NavController) {

    val problems = viewModel.problems.observeAsState(emptyList())
    val loading = viewModel.loading.observeAsState(initial = true)

    if (loading.value) {
        ShimmerEffect()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 40.dp, start = 16.dp, end = 16.dp)
        ) {

            Text(
                text = "Welcome $name",
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Blue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )

            LazyColumn(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(count = problems.value.size) {
                    val problem = problems.value[it]
                    CardItem(problem = problem) { item ->
                        val json = Uri.encode(Gson().toJson(item))
                        navController.navigate(Screen.ProblemDetailScreen.withArgs(json))
                    }
                }
            }

        }

    }


}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.padding(16.dp)) {
        repeat(10) {
            CardShimmerEffect()
        }

    }
}