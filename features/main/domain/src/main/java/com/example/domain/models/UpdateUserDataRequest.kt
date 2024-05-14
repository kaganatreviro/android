package com.example.domain.models

import java.io.File

data class UpdateUserDataRequest (
    val name: String?,
    val date: String?,
    val avatar: File?
)