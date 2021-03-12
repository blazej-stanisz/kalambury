package com.example.kalambury.db.models

import android.provider.BaseColumns

class TermsTableModel : BaseColumns {
    companion object {
        const val TERMS_TABLE_NAME = "Terms"
        const val COLUMN_TERM = "Term"
        const val COLUMN_TYPE = "Type"
    }
}

// [Terms]
//  + Id
//  + Term
//  + Type