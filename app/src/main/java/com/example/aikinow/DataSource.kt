package com.example.aikinow

import com.example.aikinow.models.Categories

class DataSource {

    companion object{
        fun createDataSet(): ArrayList<Categories>{
            val list = ArrayList<Categories>()
            list.add(
                Categories("Carpenter", R.drawable.ic_backspace_black_24dp)
            )
            list.add(
                Categories("Blacksmith", R.drawable.ic_backspace_black_24dp)
            )
            list.add(
                Categories("Plumber", R.drawable.ic_backspace_black_24dp)
            )
            list.add(
                Categories("Painter", R.drawable.ic_backspace_black_24dp)
            )



            return list
        }
    }
}