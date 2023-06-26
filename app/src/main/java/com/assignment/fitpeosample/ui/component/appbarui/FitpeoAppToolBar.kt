package com.assignment.fitpeosample.ui.component.appbarui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.assignment.fitpeosample.ui.theme.FitpeoSampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitpeoAppToolBar(title:String,
                         isHomeEnable:Boolean=false,
                         toolbarColor: Color = MaterialTheme.colorScheme.primary,
                         navIconColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
                         titleColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
                         onToolbarNavIconClick: () -> Unit = {}){
    Column {
        TopAppBar(
            title = {
                Text(title, color = titleColor)
            },
            modifier = Modifier,
//            elevation = 4.dp,
            colors = TopAppBarDefaults.largeTopAppBarColors(toolbarColor),
//            backgroundColor = MaterialTheme.colors.primarySurface,
            navigationIcon = {
                if(isHomeEnable){
                    IconButton(onClick = {/* Do Something*/
                        onToolbarNavIconClick()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon", modifier = Modifier,navIconColor)

                    }
                }
            }
//            , actions = {
//                IconButton(onClick = {/* Do Something*/ }) {
//                    Icon(Icons.Filled.Share, null)
//                }
//                IconButton(onClick = { showDialog.value = true }) {
//                    Icon(Icons.Filled.Settings, null)
//                }
//            }
        )

//        Text("Hello World")

    }
}

@Preview
@Composable
fun TestSampleAppToolBarPreview(){
    FitpeoSampleTheme() {
        FitpeoAppToolBar("Android New")
    }
}