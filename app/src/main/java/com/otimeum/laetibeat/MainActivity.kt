package com.otimeum.laetibeat


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.otimeum.laetibeat.ui.navigation.AppNavigation
import com.otimeum.laetibeat.ui.theme.LaetibeatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // 启用边缘到边缘显示（可选）
        
        setContent {
            LaetibeatTheme {
                AppNavigation()
            }
        }
    }
}