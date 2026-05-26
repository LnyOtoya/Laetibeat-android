package com.otimeum.laetibeat.ui.screens.home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.otimeum.laetibeat.data.model.Playlist
import com.otimeum.laetibeat.data.model.PlaylistViewMode
import com.otimeum.laetibeat.ui.components.GridPlaylistCard
import com.otimeum.laetibeat.ui.components.PlaylistCard
import com.otimeum.laetibeat.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    playlists: List<Playlist>,
    viewModel: MainViewModel,
    onPlaylistClick: (Playlist) -> Unit,
    onSettingsClick: () -> Unit
) {
    val viewMode = viewModel.userPreferences.playlistViewMode
    val gridColumns = viewModel.userPreferences.playlistGridColumns

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("我的歌单") },
                actions = {
                    // 视图切换按钮
                    IconButton(onClick = {
                        val newMode = if (viewMode == PlaylistViewMode.GRID) {
                            PlaylistViewMode.LIST
                        } else {
                            PlaylistViewMode.GRID
                        }
                        viewModel.setPlaylistViewMode(newMode)
                    }) {
                        Icon(
                            imageVector = if (viewMode == PlaylistViewMode.GRID) Icons.Default.ViewList else Icons.Default.GridView,
                            contentDescription = "切换视图"
                        )
                    }
                    // 设置按钮
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "设置"
                        )
                    }
                }
            )
        },
        content = { padding ->
            when (viewMode) {
                PlaylistViewMode.GRID -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(gridColumns),
                        modifier = Modifier.padding(padding),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            count = playlists.size,
                            key = { index -> playlists[index].id }
                        ) { index ->
                            val playlist = playlists[index]
                            GridPlaylistCard(playlist = playlist) {
                                onPlaylistClick(playlist)
                            }
                        }
                    }
                }
                PlaylistViewMode.LIST -> {
                    LazyColumn(
                        modifier = Modifier.padding(padding),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        items(playlists) { playlist ->
                            PlaylistCard(playlist = playlist) {
                                onPlaylistClick(playlist)
                            }
                        }
                    }
                }
            }
        }
    )
}