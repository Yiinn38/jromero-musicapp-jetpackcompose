package com.rasec.musicapp.screens

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

@Serializable
data class AlbumDetailScreenRoute(val id: Int)