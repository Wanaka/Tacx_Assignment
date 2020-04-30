package com.example.tacx_assignment.view

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.tacx_assignment.R
import com.example.tacx_assignment.adapter.FileAdapter
import com.example.tacx_assignment.model.FileModel
import com.example.tacx_assignment.navigator.Navigator
import com.example.tacx_assignment.util.Swipe
import com.example.tacx_assignment.vm.FileTableViewModel
import kotlinx.android.synthetic.main.activity_file_table.*
import kotlinx.coroutines.*
import kotlin.collections.ArrayList

class FileTableActivity : AppCompatActivity() {

    lateinit var viewModel: FileTableViewModel
    private val job = Job()
    private var navigator = Navigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_table)
        viewModel = ViewModelProviders.of(this).get(FileTableViewModel::class.java)

        list()
        createAdapterSwipe()
    }

    private fun list() {
        var folder = intent.getStringExtra(KEY)

        if (folder != null) {
            getList(folder)
            toolbar(folder)
        } else {
            getList(resources.getString(R.string.documents))
            toolbar(resources.getString(R.string.documents))
        }
    }

    private fun getList(folder: String) {
        CoroutineScope(Dispatchers.IO + job).launch {
            var result = viewModel.getList(folder)
            withContext(Dispatchers.Main) { createAdapter(result) }
        }
    }

    private fun createAdapter(list: ArrayList<FileModel>) {
        var adapter = FileAdapter(this, list)
        fileListView.adapter = adapter

        itemClickListener(list)
    }

    private fun itemClickListener(list: ArrayList<FileModel>) {
        fileListView.setOnItemClickListener { _, _, i, _ ->
            checkIsFolder(list, i)
        }
    }

    private fun checkIsFolder(arrayList: ArrayList<FileModel>, i: Int) {
        when (arrayList[i].isFolder) {
            true -> {
                navigator.startIntent(this, arrayList[i].fileName)
            }
            false -> toast(resources.getString(R.string.file))
        }
    }


    private fun createAdapterSwipe() {
        val creator = Swipe().createSwipe(this)
        fileListView.setMenuCreator(creator)

        swipeItemClickListener()
    }

    private fun swipeItemClickListener() {
        fileListView.setOnMenuItemClickListener { _, _, index ->
            when (index) {
                0 -> toast(resources.getString(R.string.favourite))
                1 -> toast(resources.getString(R.string.link))
                2 -> toast(resources.getString(R.string.delete))
            }

            // false : close the menu; true : not close the menu
            false
        }
    }


    private fun toolbar(title: String) {
        toolbarFileTable.title = title
        setSupportActionBar(toolbarFileTable)
    }


    private fun Context.toast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    companion object {
        const val KEY = "key"
    }
}
