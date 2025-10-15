package com.rasec.musicapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rasec.musicapp.models.Album

@Composable
fun AlbumCard(
  album: Album,
  onClick: () -> Unit
) {
  Box(
    modifier = Modifier
      .padding(15.dp)
      .width(200.dp)
      .height(300.dp)
      .clickable { onClick() },
    contentAlignment = Alignment.TopCenter
  ) {
    Text(text = album.title)
  }
}