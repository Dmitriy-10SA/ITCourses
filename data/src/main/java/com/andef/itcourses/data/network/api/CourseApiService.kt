package com.andef.itcourses.data.network.api

import com.andef.itcourses.data.network.dto.CoursesListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseApiService {
    @GET("uc")
    suspend fun loadCourses(
        @Query("id") id: String = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q",
        @Query("export") export: String = "download"
    ): Response<CoursesListDto>
}