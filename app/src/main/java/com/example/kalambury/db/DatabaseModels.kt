package com.example.kalambury.db

import android.provider.BaseColumns

class DatabaseModels : BaseColumns {
    companion object {
        const val TABLE_NAME = "Terms"
        const val COLUMN_TERM = "Term"
        const val COLUMN_TYPE = "Type"
    }
}