package com.creativegames.swipedemo.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.creativegames.swipedemo.databinding.ItemListContentBinding
import com.creativegames.swipedemo.model.PlaceholderItem

class RecyclerviewAdapter(
  private val values: List<PlaceholderItem>
) :
  RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position]
    holder.idView.text = item.content
    holder.contentView.text = item.details
  }

  override fun getItemCount() = values.size

  inner class ViewHolder(binding: ItemListContentBinding) : RecyclerView.ViewHolder(binding.root) {
    val idView: TextView = binding.taskName
    val contentView: TextView = binding.taskDesc
  }

}
