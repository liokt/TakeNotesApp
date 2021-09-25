package com.example.lio.takenoteapp.data.remote

import com.example.lio.takenoteapp.data.local.entities.Note
import com.example.lio.takenoteapp.data.remote.request.AccountRequest
import com.example.lio.takenoteapp.data.remote.request.AddOwnerRequest
import com.example.lio.takenoteapp.data.remote.request.DeleteNoteRequest
import com.example.lio.takenoteapp.data.remote.response.SimpleResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NoteApi {

    @POST("/register")
    suspend fun register(
        @Body registerRequest: AccountRequest
    ): Response<SimpleResponse>

    @POST("/login")
    suspend fun login(
        @Body loginRequest: AccountRequest
    ): Response<SimpleResponse>

    @POST("/addnote")
    suspend fun addNote(
        @Body note: Note
    ): Response<ResponseBody>

    @POST("/deletenote")
    suspend fun deleteNote(
        @Body deleteNoteRequest: DeleteNoteRequest
    ): Response<ResponseBody>

    @POST("/addownernote")
    suspend fun addOwnerToNote(
        @Body addOwnerRequest: AddOwnerRequest
    ): Response<SimpleResponse>

    @GET("/getnotes")
    suspend fun getNotes(): Response<List<Note>>

}