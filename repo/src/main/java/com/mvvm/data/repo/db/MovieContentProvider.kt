package com.mvvm.data.repo.db

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.mvvm.data.repo.model.TABLE_NAME


class MovieContentProvider : ContentProvider() {

    companion object {
        const val AUTHORITY = "com.mvvm.data.provider"
        val URI_MOVIE =
            Uri.parse("content://$AUTHORITY/$TABLE_NAME")
    }


    val db = context?.let { DBService(it) }
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        if (context != null) {
            val cursor: Cursor? =
                db?.database?.resultDao()?.getMovieNames(selection ?: "")
            cursor?.setNotificationUri(context?.contentResolver, uri)
            return cursor
        }

        throw IllegalArgumentException("Failed to query row for uri $uri")
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

}