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
            description = "收集那些听了后心情很舒畅的曲子",
            songs = listOf(
                Song("101", "晴天", "周杰伦", userNote = "下雨天听很有感觉"),
                Song("102", "起风了", "买辣椒也用券")
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
