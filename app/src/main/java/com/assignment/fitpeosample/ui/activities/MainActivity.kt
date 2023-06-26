package com.assignment.fitpeosample.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.assignment.fitpeosample.data.Resource
import com.assignment.fitpeosample.data.model.SourcePhoto
import com.assignment.fitpeosample.ui.component.appSnackbar.appSnackBar
import com.assignment.fitpeosample.ui.component.appbarui.FitpeoAppToolBar
import com.assignment.fitpeosample.ui.component.appcards.PhotoCard
import com.assignment.fitpeosample.ui.component.progreesUI.SimpleCircularProgressComponent
import com.assignment.fitpeosample.ui.theme.FitpeoSampleTheme
import com.assignment.fitpeosample.utils.AppLog
import com.assignment.fitpeosample.viewmodel.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val viewModel: PhotoViewModel by lazy {
        ViewModelProvider(this)[PhotoViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitpeoSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldWithTopBar(photoViewModel =viewModel)
//                    Greeting("Android")
                    viewModel.getPhotos()
                }
            }
        }
        dataStateObserver()
    }

    fun dataStateObserver() {
//            viewModel.dataList.observe(this){
//                when (it) {
//                    is Resource.Success<*> ->  AppLog.d("Amit ","Main Success[data=${it.data}]")
//                    is Resource.Failure -> AppLog.d("Amit ","Main Error[exception=${it.message}]")
//                    is Resource.Loading<*> -> AppLog.d("Amit ","Main Loading")
//                }
//            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBar(photoViewModel: PhotoViewModel) {
    Scaffold(
        topBar = {
            FitpeoAppToolBar(title = "Fitpeo Sample" ,titleColor= Color.White)
        },
        content = { it
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 58.dp, 0.dp, 10.dp)
                    .background(Color.White),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppLog.d("MainActivity","Size list"+photoViewModel.response)
                if(photoViewModel.response.value is Resource.Success<*>){
                    AppLog.d("MainActivity","Success ----")
                    val photosList=    (photoViewModel.response.value as Resource.Success<List<SourcePhoto>>).data as List<SourcePhoto>
                    AppLog.d("MainActivity","Success ----${photosList.size}")
                    PhotoList(sourcePhoto = photosList)
                }
                else if (photoViewModel.response.value is Resource.Failure<*>){
//                    val photosList=    (photoViewModel.response.value as Resource.Failure<List<SourcePhoto>>).data as List<SourcePhoto>
                    val errorMessage=  (photoViewModel.response.value as Resource.Failure<List<SourcePhoto>>).message as String
                    AppLog.d("MainActivity","Fails ----"+errorMessage)
                    appSnackBar(errorMessage,"OK")
                }else{
                    AppLog.d("MainActivity","Loading ----")
                    SimpleCircularProgressComponent()
                }
            }
        }
    )
}

@Composable
fun PhotoList(sourcePhoto: List<SourcePhoto>) {
    AppLog.d("Main Activity","Size sourcePhoto fun "+sourcePhoto.size)
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        itemsIndexed(sourcePhoto) { _, item->
            PhotoCard(sourcePhoto = item , onCardItemClick = { item->
                AppLog.d("Main ","item -> ${item.albumID}")
                val detailActivityIntent= Intent(context, DetailsActivity::class.java)
                detailActivityIntent.putExtra("data",item)
                context.startActivity(detailActivityIntent)
            })
        }
    }
}

@Composable
@Preview
fun PreviewList(){
    val tempList=ArrayList<SourcePhoto>()
    tempList.add(SourcePhoto(1,1,"My Joke 1","",""))
    tempList.add(SourcePhoto(1,1,"My Joke 2","",""))
    tempList.add(SourcePhoto(1,1,"My Joke 3","",""))
    PhotoList(tempList)
}

