package com.rasec.musicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rasec.musicapp.models.Album
import com.rasec.musicapp.sections.HeaderAlbum
import com.rasec.musicapp.services.AlbumService
import com.rasec.musicapp.ui.theme.BackgroundColor
import com.rasec.musicapp.ui.theme.DarkColor
import com.rasec.musicapp.ui.theme.LightColor
import com.rasec.musicapp.ui.theme.PrimaryColor
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

  val Album = album
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(BackgroundColor)
      .padding(top = 45.dp)
      .padding(horizontal = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    if (Album != null) {
      HeaderAlbum(album = Album)
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp)
          .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(16.dp)
          )
          .background(Color.White)
          .clip(RoundedCornerShape(16.dp))
          .padding(12.dp)
      ) {
        Text(
          text = "About this album",
          color = PrimaryColor,
          style = MaterialTheme.typography.bodyLarge,
        )
        Text(
          text = Album.description,
          color = Color.DarkGray,
          style = MaterialTheme.typography.bodyMedium,
        )
      }
      Column(
        modifier = Modifier
          .padding(8.dp)
          .align(Alignment.Start)
          .width(240.dp)
          .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(16.dp)
          )
          .background(Color.White)
          .clip(RoundedCornerShape(16.dp))
          .padding(12.dp),
        horizontalAlignment = Alignment.Start
      ) {
        Text(
          text = "Artist",
          color = PrimaryColor,
          style = MaterialTheme.typography.bodyLarge,
        )
        Text(
          text = Album.artist,
          color = Color.DarkGray,
          style = MaterialTheme.typography.bodyMedium,
        )
      }

    } else {
      CircularProgressIndicator(
        color = DarkColor,
        trackColor = LightColor
      )
    }
  }
}