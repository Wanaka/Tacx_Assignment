package com.example.tacx_assignment.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.example.tacx_assignment.R

class Swipe {

    fun createSwipe(context: Context): SwipeMenuCreator {
        return SwipeMenuCreator { menu ->
            menu.addMenuItem(favourite(context))
            menu.addMenuItem(link(context))
            menu.addMenuItem(delete(context))
        }
    }

    private fun favourite(context: Context): SwipeMenuItem {
        val favouriteItem = SwipeMenuItem(
            context
        )
        // set item background
        favouriteItem.background = ColorDrawable(
            ContextCompat.getColor(
                context,
                R.color.colorGreen
            )
        )
        // set item width
        favouriteItem.width = 200
        // set a icon
        favouriteItem.setIcon(R.drawable.ic_favourite)

        return favouriteItem
    }

    private fun link(context: Context): SwipeMenuItem {
        val linkItem = SwipeMenuItem(
            context
        )
        // set item background
        linkItem.background = ColorDrawable(
            ContextCompat.getColor(
                context,
                R.color.colorBlue
            )
        )
        // set item width
        linkItem.width = 200
        // set a icon
        linkItem.setIcon(R.drawable.ic_link)

        return linkItem
    }

    private fun delete(context: Context): SwipeMenuItem {
        val deleteItem = SwipeMenuItem(
            context
        )
        // set item background
        deleteItem.background = ColorDrawable(
            Color.rgb(
                0xF9,
                0x3F, 0x25
            )
        )
        // set item width
        deleteItem.width = 200
        // set a icon
        deleteItem.setIcon(R.drawable.ic_delete)

        return deleteItem
    }
}