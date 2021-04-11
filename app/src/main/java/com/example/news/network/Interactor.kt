package com.example.news.network

import com.example.news.utility.AppUtils
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * This class acts as an intermediary to filter the success and failures
 */
object Interactor {
    suspend fun <ResponseType : Any> handleRequest(requestFun: suspend () -> ResponseType): Output<ResponseType> {
        return if (AppUtils.isNetworkConnected()) {
            try {
                withContext(Dispatchers.Default) {
                    val data = requestFun.invoke()
                    if (data is CommonResponse) Output.Success(data)
                    else Output.Failure("Data received is not in specified format")
                }
            } catch (e: Throwable) {
                return when (e) {
                    is HttpException -> Output.Failure("Http Exception")
                    is CancellationException -> Output.Failure("Cancellation Exception")
                    else -> Output.Failure(e.toString())
                }
            }
        } else {
            Output.Failure("Internet Connection Error")
        }
    }
}