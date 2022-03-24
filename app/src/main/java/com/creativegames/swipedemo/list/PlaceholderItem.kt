package com.creativegames.swipedemo.list

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