package com.assignment.fitpeosample.ui.component.appcards


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.assignment.fitpeosample.data.model.SourcePhoto


@Composable
fun PhotoCard(sourcePhoto: SourcePhoto, onCardItemClick: (item:SourcePhoto) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth().clickable{ onCardItemClick(sourcePhoto)},
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {

        Row(modifier = Modifier.padding(10.dp).fillMaxWidth()) {

            Image(
//                painter = painterResource(R.drawable.ic_launcher_background),
                painter =  rememberAsyncImagePainter(sourcePhoto.url),
                contentDescription = "Profile Image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .clip((CircleShape)))

            Column(
                modifier = Modifier.fillMaxWidth().align(alignment = Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center
//                horizontalAlignment = Alignment.CenterHorizontally
               )
            {
                sourcePhoto.title?.let {
                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally).fillMaxWidth().padding(start = 10.dp),
                        textAlign = TextAlign.Left,
                        text = it,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    )
                }

                }
            }
    }
}


@Composable
@Preview
fun PreviewPhotoCard(){
    PhotoCard(sourcePhoto = SourcePhoto(1,1,"This is my first joke","",""))
}

