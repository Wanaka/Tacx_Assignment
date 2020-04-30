package com.example.tacx_assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.example.tacx_assignment.R
import com.example.tacx_assignment.model.FileModel
import kotlinx.android.synthetic.main.list_view_item.view.*

class FileAdapter(
    private val context: Context,
    private val dataSource: ArrayList<FileModel>
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.list_view_item, parent, false)
        val item = getItem(position) as FileModel

        rowView.itemTitle.text = item.fileName
        rowView.itemMod.text = item.modDate
        checkIsFolder(item.isFolder, rowView)
        checkIsOrange(item.isOrange, rowView)

        return rowView
    }

    private fun checkIsFolder(isFolder: Boolean, rowView: View) {
        when (isFolder) {
            true -> rowView.itemIcon.setImageResource(R.drawable.ic_folder)
            false -> rowView.itemIcon.setImageResource(R.drawable.ic_file)
        }
    }

    private fun checkIsOrange(isOrange: Boolean, rowView: View) {
        when (isOrange) {
            true -> rowView.colorTab.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorOrange
                )
            )
            false -> rowView.colorTab.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorBlue
                )
            )
        }
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}