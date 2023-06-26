package com.assignment.fitpeosample.ui.component.appSnackbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignment.fitpeosample.ui.theme.FitpeoSampleTheme

@Composable
fun appSnackBar(title:String,label:String){
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
//                .padding(bottom = 60.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var snackbarVisibleState = remember {
                mutableStateOf(true)
            }
            if (snackbarVisibleState.value) {
                Snackbar(
                    action = {
                        Button(onClick = {
                            snackbarVisibleState.value=false
                        }) {
                            Text(label)
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text =title)
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun appSnackBarNewPreview(){
    FitpeoSampleTheme() {
        appSnackBar("This is test","Ok")
    }
}
