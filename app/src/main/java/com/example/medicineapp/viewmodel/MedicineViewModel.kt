package com.example.medicineapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.medicineapp.db.ProblemDao
import com.example.medicineapp.db.entities.ProblemEntity
import com.example.medicineapp.network.ApiHandler
import com.example.medicineapp.network.Resource
import com.example.medicineapp.network.RetrofitInstance
import com.example.medicineapp.response.Problem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(private val problemDao: ProblemDao): ViewModel() {
    private val apiService = RetrofitInstance.api

    val loading = MutableLiveData<Boolean>()

    val problems: LiveData<List<ProblemEntity>>
        get() = problemDao.getAllProblem()


    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    init {

        viewModelScope.launch {
            loading.postValue(true)
            val response = ApiHandler.safeApiCall {
                apiService.getDiseases()
            }

            when (response) {

                is Resource.Success -> {
                    loading.postValue(false)
                    problemDao.deleteAll()
                    val problems = response.value.problems.map {
                        Problem.mapData(it)
                    }
                    problemDao.deleteAll()
                    problemDao.insertProblems(problems)
                  //  _problems.value = response.value.problems
                }

                is Resource.Failure -> {
                    loading.postValue(false)
                    _error.value = response.errorMsg
                }
            }

        }
    }

}