package com.rasec.musicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rasec.musicapp.screens.HomeScreen
import com.rasec.musicapp.screens.HomeScreenRoute
import com.rasec.musicapp.screens.AlbumDetailScreen
import com.rasec.musicapp.screens.AlbumDetailScreenRoute
import com.rasec.musicapp.ui.theme.MusicAppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MusicAppTheme {
        val navController = rememberNavController()
        Scaffold(
          modifier = Modifier.fillMaxSize(),
          bottomBar = {

          }
        ) { innerPadding ->
          NavHost(navController = navController, startDestination = HomeScreenRoute) {
            composable<HomeScreenRoute> {
              HomeScreen(
                navController = navController
              )
            }
            composable<AlbumDetailScreenRoute> { backEntry ->
              val args = backEntry.toRoute<AlbumDetailScreenRoute>()
              AlbumDetailScreen(args.id)
            }
          }
        }
      }
    }
  }
}
