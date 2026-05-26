package com.otimeum.laetibeat.data.model

data class Song(
    val id: String,
    val title: String,
    val artist: String,
    val album: String? = null,
    val duration: Long = 0,
    val coverUri: String? = null,
    val userNote: String? = null // 用户对这首歌的想法/备注
)

data class Playlist(
    val id: String,
    val name: String,
    val description: String? = null, // 歌单的创建想法
    val coverUri: String? = null,
    val songs: List<Song> = emptyList()
)

enum class LibraryViewType {
    ALBUMS,
    ARTISTS,
    SONGS
}

enum class PlaylistViewMode {
    GRID,  // 宫格布局
    LIST   // 列表布局
}

data class UserPreferences(
    val libraryViews: Set<LibraryViewType> = setOf(LibraryViewType.ALBUMS, LibraryViewType.ARTISTS),
    val showStats: Boolean = false,
    val playlistViewMode: PlaylistViewMode = PlaylistViewMode.GRID,
    val playlistGridColumns: Int = 2  // 2 或 3
)
