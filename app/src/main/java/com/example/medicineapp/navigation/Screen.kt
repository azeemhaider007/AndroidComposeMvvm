package com.example.medicineapp.navigation

sealed class Screen(val route:String){
   object LoginScreen : Screen(route = "Login")
    object MedicineListScreen : Screen(route = "MedicineList")
     object ProblemDetailScreen : Screen(route = "ProblemDetail")


    fun withArgs(vararg args:String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}