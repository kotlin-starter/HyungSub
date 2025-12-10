package kr.kimrasng.skills.study.hyungsub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import kr.kimrasng.skills.study.hyungsub.data.ContentData
import kr.kimrasng.skills.study.hyungsub.data.VideoItem
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

    val isDark = isSystemInDarkTheme()

    val bc = if (isDark)
        Color(0x0000)
    else
        Color(0xFFFF)

    NavigationSuiteScaffold(
        modifier = Modifier.background(bc),
        navigationSuiteItems = {
            AppDestinations.entries.forEach { it ->

                val iconModifier = if (it.label == "") Modifier.size(50.dp) else Modifier

                item(
                    icon = {
                        Icon(
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
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Header()
            Finder()
            ContentList(ContentData)
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

var FindList = arrayOf("전체", "새로운 맞춤 동영상", "팟캐스트", "음악", "믹스", "라이브", "최근에 업로드된 영상")

@Composable
fun Header() {
    val isDark = isSystemInDarkTheme()

    val logo = if (isDark)
        R.drawable.youtube_white
    else
        R.drawable.youtube_black

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(WindowInsets.systemBars.asPaddingValues())
            .padding(horizontal = 12.dp, vertical = 1.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = logo),
                contentDescription = "YouTube Logo",
                modifier = Modifier.height(22.dp)
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,

        ) {

            Icon(
                painter = painterResource(id = R.drawable.cast),
                contentDescription = "Cast",
                modifier = Modifier
                    .size(40.dp)
                    .padding(horizontal = 6.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = "Bell",
                modifier = Modifier
                    .size(40.dp)
                    .padding(horizontal = 6.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search",
                modifier = Modifier
                    .size(40.dp)
                    .padding(horizontal = 6.dp)
            )

        }
    }
}

@Composable
fun Finder() {
    var selectIndex by rememberSaveable { mutableStateOf(0) }
    val isDark = isSystemInDarkTheme()

    val selectedBg = if (isDark) Color.White else Color.Black
    val unselectedBg = if (isDark) Color(0xFF3A3A3A) else Color(0xFFE5E5E5)

    val selectedText = if (isDark) Color.Black else Color.White
    val unselectedText = if (isDark) Color.White else Color.Black

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        item {
            Box(
                modifier = Modifier
                    .background(
                        if (isDark) Color(0xFF3A3A3A) else Color(0xFFE5E5E5),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(6.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.finde),
                    contentDescription = "Finder",
                    modifier = Modifier.size(22.dp),
                    tint = if (isDark) Color.White else Color.Black
                )
            }
        }

        items(FindList.size) { index ->
            val isSelected = selectIndex == index

            Box(
                modifier = Modifier
                    .background(
                        if (isSelected) selectedBg else unselectedBg,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { selectIndex = index }
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Text(
                    text = FindList[index],
                    color = if (isSelected) selectedText else unselectedText,
                    fontSize = 14.sp
                )
            }
        }
    }
}



@Composable
fun ContentList(videos: List<VideoItem>) {
    LazyColumn {
        items(videos.size) { index ->
            Content(video = videos[index])
        }
    }
}

@Composable
fun Content(video: VideoItem) {
    Column {
        Box {
            Image(
                painter = painterResource(id = video.thumbnail),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )
        }
    }

    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.Top
    ){
        Image(
            painter = painterResource(id = video.channelProfile),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp)
        )
        Column {
            Text(video.title, fontWeight = FontWeight.Bold)
            Text("${video.channelName} · ${video.viewCount} · ${video.uploadTime}",
                color = Color.Gray, fontSize = 12.sp)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HyungSubTheme {
    }
}