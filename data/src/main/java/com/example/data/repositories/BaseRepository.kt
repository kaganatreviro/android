package com.example.data.repositories

import com.example.core.either.AuthenticationError
import com.example.core.either.Either
import com.example.core.either.NetworkError
import com.example.data.utils.DataMapper
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.InterruptedIOException

internal fun <T> makeNetworkRequest(
    request: suspend () -> T
) =
    flow<Either<String, T>> {
        request().also {
            emit(Either.Right(value = it))
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        emit(Either.Left(value = exception.message ?: "Error Occurred!"))
    }

internal fun <T> makeNetworkRequestWithUnitReturnType(
    request: suspend () -> Response<T>
) = newMakeNetworkRequest(request = request) {
    Either.Right(Unit)
}

internal fun <T : DataMapper<S>, S> makeNetworkRequestWithMapping(
    request: suspend () -> Response<T>,
) = newMakeNetworkRequest(request = request) { body ->
    Either.Right(body.toDomain())
}

internal fun <T : DataMapper<S>, S> makeNetworkRequestToFetchList(
    request: suspend () -> Response<List<T>>
) = newMakeNetworkRequest(request) { body ->
    Either.Right(body.map { it.toDomain() })
}


private fun <T, S> newMakeNetworkRequest(
    request: suspend () -> Response<T>,
    successful: (T) -> Either.Right<S>
) = flow<Either<NetworkError, S>> {
    request().let {
        when {
            it.isSuccessful && it.body() != null -> {
                emit(successful.invoke(it.body()!!))
            }

            else -> {
                emit(
                    Either.Left(
                        NetworkError.AuthApi(
                            AuthenticationError(it.message(), it.code())
                        )
                    )
                )
            }
        }
    }
}.flowOn(Dispatchers.IO).catch { exception ->
    when (exception) {
        is InterruptedIOException -> {
            emit(Either.Left(NetworkError.Timeout))
        }

        else -> {
            val message = exception.localizedMessage ?: "Error Occurred!"
            emit(Either.Left(NetworkError.Api(message)))
        }
    }
}

private fun <T, S> test(
    request: suspend () -> Response<T>,
    successful: (T) -> Either.Right<S>
) = flow<Either<AppError, S>> {
    request().let {
        when {
            it.isSuccessful && it.body() != null -> {
                emit(successful.invoke(it.body()!!))
            }

            else -> {
                Json.decodeFromString<ApiErrorModel>(it.errorBody()?.string())
                emit(
//                    Either.Left(
//                        AppError.ApiError(it.errorBody())
//                    )
//                )
//                Json
            }
        }
    }
}.flowOn(Dispatchers.IO).catch { exception ->
    when (exception) {
//        is InterruptedIOException -> {
//            emit(Either.Left(NetworkError.Timeout))
//        }
//
//        else -> {
//            val message = exception.localizedMessage ?: "Error Occurred!"
//            emit(Either.Left(NetworkError.Api(message)))
//        }
    }
}

sealed class AppError {
    data class ApiError(val error: ApiErrorModel) : AppError()
    data class UnexpectedError(val message: String) : AppError()
}

data class ApiErrorModel(
    val error_code: String,
    val message: String,
    val code: Int
)

private inline fun <reified T> ResponseBody?.toApiError(): T? {
    return this?.string()?.let { Json.decodeFromString<ApiErrorModel>(it) }
}

private inline fun <reified T> ResponseBody?.toApiError2(): T? {
    return this?.string()?.let { fromJson<T>(it) }
}

internal inline fun <reified T> fromJson(value: String): T? {
    return Gson().fromJson(value, T::class.java)
//    return gson.adapter(T::class.java).fromJson(value)
}

