package com.otimeum.laetibeat.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
    val shapeType = getShapeBySongCount(playlist.songs.size)
    val shapeColor = MaterialTheme.colorScheme.primaryContainer

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = androidx.compose.foundation.shape.GenericShape { size, _ ->
            // 根据形状类型创建对应的 Path
            when (shapeType) {
                M3EShapeType.CIRCLE -> {
                    addOval(androidx.compose.ui.geometry.Rect(0f, 0f, size.width, size.height))
                }
                M3EShapeType.SQUARE -> {
                    val cornerRadius = minOf(size.width, size.height) * 0.16f
                    addRoundRect(
                        androidx.compose.ui.geometry.RoundRect(
                            rect = androidx.compose.ui.geometry.Rect(0f, 0f, size.width, size.height),
                            topLeft = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
                            topRight = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
                            bottomLeft = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
                            bottomRight = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius)
                        )
                    )
                }
                M3EShapeType.PILL -> {
                    val radius = minOf(size.width, size.height) / 2f
                    addRoundRect(
                        androidx.compose.ui.geometry.RoundRect(
                            rect = androidx.compose.ui.geometry.Rect(0f, 0f, size.width, size.height),
                            topLeft = androidx.compose.ui.geometry.CornerRadius(radius, radius),
                            topRight = androidx.compose.ui.geometry.CornerRadius(radius, radius),
                            bottomLeft = androidx.compose.ui.geometry.CornerRadius(radius, radius),
                            bottomRight = androidx.compose.ui.geometry.CornerRadius(radius, radius)
                        )
                    )
                }
                M3EShapeType.ARCH -> {
                    moveTo(0f, size.height)
                    lineTo(0f, size.height * 0.5f)
                    arcTo(
                        rect = androidx.compose.ui.geometry.Rect(0f, 0f, size.width, size.height),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )
                    lineTo(size.width, size.height)
                    close()
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // M3E 形状显示区域
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // 这里可以放置封面图片或其他内容
                // 暂时留空，只显示背景色
            }

            // 歌单名称
            Text(
                text = playlist.name,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2
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
}
