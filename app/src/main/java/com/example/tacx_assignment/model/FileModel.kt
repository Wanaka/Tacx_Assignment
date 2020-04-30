package com.example.tacx_assignment.model


data class FileModel(
    var fileName: String,
    var isFolder: Boolean,
    var modDate: String,
    var fileType: String,
    var isOrange: Boolean
)