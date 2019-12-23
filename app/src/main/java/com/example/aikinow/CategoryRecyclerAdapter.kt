package com.example.aikinow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aikinow.models.Categories
import kotlinx.android.synthetic.main.category_list_row.view.*

class CategoryRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var items : List<Categories> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return CategoriesViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.category_list_row, parent,false)
       )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when(holder){
           is CategoriesViewHolder -> {
               holder.bind(items.get(position))
           }
       }
    }
    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(categoryList: List<Categories>){
        items = categoryList
    }

    class CategoriesViewHolder constructor(
       itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val categoryText: TextView = itemView.categoryTitleText
        val categoryImage: ImageView = itemView.categoryImageText

        fun bind(categories: Categories){
            categoryText.setText(categories.title)
            categoryImage.setImageResource(categories.icon)
        }

    }

}