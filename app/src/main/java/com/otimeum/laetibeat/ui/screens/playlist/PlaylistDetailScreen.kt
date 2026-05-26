package com.otimeum.laetibeat.ui.screens.playlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.otimeum.laetibeat.data.model.Playlist
import com.otimeum.laetibeat.ui.components.SongListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistDetailScreen(playlist: Playlist, onSongClick: (com.otimeum.laetibeat.data.model.Song) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(playlist.name) }
            )
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier.padding(padding),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    playlist.description?.let {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                        ) {
                            Text(
                                text = "💭 $it",
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
                items(playlist.songs) { song ->
                    SongListItem(song = song, modifier = Modifier.fillMaxWidth()) {
                        onSongClick(song)
                    }
                }
            }
        }
    )
}
