package com.assignment.fitpeosample.ui.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.assignment.fitpeosample.data.model.SourcePhoto
import com.assignment.fitpeosample.ui.component.appbarui.FitpeoAppToolBar
import com.assignment.fitpeosample.ui.component.appcards.DetailInfoCard
import com.assignment.fitpeosample.ui.theme.FitpeoSampleTheme

class DetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitpeoSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                       val data:SourcePhoto?= intent.getParcelableExtra("data", SourcePhoto::class.java)
                        ScaffoldWithTopBar(data)
                    } else {
                       val data= intent.getParcelableExtra<SourcePhoto>("data")
                        ScaffoldWithTopBar(data)
                    }
//                    FitpeoAppToolBar("Android New",titleColor= Color.White)

                }
            }
        }
    }
}

@Composable
fun DetailsContent(sourcePhoto: SourcePhoto?){
   DetailInfoCard(sourcePhoto = sourcePhoto)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBar(sourcePhoto: SourcePhoto?) {
    Scaffold(
        topBar = {
            FitpeoAppToolBar(title = "Fitpeo Sample" ,titleColor= Color.White)
        },
        content = { it
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 200.dp, 0.dp, 10.dp)
                    .background(Color.White),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DetailsContent(sourcePhoto = sourcePhoto)
            }
        }
    )
}
