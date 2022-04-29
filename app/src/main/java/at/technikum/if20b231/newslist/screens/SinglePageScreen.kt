package at.technikum.if20b231.newslist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import at.technikum.if20b231.newslist.R
import at.technikum.if20b231.newslist.modle.Page


@Composable
fun SinglePageScreen(page: Page) {
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
            page.pubDate?.let {
                Text(
                    text = "Publication date: ",
                    color = Color.Black,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
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

            page.id?.let {
                Text(
                    text = "Unique identifier:",
                    color = Color.Black,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            }
            page.id?.let {
                Text(
                    text = "$it",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            page.title?.let {
                Text(
                    text = "Title:",
                    color = Color.Black,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            }

            page.title?.let {
                Text(
                    text = "$it",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            page.author?.let {
                Text(
                    text = "Author:",
                    color = Color.Black,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
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

            page.imageURL?.let {
                Text(
                    text = "Image URL:",
                    color = Color.Black,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            }

            page.imageURL?.let {
                Text(
                    text = "$it",
                    color = Color.Blue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textDecoration = TextDecoration.Underline
                )
            }

            page.articleURL?.let {
                Text(
                    text = "Full article link:",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                )
            }

            page.articleURL?.let {
                Text(
                    text = "$it",
                    color = Color.Blue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Composable
fun ShowSinglePage(navController: NavController, page: Page) {
    SinglePageScreen(page)
}