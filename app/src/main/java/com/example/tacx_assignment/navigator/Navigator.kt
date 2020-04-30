package com.example.tacx_assignment.navigator

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.tacx_assignment.view.FileTableActivity

class Navigator {

    fun startIntent(context: Context, folder: String) {
        val intent = Intent(context, FileTableActivity::class.java)
        intent.putExtra(KEY, folder)
        startActivity(context, intent, null)
    }

    companion object{
        const val KEY = "key"
    }
}