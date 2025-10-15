package com.rasec.musicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rasec.musicapp.models.Album
import com.rasec.musicapp.services.AlbumService
import com.rasec.musicapp.ui.theme.BackgroundColor
import com.rasec.musicapp.ui.theme.DarkColor
import com.rasec.musicapp.ui.theme.LightColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AlbumDetailScreen(id: String) {
  val BASE_URL = "https://music.juanfrausto.com/api/"
  var album by remember {
    mutableStateOf<Album?>(null)
  }
  LaunchedEffect(true) {
    try {
      val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
      val service = retrofit.create(AlbumService::class.java)
      val result = withContext(Dispatchers.IO) {
        service.getAlbumById(id)
      }
      album = result
      Log.i("ProductDetail", album.toString())
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  val currentAlbum = album
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(BackgroundColor)
      .padding(top = 45.dp)
      .padding(horizontal = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    if(currentAlbum != null) {
      Text(text = currentAlbum.title)
    } else {
      CircularProgressIndicator(
        color = DarkColor,
        trackColor = LightColor
      )
    }
  }
}