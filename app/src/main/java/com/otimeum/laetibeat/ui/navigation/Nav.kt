package com.otimeum.laetibeat.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.PlaylistPlay
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.otimeum.laetibeat.data.model.LibraryViewType
import com.otimeum.laetibeat.ui.screens.home.HomeScreen
import com.otimeum.laetibeat.ui.screens.library.LibraryScreen
import com.otimeum.laetibeat.ui.screens.player.PlayerScreen
import com.otimeum.laetibeat.ui.screens.playlist.PlaylistDetailScreen
import com.otimeum.laetibeat.ui.screens.settings.SettingsScreen
import com.otimeum.laetibeat.viewmodel.LibraryViewModel
import com.otimeum.laetibeat.viewmodel.MainViewModel

sealed class Screen(val route: String, val label: String, val icon: @Composable () -> Unit) {
    object Playlists : Screen("playlists", "歌单", { Icon(Icons.Default.PlaylistPlay, null) })
    object Library : Screen("library", "音乐库", { Icon(Icons.Default.LibraryMusic, null) })
    object Stats : Screen("stats", "统计", { Icon(Icons.Default.ShowChart, null) })
    object PlaylistDetail : Screen("playlist_detail/{playlistId}", "歌单详情", { Icon(Icons.Default.PlaylistPlay, null) })
    object Player : Screen("player/{songId}", "播放器", { Icon(Icons.Default.LibraryMusic, null) })
}

@Composable
fun AppNavigation(viewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == Screen.Playlists.route || currentRoute == Screen.Library.route || 
                (viewModel.userPreferences.showStats && currentRoute == Screen.Stats.route)) {
                NavigationBar {
                    val screens = listOfNotNull(
                        Screen.Playlists,
                        Screen.Library,
                        if (viewModel.userPreferences.showStats) Screen.Stats else null
                    )
                    screens.forEach { screen ->
                        NavigationBarItem(
                            icon = screen.icon,
                            label = { androidx.compose.material3.Text(screen.label) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Playlists.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Playlists.route) {
                HomeScreen(
                    playlists = viewModel.playlists,
                    viewModel = viewModel,
                    onPlaylistClick = { playlist ->
                        navController.navigate("playlist_detail/${playlist.id}")
                    },
                    onSettingsClick = {
                        navController.navigate("settings")
                    }
                )
            }
            composable(Screen.Library.route) {
                val libraryViewModel = androidx.lifecycle.viewmodel.compose.viewModel<LibraryViewModel>()
                LibraryScreen(viewModel = libraryViewModel)
            }
            composable(Screen.Stats.route) {
                // TODO: Stats Screen
            }
            composable("playlist_detail/{playlistId}") { backStackEntry ->
                val playlistId = backStackEntry.arguments?.getString("playlistId")
                val playlist = viewModel.playlists.find { it.id == playlistId }
                playlist?.let {
                    PlaylistDetailScreen(playlist = it) { song ->
                        navController.navigate("player/${song.id}")
                    }
                }
            }
            composable("player/{songId}") { backStackEntry ->
                val songId = backStackEntry.arguments?.getString("songId")
                // 简单查找歌曲，实际项目中应通过 ID 查找
                val song = viewModel.playlists.flatMap { it.songs }.find { it.id == songId }
                song?.let {
                    PlayerScreen(song = it)
                }
            }
            composable("settings") {
                SettingsScreen(viewModel = viewModel)
            }
        }
    }
}