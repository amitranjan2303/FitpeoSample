package com.assignment.fitpeosample.data.repository

import com.assignment.fitpeosample.data.Resource
import com.assignment.fitpeosample.data.model.SourcePhoto
import com.assignment.fitpeosample.network.FitpeoApi
import com.assignment.fitpeosample.utils.AppLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FitpeoRepository @Inject constructor(private val fitpeoApi: FitpeoApi) {

   suspend fun getPhotos() : Flow<Resource<List<SourcePhoto>>> =flow {
      emit(Resource.Loading())
      try {
         val genreResult = fitpeoApi.getPhotos()
         emit(Resource.Success(genreResult))

      } catch (e: Exception) {
         if(!e.message.isNullOrEmpty()){
            AppLog.d("Repository ","Fails ----"+e.message.toString())
            emit(Resource.Failure(message = e.message.toString()))
         }else{
            emit(Resource.Failure(message = "Something went wrong "))
         }

      }
   }
}