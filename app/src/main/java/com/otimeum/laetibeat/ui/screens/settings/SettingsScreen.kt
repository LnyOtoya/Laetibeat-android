package com.otimeum.laetibeat.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.otimeum.laetibeat.data.model.LibraryViewType
import com.otimeum.laetibeat.data.model.PlaylistViewMode
import com.otimeum.laetibeat.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("设置") }
            )
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier.padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 歌单视图模式设置
                item {
                    Card {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "歌单视图",
                                style = MaterialTheme.typography.titleMedium
                            )
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("宫格布局")
                                RadioButton(
                                    selected = viewModel.userPreferences.playlistViewMode == PlaylistViewMode.GRID,
                                    onClick = { viewModel.setPlaylistViewMode(PlaylistViewMode.GRID) }
                                )
                            }
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("列表布局")
                                RadioButton(
                                    selected = viewModel.userPreferences.playlistViewMode == PlaylistViewMode.LIST,
                                    onClick = { viewModel.setPlaylistViewMode(PlaylistViewMode.LIST) }
                                )
                            }
                        }
                    }
                }

                // 宫格列数设置
                if (viewModel.userPreferences.playlistViewMode == PlaylistViewMode.GRID) {
                    item {
                        Card {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "宫格列数",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                
                                Spacer(modifier = Modifier.height(8.dp))
                                
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("2 列")
                                    RadioButton(
                                        selected = viewModel.userPreferences.playlistGridColumns == 2,
                                        onClick = { viewModel.setPlaylistGridColumns(2) }
                                    )
                                }
                                
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("3 列")
                                    RadioButton(
                                        selected = viewModel.userPreferences.playlistGridColumns == 3,
                                        onClick = { viewModel.setPlaylistGridColumns(3) }
                                    )
                                }
                            }
                        }
                    }
                }

                // 音乐库视图设置
                item {
                    Card {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "音乐库显示",
                                style = MaterialTheme.typography.titleMedium
                            )
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            LibraryViewType.values().forEach { viewType ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        when (viewType) {
                                            LibraryViewType.ALBUMS -> "专辑"
                                            LibraryViewType.ARTISTS -> "艺术家"
                                            LibraryViewType.SONGS -> "歌曲"
                                        }
                                    )
                                    Checkbox(
                                        checked = viewModel.userPreferences.libraryViews.contains(viewType),
                                        onCheckedChange = { viewModel.toggleLibraryView(viewType) }
                                    )
                                }
                            }
                        }
                    }
                }

                // 统计页面显示
                item {
                    Card {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "显示统计页面",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Switch(
                                checked = viewModel.userPreferences.showStats,
                                onCheckedChange = { viewModel.toggleStatsVisibility() }
                            )
                        }
                    }
                }
            }
        }
    )
}
