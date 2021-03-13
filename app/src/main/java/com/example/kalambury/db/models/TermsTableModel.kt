package com.example.kalambury.db.models

import android.provider.BaseColumns

class TermsTableModel : BaseColumns {
    companion object {
        const val TERMS_TABLE_NAME = "Terms"
        const val COLUMN_ID = "id"
        const val COLUMN_TERM_NAME = "term_name"
        const val COLUMN_CATEGORY_ID = "category_id"
    }
}

// [Terms]
//  + id
//  + term_name
//  + category_id