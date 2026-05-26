package com.otimeum.laetibeat.ui.screens.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.otimeum.laetibeat.data.model.Song
import com.otimeum.laetibeat.ui.components.SongListItem
import com.otimeum.laetibeat.ui.theme.DynamicThemeHelper

@Composable
fun PlayerScreen(song: Song) {
    // 直接使用当前主题的颜色方案，后续可以通过封面取色动态更新
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        colorScheme.primary.copy(alpha = 0.2f),
                        colorScheme.background
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 专辑封面占位
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(colorScheme.surfaceVariant, MaterialTheme.shapes.large)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = song.title, style = MaterialTheme.typography.headlineMedium)
            Text(text = song.artist, style = MaterialTheme.typography.titleLarge, color = colorScheme.onSurfaceVariant)

            song.userNote?.let {
                Card(
                    modifier = Modifier.padding(top = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = colorScheme.primaryContainer)
                ) {
                    Text(
                        text = it,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // 播放控制占位
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FilledTonalButton(onClick = { }) { Text("上一首") }
                Button(onClick = { }) { Text("播放/暂停") }
                FilledTonalButton(onClick = { }) { Text("下一首") }
            }
        }
    }
}
