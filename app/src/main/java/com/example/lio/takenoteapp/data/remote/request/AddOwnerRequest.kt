package com.example.lio.takenoteapp.data.remote.request

data class AddOwnerRequest(
    val owner: String,
    val noteID: String
)