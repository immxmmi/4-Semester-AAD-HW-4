package at.technikum.if20b231.newslist.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import at.technikum.if20b231.newslist.R
import at.technikum.if20b231.newslist.handler.HtmlText
import at.technikum.if20b231.newslist.modle.Page
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun SinglePageScreen(page: Page) {
    var imageUrl by remember { mutableStateOf(page.imageURL) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(title = { Text(stringResource(R.string.pageTitle)) })

       // Scaffold(topBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
            ) {
                // IMG
                // IMG
                GlideImage(
                    imageModel = "https://media-mbst-pub-ue1.s3.amazonaws.com/creatr-uploaded-images/2020-09/1fa32ec0-f76f-11ea-9d69-07d789e1644d",
                   // imageModel = page.imageURL,
                    contentScale = ContentScale.Fit,
                    circularReveal = CircularReveal(250),
                    modifier = Modifier.size(500.dp),
                    placeHolder = Icons.Filled.Image,
                    error = Icons.Filled.Error
                )
                // Box
                Column(
                    Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)
                ) {
                    page.title?.let {
                        Text(
                            text = "$it",
                            color = Color.DarkGray,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    page.author?.let {
                        Text(
                            text = "$it",
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    page.pubDate?.let {
                        Text(
                            text = "$it",
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
      //  }, content = {

            // HTML
            page.descriptor?.let {
                HtmlText(text = "$it")
            }

            //Full Story
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    Toast.makeText(context, "No Details", Toast.LENGTH_SHORT).show()
                }) {
                Text(text = "Full Story")
            }
     //   }
     //   )
    }
}

@Composable
fun ShowSinglePage(navController: NavController, page: Page) {
    SinglePageScreen(page)
}


