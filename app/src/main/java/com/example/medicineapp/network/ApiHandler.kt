package com.example.medicineapp.network
import com.example.medicineapp.util.ERROR_KEY
import com.example.medicineapp.util.MESSAGE_KEY
import com.example.medicineapp.util.SOMETHING_WRONG_HAPPENED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import okio.IOException
import org.json.JSONObject
import retrofit2.HttpException
import java.net.SocketTimeoutException

object ApiHandler {

    suspend inline fun <T> safeApiCall(crossinline responseFunction: suspend () -> T): Resource<T> {

        return try {
            withContext(Dispatchers.IO) {
                Resource.Success(responseFunction.invoke())
            }
        } catch (e: Exception) {

            withContext(Dispatchers.Main) {

                e.printStackTrace()
                val d = e
                when (e) {

                    is HttpException -> {
                        val body = e.response()?.errorBody()
                        Resource.Failure(false, e.code(), body, errorMsg = getErrorMessage(body))
                    }
                    is SocketTimeoutException -> Resource.Failure(
                        true,
                        null,
                        errorMsg = "Check your internet"
                    )
                    is IOException -> Resource.Failure(true, null, errorMsg = "Server failure!")

                    else -> Resource.Failure(true, null, errorMsg = "Server failure!")
                }
            }
        }
    }
    
    fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = responseBody?.string()?.let { JSONObject(it) }
            when {
                jsonObject?.has(MESSAGE_KEY) == true -> (jsonObject.getString(MESSAGE_KEY) ?: SOMETHING_WRONG_HAPPENED).let {
                    if (it.startsWith(prefix = "<")) {
                        SOMETHING_WRONG_HAPPENED
                    } else {
                        it
                    }
                }
                jsonObject?.has(ERROR_KEY) == true -> jsonObject.getString(ERROR_KEY)
                else -> (jsonObject?.getString(MESSAGE_KEY) ?: "Something went wrong").let {
                    if (it.startsWith(prefix = "<")) {
                        SOMETHING_WRONG_HAPPENED
                    } else {
                        it
                    }
                }
            }
        } catch (e: Exception) {
            SOMETHING_WRONG_HAPPENED
        }
    }
}