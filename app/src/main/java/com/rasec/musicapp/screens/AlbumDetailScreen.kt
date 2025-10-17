package com.rasec.musicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
  var album by remember { mutableStateOf<Album?>(null) }

  LaunchedEffect(true) {
    try {
      val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
      val service = retrofit.create(AlbumService::class.java)
      val result = withContext(Dispatchers.IO) { service.getAlbumById(id) }
      album = result
      Log.i("ProductDetail", album.toString())
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  val Album = album

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(BackgroundColor),
    contentAlignment = Alignment.Center
  ) {
    if (Album != null) {
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .padding(top = 45.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {

        item {
          HeaderAlbum(album = Album)
        }

        item {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(8.dp)
              .shadow(4.dp, RoundedCornerShape(16.dp))
              .background(Color.White)
              .clip(RoundedCornerShape(16.dp))
              .padding(12.dp)
          ) {
            Text(
              "About this album",
              color = PrimaryColor,
              style = MaterialTheme.typography.bodyLarge
            )
            Text(
              Album.description,
              color = Color.DarkGray,
              style = MaterialTheme.typography.bodyMedium
            )
          }
        }

        item {
          Box(modifier = Modifier.fillMaxWidth()) {
            Column(
              modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterStart)
                .width(240.dp)
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .background(Color.White)
                .clip(RoundedCornerShape(16.dp))
                .padding(12.dp),
              horizontalAlignment = Alignment.Start
            ) {
              Text(
                "Artist",
                color = PrimaryColor,
                style = MaterialTheme.typography.bodyLarge
              )
              Text(
                Album.artist,
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodyMedium
              )
            }
          }
        }

        item {
          Spacer(modifier = Modifier.height(16.dp))
        }

        val tracks = (1..24 step 1).map { trackNumber ->
          "Track.$trackNumber"
        }

        itemsIndexed(tracks) { index, trackName ->
          SongItem(
            trackNumber = trackName,
            title = Album.title,
            artist = Album.artist,
            imageUrl = Album.image
          )
          Spacer(modifier = Modifier.height(8.dp))
        }
      }

    } else {
      CircularProgressIndicator(
        color = DarkColor,
        trackColor = LightColor
      )
    }
  }
}

@Composable
fun SongItem(
  trackNumber: String,
  title: String,
  artist: String,
  imageUrl: String
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .shadow(4.dp, RoundedCornerShape(16.dp))
      .clip(RoundedCornerShape(16.dp))
      .background(Color.White)
      .padding(8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(
      modifier = Modifier
        .size(60.dp)
        .clip(RoundedCornerShape(16.dp))
    ) {
      AsyncImage(
        model = imageUrl,
        contentDescription = null,
        error = ColorPainter(DarkColor),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )
    }

    Column(
      modifier = Modifier
        .fillMaxHeight()
        .padding(start = 8.dp)
    ) {
      Text(
        "$title  • $trackNumber",
        style = MaterialTheme.typography.bodyLarge,
        color = PrimaryColor
      )
      Text(
        artist,
        style = MaterialTheme.typography.bodySmall,
        color = Color.Gray
      )
    }

    Spacer(modifier = Modifier.weight(1f))

    Icon(
      imageVector = Icons.Default.MoreVert,
      contentDescription = null,
      tint = Color.DarkGray
    )
  }
}