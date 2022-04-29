package at.technikum.if20b231.newslist.screens

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import at.technikum.if20b231.newslist.R
import at.technikum.if20b231.newslist.viewmodel.NewsListViewModel

@Composable
fun SettingsScreen(navController : NavController, model : NewsListViewModel) {
    val text by model.text.observeAsState()
    var newText by remember { mutableStateOf(text) }

    BackHandler {
      //  model.updateText(newText ?: "")
        navController.navigateUp()
    }

    Column {
        TopAppBar(title = { stringResource(R.string.setings_menu_title) })
        TextField(
            value = newText ?: "",
            onValueChange = {
                newText = it
            },
            label = { Text("Text") },
            textStyle = TextStyle(fontSize = 21.sp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth())
    }
}


@Composable
fun BackHandler(enabled: Boolean = true, onBack: () -> Unit) {
    //https://dev.to/pawegio/handling-back-presses-in-jetpack-compose-50d5
    val currentOnBack by rememberUpdatedState(onBack)
    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                currentOnBack()
            }
        }
    }
    SideEffect {
        backCallback.isEnabled = enabled
    }
    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner"
    }.onBackPressedDispatcher
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, backDispatcher) {
        backDispatcher.addCallback(lifecycleOwner, backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}
