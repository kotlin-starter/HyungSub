package kr.kimrasng.skills.study.hyungsub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import kr.kimrasng.skills.study.hyungsub.ui.theme.HyungSubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HyungSubTheme {
                HyungSubApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun HyungSubApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { it ->

                val iconModifier = if (it.label == "") Modifier.size(32.dp) else Modifier

                item(
                    icon = {
                        Icon (
                            painter = painterResource(id = it.icon),
                            contentDescription = it.label.takeIf { it.isNotEmpty() },
                            modifier = iconModifier
                        )
                    },
                    label = it.label.takeIf { it.isNotEmpty() }?.let { { Text(it) } },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )

            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Greeting(
                name = "Android",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("홈", R.drawable.home),
    SHORTS("Shorts", R.drawable.shorts),
    ADD("", R.drawable.add),
    SUBSCRIBE("구독", R.drawable.subscriptions),
    PAGES("내 페이지", R.drawable.profile),

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HyungSubTheme {
        Greeting("Android")
    }
}