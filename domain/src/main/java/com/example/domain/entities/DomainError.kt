package com.example.domain.entities

sealed class DomainError : Exception() {

    object UsersNotFound : DomainError()
    data class Unknown(
        override val message: String,
        override val cause: Throwable?
    ) : DomainError()

    data class ServerConnectionError(
        override val message: String,
        override val cause: Throwable?
    ) : DomainError()
    data class LocalTransactionError(
        override val message: String,
        override val cause: Throwable?
    ) : DomainError()

    companion object {
        fun mapError(error: Exception): DomainError =
            (error as? DomainError) ?: Unknown(error.message ?: "", error.cause)
    }
}

suspend fun <T> safeCall(action: suspend () -> T) = try {
    action.invoke()
} catch (error: Exception) {
    throw DomainError.mapError(error)
}