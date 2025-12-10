package kr.kimrasng.skills.study.hyungsub.data

import kr.kimrasng.skills.study.hyungsub.R

data class VideoItem(
    val thumbnail: Int,
    val title: String,
    val channelName: String,
    val channelProfile: Int,
    val viewCount: String,
    val uploadTime: String,
    val duration: String,
    val isLive: Boolean = false
)

val ContentData = listOf(
    VideoItem(
        thumbnail = R.drawable.video1,
        title = "조진웅 논란 레전드 ㅋㅋㅋ",
        channelName = "가재맨",
        channelProfile = R.drawable.hyungsub,
        viewCount = "13만회",
        uploadTime = "1일 전",
        duration = "23:15"
    ),
    VideoItem(
        thumbnail = R.drawable.video2,
        title = "쿠팡 개인정보 유출사건 ㅋㅋㅋㅋ",
        channelName = "가재맨",
        channelProfile = R.drawable.hyungsub,
        viewCount = "15만회",
        uploadTime = "4일 전",
        duration = "27:47"
    ),
    VideoItem(
        thumbnail = R.drawable.video3,
        title = "뻑가 복귀 레전드 ㅋㅋㅋㅋㅋㅋ",
        channelName = "가재맨",
        channelProfile = R.drawable.hyungsub,
        viewCount = "20만회",
        uploadTime = "6일 전",
        duration = "27:20"
    ),
    VideoItem(
        thumbnail = R.drawable.video4,
        title = "김어준 가재맨 샤라웃 레전드 ㅋㅋㅋㅋㅋ",
        channelName = "가재맨",
        channelProfile = R.drawable.hyungsub,
        viewCount = "19만회",
        uploadTime = "8일 전",
        duration = "26:2"
    ),
    VideoItem(
        thumbnail = R.drawable.video5,
        title = "엄마를 고발합니다...",
        channelName = "가재맨",
        channelProfile = R.drawable.hyungsub,
        viewCount = "25만회",
        uploadTime = "9일 전",
        duration = "25:37"
    ),
    VideoItem(
        thumbnail = R.drawable.video6,
        title = "영포티 레전드 섹백좌 최신 근황",
        channelName = "가재맨",
        channelProfile = R.drawable.hyungsub,
        viewCount = "14만회",
        uploadTime = "13일 전",
        duration = "24:48"
    )
)
