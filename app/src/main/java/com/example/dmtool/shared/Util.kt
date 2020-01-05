package com.example.dmtool.shared

import android.app.Application

fun formatNpcLocation(location: String): String {
    return "Location: $location"
}

fun formatNpcType(type: String): String {
    return "Type: $type"
}

fun getDatabase(application: Application): DmDatabase {
    return DmDatabase.getInstance(application)
}