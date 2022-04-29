package at.technikum.if20b231.newslist.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import at.technikum.if20b231.newslist.R
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
        TopAppBar(
            title = { Text(stringResource(R.string.pageTitle)) })

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
        ) {
            // IMG
            GlideImage(
                imageModel = imageUrl,
                contentScale = ContentScale.Fit,
                circularReveal = CircularReveal(400),
                modifier = Modifier.size(100.dp),
                placeHolder = Icons.Filled.Image,
                error = Icons.Filled.Error
            )


            // Box
            page.title?.let {
                Text(
                    text = "$it",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            page.author?.let {
                Text(
                    text = "$it",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            page.pubDate?.let {
                Text(
                    text = "$it",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }


            // HTML
            page.descriptor?.let {

                Text(
                    text = "Description:",
                    color = Color.Black,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            }
            page.descriptor?.let {
                Text(
                    text = "$it",
                    color = Color.Black,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Normal
                )
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
        }
    }
}

@Composable
fun ShowSinglePage(navController: NavController, page: Page) {
    SinglePageScreen(page)
}


