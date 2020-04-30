package com.example.tacx_assignment.vm

import androidx.lifecycle.ViewModel
import com.example.tacx_assignment.model.FileModel
import com.example.tacx_assignment.repo.FirestoreRepo

class FileTableViewModel : ViewModel() {
    private var firestoreRepo = FirestoreRepo()

    fun getList(folder: String): ArrayList<FileModel> {
        return firestoreRepo.getList(folder)
    }
}