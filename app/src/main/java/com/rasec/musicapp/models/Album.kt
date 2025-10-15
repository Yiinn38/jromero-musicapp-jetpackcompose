package com.rasec.musicapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Album(
  val id: Int,
  val title: String,
  val description: String,
  val image: String
)