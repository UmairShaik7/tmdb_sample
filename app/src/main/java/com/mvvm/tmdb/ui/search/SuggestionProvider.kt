package com.mvvm.tmdb.ui.search

import android.content.SearchRecentSuggestionsProvider

class SuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(
            AUTHORITY,
            MODE
        )
    }

    companion object {
        const val AUTHORITY = "com.mvvm.tmdb.ui.search.SuggestionProvider"
        const val MODE: Int = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES
    }
}