package com.otimeum.laetibeat.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.otimeum.laetibeat.data.model.Playlist

@Composable
fun GridPlaylistCard(
    playlist: Playlist,
    onClick: () -> Unit
) {
    // 为每个歌单生成一个固定的随机形状（基于 ID）
    val shapeType = remember(playlist.id) { getRandomShape() }
    val shapeColor = MaterialTheme.colorScheme.primaryContainer

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // M3E 形状容器 - 直接作为框架
        Box(
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            M3EShape(
                shapeType = shapeType,
                size = 120.dp,
                color = shapeColor
            )
            
            // 如果有封面，可以在这里显示（暂时用占位符）
            if (playlist.coverUri != null) {
                // TODO: 使用 Coil 加载图片并裁剪为形状
            }
        }

        // 歌单名称
        Text(
            text = playlist.name,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2,
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
        )

        // 歌曲数量
        Text(
            text = "${playlist.songs.size} 首",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
