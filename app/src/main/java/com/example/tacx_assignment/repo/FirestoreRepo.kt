package com.example.tacx_assignment.repo

import com.example.tacx_assignment.model.FileModel
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore


class FirestoreRepo {
    var db = FirebaseFirestore.getInstance()
    var fileItemList = ArrayList<FileModel>()
    private lateinit var fileItem: FileModel

    fun getList(folder: String): ArrayList<FileModel> {
        val path = db.collection(folder)
        var document = Tasks.await(path.get())

        if (document.documents != null) {
            for (d in document.documents) {
                fileItem = FileModel(
                    d.get("fileName").toString(),
                    d.get("isFolder").toString().toBoolean(),
                    d.get("modDate").toString(),
                    d.get("fileType").toString(),
                    d.get("isOrange").toString().toBoolean()
                )

                fileItemList.add(fileItem)
            }
        }

        return fileItemList
    }
}