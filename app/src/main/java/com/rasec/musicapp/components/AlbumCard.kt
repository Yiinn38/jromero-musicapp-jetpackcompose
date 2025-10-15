package com.rasec.musicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rasec.musicapp.models.Album
import com.rasec.musicapp.ui.theme.DarkColor
import com.rasec.musicapp.ui.theme.LightColor
import com.rasec.musicapp.ui.theme.PrimaryColor

@Composable
fun AlbumCard(
  album: Album,
  onClick: () -> Unit
) {
  Column(
    modifier = Modifier
      .clickable { onClick() }
  ) {

    Box(
      modifier = Modifier
        .size(150.dp)
        .clip(RoundedCornerShape(8.dp))
    ) {
      AsyncImage(
        model = album.image,
        contentDescription = album.title,
        error = ColorPainter(DarkColor),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )
    }

    Text(
      text = album.title,
      style = MaterialTheme.typography.bodyLarge,
      modifier = Modifier.padding(top = 4.dp),
      color = PrimaryColor
    )

    Text(
      text = album.artist,
      style = MaterialTheme.typography.bodySmall,
      color = Color.Gray
    )
  }
}