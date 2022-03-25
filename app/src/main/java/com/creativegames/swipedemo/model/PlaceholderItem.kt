package com.creativegames.swipedemo.model

/**
 * A placeholder item representing a piece of content.
 */
data class PlaceholderItem(
  val id: String,
  val content: String,
  val details: String
) {
  override fun toString(): String = content
}