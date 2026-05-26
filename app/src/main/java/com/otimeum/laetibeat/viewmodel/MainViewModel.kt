package com.otimeum.laetibeat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.otimeum.laetibeat.data.model.LibraryViewType
import com.otimeum.laetibeat.data.model.Playlist
import com.otimeum.laetibeat.data.model.PlaylistViewMode
import com.otimeum.laetibeat.data.model.Song
import com.otimeum.laetibeat.data.model.UserPreferences

class MainViewModel : ViewModel() {

    var userPreferences by mutableStateOf(UserPreferences())
        private set

    // 模拟数据
    val playlists = listOf(
        Playlist(
            id = "1",
            name = "心情舒畅时刻",
            description = "收集那些听了后心情很舒畅的曲子，适合放松时听",
            songs = listOf(
                Song("101", "晴天", "周杰伦", userNote = "下雨天听很有感觉"),
                Song("102", "起风了", "买辣椒也用券", userNote = "每次听都想起夏天"),
                Song("103", "平凡之路", "朴树", userNote = "人生感悟满满"),
                Song("104", "成都", "赵雷")
            )
        ),
        Playlist(
            id = "2",
            name = "深夜emo专用",
            description = "夜深人静时的情绪宣泄，每一首都戳心",
            songs = listOf(
                Song("201", "演员", "薛之谦", userNote = "太扎心了"),
                Song("202", "消愁", "毛不易", userNote = "一杯敬自由，一杯敬死亡"),
                Song("203", "南山南", "马頔"),
                Song("204", "董小姐", "宋冬野", userNote = "民谣经典"),
                Song("205", "斑马斑马", "宋冬野")
            )
        ),
        Playlist(
            id = "3",
            name = "运动健身必备",
            description = "节奏感强，让人充满动力！",
            songs = listOf(
                Song("301", "Uptown Funk", "Bruno Mars", userNote = "节奏超棒"),
                Song("302", "Shape of You", "Ed Sheeran"),
                Song("303", "Believer", "Imagine Dragons", userNote = "燃爆了"),
                Song("304", "Stronger", "Kelly Clarkson")
            )
        ),
        Playlist(
            id = "4",
            name = "工作专注BGM",
            description = "帮助集中注意力，提高工作效率的背景音乐",
            songs = listOf(
                Song("401", "Weightless", "Marconi Union", userNote = "最放松的音乐"),
                Song("402", "Experience", "Ludovico Einaudi"),
                Song("403", "Nuvole Bianche", "Ludovico Einaudi", userNote = "钢琴曲经典"),
                Song("404", "Comptine d'un autre été", "Yann Tiersen"),
                Song("405", "River Flows in You", "Yiruma"),
                Song("406", "Kiss The Rain", "Yiruma")
            )
        ),
        Playlist(
            id = "5",
            name = "怀旧金曲",
            description = "那些年我们一起听过的歌，满满的回忆",
            songs = listOf(
                Song("501", "海阔天空", "Beyond", userNote = "永远的经典"),
                Song("502", "朋友", "周华健"),
                Song("503", "吻别", "张学友", userNote = "90年代回忆"),
                Song("504", "红日", "李克勤"),
                Song("505", "千千阙歌", "陈慧娴")
            )
        ),
        Playlist(
            id = "6",
            name = "咖啡时光",
            description = "悠闲的下午，一杯咖啡，一首好歌",
            songs = listOf(
                Song("601", "Fly Me to the Moon", "Frank Sinatra", userNote = "爵士经典"),
                Song("602", "What a Wonderful World", "Louis Armstrong"),
                Song("603", "La Vie En Rose", "Édith Piaf"),
                Song("604", "Moon River", "Audrey Hepburn")
            )
        )
    )

    fun toggleLibraryView(viewType: LibraryViewType) {
        val current = userPreferences.libraryViews.toMutableSet()
        if (current.contains(viewType)) {
            current.remove(viewType)
        } else {
            current.add(viewType)
        }
        userPreferences = userPreferences.copy(libraryViews = current)
    }

    fun toggleStatsVisibility() {
        userPreferences = userPreferences.copy(showStats = !userPreferences.showStats)
    }

    fun setPlaylistViewMode(mode: PlaylistViewMode) {
        userPreferences = userPreferences.copy(playlistViewMode = mode)
    }

    fun setPlaylistGridColumns(columns: Int) {
        if (columns in 2..3) {
            userPreferences = userPreferences.copy(playlistGridColumns = columns)
        }
    }
}
