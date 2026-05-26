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
import com.otimeum.laetibeat.ui.components.FilterChipSimple
import com.otimeum.laetibeat.viewmodel.LibraryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(viewModel: LibraryViewModel) {
    val selectedFilter = viewModel.selectedFilter
    val albums = viewModel.albums
    val artists = viewModel.artists

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
                                .clickable { viewModel.setFilter(LibraryViewModel.FilterType.MIXED) }
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
                    FilterChipSimple(
                        label = "专辑",
                        isSelected = selectedFilter == LibraryViewModel.FilterType.ALBUMS,
                        onClick = {
                            viewModel.setFilter(
                                if (selectedFilter == LibraryViewModel.FilterType.ALBUMS) {
                                    LibraryViewModel.FilterType.MIXED
                                } else {
                                    LibraryViewModel.FilterType.ALBUMS
                                }
                            )
                        }
                    )

                    // 艺人 Chip
                    FilterChipSimple(
                        label = "艺人",
                        isSelected = selectedFilter == LibraryViewModel.FilterType.ARTISTS,
                        onClick = {
                            viewModel.setFilter(
                                if (selectedFilter == LibraryViewModel.FilterType.ARTISTS) {
                                    LibraryViewModel.FilterType.MIXED
                                } else {
                                    LibraryViewModel.FilterType.ARTISTS
                                }
                            )
                        }
                    )
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
                                AlbumListItem(album = album)
                            }
                        }
                        LibraryViewModel.FilterType.ARTISTS -> {
                            items(artists) { artist ->
                                ArtistListItem(artist = artist)
                            }
                        }
                        LibraryViewModel.FilterType.MIXED -> {
                            // 混合显示：交替显示专辑和艺人
                            val mixedList = buildList {
                                addAll(albums.take((albums.size + artists.size) / 2))
                                addAll(artists.take((albums.size + artists.size) / 2))
                            }
                            items(mixedList) { item ->
                                when (item) {
                                    is Album -> AlbumListItem(album = item)
                                    is Artist -> ArtistListItem(artist = item)
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
fun AlbumListItem(album: Album) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 专辑封面占位
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.shapes.small)
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
            // 艺人头像占位
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.shapes.small)
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
