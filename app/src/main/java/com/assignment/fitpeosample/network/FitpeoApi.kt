package com.assignment.fitpeosample.network

import com.assignment.fitpeosample.data.model.SourcePhoto
import com.assignment.fitpeosample.utils.PATH_PHOTO
import retrofit2.http.GET

interface FitpeoApi {

    @GET(PATH_PHOTO)
    suspend fun getPhotos(): List<SourcePhoto>

}