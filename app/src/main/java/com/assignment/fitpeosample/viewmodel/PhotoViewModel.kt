package com.assignment.fitpeosample.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.assignment.fitpeosample.data.Resource
import com.assignment.fitpeosample.data.model.SourcePhoto
import com.assignment.fitpeosample.data.repository.FitpeoRepository
import com.assignment.fitpeosample.utils.AppLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val fitpeoRepository: FitpeoRepository): ViewModel() {

    val response: MutableState<Resource<List<SourcePhoto>>?> = mutableStateOf(Resource.Loading())

    fun getPhotos(){
        AppLog.d("Amit ","View model getPhotos()")
      viewModelScope.launch(Dispatchers.IO){
              AppLog.d("Amit ","View model getPhotos() repo non empty")
              fitpeoRepository.getPhotos().onEach(){
//                  mutableLiveData.value=it
                  response.value = it
              }.launchIn(viewModelScope)
      }
   }

}