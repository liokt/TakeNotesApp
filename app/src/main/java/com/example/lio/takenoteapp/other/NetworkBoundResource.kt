package com.example.lio.takenoteapp.other

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>, //here we define how we get the data form our database
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit, //we get the response from the fetch function
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {

    emit(Resource.loading(null))
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.loading(data))

        try {
            val fetchedResult = fetch()
            saveFetchResult(fetchedResult)
            query().map { Resource.success(it) } //here we get the data we already save in our database
        } catch (t: Throwable) {
            onFetchFailed(t)
            query().map {
                Resource.error("Couldn't reach server. It might be down", it)
            }
        }
    } else {
        query().map { Resource.success(it) }
    }
    emitAll(flow)
}