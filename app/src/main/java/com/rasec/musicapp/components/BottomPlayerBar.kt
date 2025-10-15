package com.rasec.musicapp.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rasec.musicapp.models.Album
import com.rasec.musicapp.services.AlbumService
import com.rasec.musicapp.ui.theme.DarkColor
import com.rasec.musicapp.ui.theme.LightColor
import com.rasec.musicapp.ui.theme.PrimaryColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun BottomPlayerBar() {
  val albumId = "682243ecf60db4caa642a48a"
  val BASE_URL = "https://music.juanfrausto.com/api/"
  var album by remember {
    mutableStateOf<Album?>(null)
  }

  LaunchedEffect(albumId) {
    try {
      val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
      val service = retrofit.create(AlbumService::class.java)
      val result = withContext(Dispatchers.IO) {
        service.getAlbumById(albumId)
      }
      album = result
      Log.i("BottomPlayerBar", "Album loaded: ${album?.title}")
    } catch (e: Exception) {
      Log.e("BottomPlayerBar", "Error loading album: ${e.toString()}")
      e.printStackTrace()
    }
  }

  val image = album?.image ?: ""
  val title = album?.title ?: "Loading Song..."
  val artist = album?.artist ?: "Unknow Artist"

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp)
      .padding(bottom = 20.dp)
      .shadow(
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
      )
      .clip(RoundedCornerShape(16.dp))
      .background(PrimaryColor)
      .padding(vertical = 8.dp, horizontal = 12.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    AsyncImage(
      model = image.ifEmpty { null },
      contentDescription = title,
      error = ColorPainter(LightColor),
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .size(60.dp)
        .clip(RoundedCornerShape(8.dp))
    )

    Column(
      modifier = Modifier
        .weight(1f)
        .padding(start = 12.dp),
      verticalArrangement = Arrangement.Center
    ) {
      Text(text = title, color = Color.White)
      Text(text = artist, color = Color.LightGray)
    }

    Icon(
      imageVector = Icons.Default.PlayArrow,
      contentDescription = null,
      tint = Color.White,
      modifier = Modifier.size(30.dp)
    )

    Spacer(modifier = Modifier.width(12.dp))

    Icon(
      imageVector = Icons.Default.SkipNext,
      contentDescription = null,
      tint = Color.White,
      modifier = Modifier.size(30.dp)
    )
  }
}