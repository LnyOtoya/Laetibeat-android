package com.otimeum.laetibeat.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * M3E 风格的动态形状类型（使用标准 Compose Shape API）
 */
enum class M3EShapeType {
    CIRCLE,
    SQUARE,
    PILL,
    ARCH
}

/**
 * 通用有机形状 - 基于 Path 的自定义 Shape
 */
class OrganicShape(
    private val pathBuilder: (Size) -> Path
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = pathBuilder(size))
    }
}

@Composable
fun M3EShape(
    shapeType: M3EShapeType,
    size: Dp = 120.dp,
    color: Color = Color.White
) {
    val shape = when (shapeType) {
        M3EShapeType.CIRCLE -> CircleShape
        M3EShapeType.SQUARE -> SquareShape
        M3EShapeType.PILL -> PillShape
        M3EShapeType.ARCH -> ArchShape
    }

    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(size)
            .background(color, shape)
    ) {
        // Canvas 用于绘制背景
    }
}

// ==================== 形状定义 ====================

/**
 * 圆形
 */
val CircleShape = OrganicShape { size ->
    Path().apply {
        addOval(Rect(Offset.Zero, size))
    }
}

/**
 * 圆角正方形
 */
val SquareShape = OrganicShape { size ->
    val cornerRadius = minOf(size.width, size.height) * 0.16f
    Path().apply {
        addRoundRect(
            androidx.compose.ui.geometry.RoundRect(
                rect = Rect(Offset.Zero, size),
                topLeft = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
                topRight = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
                bottomLeft = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius),
                bottomRight = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius)
            )
        )
    }
}

/**
 * 药丸形（胶囊形）
 */
val PillShape = OrganicShape { size ->
    val radius = minOf(size.width, size.height) / 2f
    Path().apply {
        addRoundRect(
            androidx.compose.ui.geometry.RoundRect(
                rect = Rect(Offset.Zero, size),
                topLeft = androidx.compose.ui.geometry.CornerRadius(radius, radius),
                topRight = androidx.compose.ui.geometry.CornerRadius(radius, radius),
                bottomLeft = androidx.compose.ui.geometry.CornerRadius(radius, radius),
                bottomRight = androidx.compose.ui.geometry.CornerRadius(radius, radius)
            )
        )
    }
}

/**
 * 拱形
 */
val ArchShape = OrganicShape { size ->
    Path().apply {
        val width = size.width
        val height = size.height
        
        // 从左下角开始
        moveTo(0f, height)
        
        // 左侧垂直线到中间
        lineTo(0f, height * 0.5f)
        
        // 顶部弧形
        arcTo(
            rect = Rect(0f, 0f, width, height),
            startAngleDegrees = 180f,
            sweepAngleDegrees = 180f,
            forceMoveTo = false
        )
        
        // 右侧垂直线到底部
        lineTo(width, height)
        
        close()
    }
}


/**
 * 获取随机形状
 */
fun getRandomShape(): M3EShapeType {
    return M3EShapeType.values().random()
}
