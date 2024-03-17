package com.example.medicineapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.medicineapp.ui.screens.LoginScreen
import com.example.medicineapp.ui.screens.MedicineListScreen
import com.example.medicineapp.ui.screens.ProblemDetailScreen
import com.example.medicineapp.db.entities.ProblemEntity
import com.example.medicineapp.util.AssetParamType
import com.example.medicineapp.viewmodel.MedicineViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.MedicineListScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->

            val medicineViewModel: MedicineViewModel = hiltViewModel()
            MedicineListScreen(name = entry.arguments?.getString("name"), medicineViewModel, navController = navController)
        }

        composable(route = Screen.ProblemDetailScreen.route + "/{problem}",
            arguments = listOf(
                navArgument("problem") {
                    type = AssetParamType()
                }
            )
        ) { entry ->
            val problem = entry.arguments?.getParcelable<ProblemEntity>("problem")
            ProblemDetailScreen(navController = navController, problem)
        }
    }
}