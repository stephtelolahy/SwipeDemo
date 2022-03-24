package com.creativegames.swipedemo.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.creativegames.swipedemo.databinding.ItemListContentBinding

class SwipeRecyclerviewAdapter(
  private val values: List<PlaceholderItem>
) :
  RecyclerView.Adapter<SwipeRecyclerviewAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeRecyclerviewAdapter.ViewHolder {
    val binding = ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position]
    holder.idView.text = item.id
    holder.contentView.text = item.content
  }

  override fun getItemCount() = values.size

  inner class ViewHolder(binding: ItemListContentBinding) : RecyclerView.ViewHolder(binding.root) {
    val idView: TextView = binding.idText
    val contentView: TextView = binding.content
  }
}