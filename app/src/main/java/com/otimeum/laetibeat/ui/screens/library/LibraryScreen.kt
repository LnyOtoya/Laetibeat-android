package com.otimeum.laetibeat.ui.screens.library

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.otimeum.laetibeat.data.model.Album
import com.otimeum.laetibeat.data.model.Artist
import com.otimeum.laetibeat.data.model.LibraryViewType
import com.otimeum.laetibeat.data.model.Song
import com.otimeum.laetibeat.ui.components.FilterChipSimple
import com.otimeum.laetibeat.ui.components.SongListItem
import com.otimeum.laetibeat.viewmodel.LibraryViewModel
import com.otimeum.laetibeat.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    libraryViewModel: LibraryViewModel,
    mainViewModel: MainViewModel,
    onAlbumClick: (Album) -> Unit = {}
) {
    val selectedFilter = libraryViewModel.selectedFilter
    val albums = libraryViewModel.albums
    val artists = libraryViewModel.artists
    val songs = libraryViewModel.songs
    val enabledViews = mainViewModel.userPreferences.libraryViews

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("音乐库") }
            )
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                // 过滤 Chips 区域
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 清除按钮（只在有选中项时显示）
                    AnimatedVisibility(
                        visible = selectedFilter != LibraryViewModel.FilterType.MIXED,
                        enter = fadeIn(animationSpec = tween(200)),
                        exit = fadeOut(animationSpec = tween(200))
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .clickable { libraryViewModel.setFilter(LibraryViewModel.FilterType.MIXED) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "清除筛选",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    // 专辑 Chip
                    if (enabledViews.contains(LibraryViewType.ALBUMS)) {
                        FilterChipSimple(
                            label = "专辑",
                            isSelected = selectedFilter == LibraryViewModel.FilterType.ALBUMS,
                            onClick = {
                                libraryViewModel.setFilter(
                                    if (selectedFilter == LibraryViewModel.FilterType.ALBUMS) {
                                        LibraryViewModel.FilterType.MIXED
                                    } else {
                                        LibraryViewModel.FilterType.ALBUMS
                                    }
                                )
                            }
                        )
                    }

                    // 艺人 Chip
                    if (enabledViews.contains(LibraryViewType.ARTISTS)) {
                        FilterChipSimple(
                            label = "艺人",
                            isSelected = selectedFilter == LibraryViewModel.FilterType.ARTISTS,
                            onClick = {
                                libraryViewModel.setFilter(
                                    if (selectedFilter == LibraryViewModel.FilterType.ARTISTS) {
                                        LibraryViewModel.FilterType.MIXED
                                    } else {
                                        LibraryViewModel.FilterType.ARTISTS
                                    }
                                )
                            }
                        )
                    }

                    // 歌曲 Chip
                    if (enabledViews.contains(LibraryViewType.SONGS)) {
                        FilterChipSimple(
                            label = "歌曲",
                            isSelected = selectedFilter == LibraryViewModel.FilterType.SONGS,
                            onClick = {
                                libraryViewModel.setFilter(
                                    if (selectedFilter == LibraryViewModel.FilterType.SONGS) {
                                        LibraryViewModel.FilterType.MIXED
                                    } else {
                                        LibraryViewModel.FilterType.SONGS
                                    }
                                )
                            }
                        )
                    }
                }

                // 内容列表
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    when (selectedFilter) {
                        LibraryViewModel.FilterType.ALBUMS -> {
                            items(albums) { album ->
                                AlbumListItem(album = album, onClick = { onAlbumClick(album) })
                            }
                        }
                        LibraryViewModel.FilterType.ARTISTS -> {
                            items(artists) { artist ->
                                ArtistListItem(artist = artist)
                            }
                        }
                        LibraryViewModel.FilterType.SONGS -> {
                            items(songs) { song ->
                                SongListItem(song = song, modifier = Modifier.fillMaxWidth())
                            }
                        }
                        LibraryViewModel.FilterType.MIXED -> {
                            // 混合显示：只显示启用的视图类型
                            val mixedList = buildList {
                                if (enabledViews.contains(LibraryViewType.ALBUMS)) {
                                    addAll(albums.take(3))
                                }
                                if (enabledViews.contains(LibraryViewType.ARTISTS)) {
                                    addAll(artists.take(3))
                                }
                                if (enabledViews.contains(LibraryViewType.SONGS)) {
                                    addAll(songs.take(3))
                                }
                            }
                            items(mixedList) { item ->
                                when (item) {
                                    is Album -> AlbumListItem(album = item, onClick = { onAlbumClick(item) })
                                    is Artist -> ArtistListItem(artist = item)
                                    is Song -> SongListItem(song = item, modifier = Modifier.fillMaxWidth())
                                    else -> {}
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun AlbumListItem(album: Album, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 专辑封面占位 - 圆角矩形
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = album.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = album.artist,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                album.year?.let {
                    Text(
                        text = "$it · ${album.songCount} 首歌曲",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtistListItem(artist: Artist) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 艺人头像占位 - 圆形
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = artist.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${artist.albumCount} 张专辑",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
