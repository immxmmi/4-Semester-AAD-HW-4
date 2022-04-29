package at.technikum.if20b231.newslist.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import at.technikum.if20b231.newslist.R
import at.technikum.if20b231.newslist.modle.Page
import at.technikum.if20b231.newslist.ui.theme.NewsListTheme
import at.technikum.if20b231.newslist.viewmodel.NewsListViewModel

@Composable
fun PageItem(page: Page, navController: NavController) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .border(0.02.dp, color = Color.Black)
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),

        ) {

        Text(
            modifier = Modifier.clickable(enabled = true) {
                if (page == null) {
                    Toast.makeText(context, "No Details", Toast.LENGTH_SHORT).show()
                } else {

                    navController.navigate(
                        route = Screen.PageDetail.withArgs(
                            page.id.orEmpty(),
                            page.title.orEmpty(),
                            page.author.orEmpty(),
                            page.descriptor.orEmpty().replace("/", "\\"),
                            page.pubDate.toString(),
                            page.imageURL.orEmpty().replace("/", "\\"),
                            page.articleURL.toString().replace("/", "\\")
                        )
                    )
                }
            },
            text = "${page.title}",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@Composable
fun ShowListOfPages(navController: NavController, model: NewsListViewModel) {

    val data by model.load.observeAsState()
    var page = data ?: emptyList()
    val context = LocalContext.current
    NewsListTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column {

                TopAppBar(
                    title = { Text(stringResource(R.string.app_title)) })
                Button(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .border(0.02.dp, color = Color.Black)
                        .padding(8.dp),

                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2D7637)),
                    onClick = {
                       model.reload()
                    }

                ) {
                    Text(
                        color = Color.White,
                        fontFamily= FontFamily.Monospace,
                        text = stringResource(R.string.reload)
                    )

                }
                LazyColumn {
                    items(items = page) { page ->
                        PageItem(page = page, navController)
                    }
                }
            }
        }
    }
}



