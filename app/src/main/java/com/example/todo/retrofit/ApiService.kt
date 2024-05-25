package com.example.todo.retrofit

import com.example.todo.models.Project
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("/projects/")
    suspend fun getProjects(): List<Project>

    @POST("/projects/")
    suspend fun createProject(@Body project: Project): Project

    @PUT("/projects/{id}")
    suspend fun updateProject(@Path("id") id: Int, @Body project: Project): Project

    @DELETE("/projects/{id}")
    suspend fun deleteProject(@Path("id") id: Int)
}