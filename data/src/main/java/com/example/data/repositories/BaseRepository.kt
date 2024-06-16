package com.example.data.repositories

import com.example.core.either.AuthenticationError
import com.example.core.either.Either
import com.example.core.either.NetworkError
import com.example.data.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

