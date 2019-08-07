package com.mvvm.tmdb.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mvvm.tmdb.R

class SearchableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)

        getSearchIntent(intent)

    }

    override fun onNewIntent(intent: Intent?) {
        setIntent(intent)
        getSearchIntent(intent)
        super.onNewIntent(intent)
    }

    private fun getSearchIntent(intent: Intent?) {
        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent?.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
                SearchRecentSuggestions(this, SuggestionProvider.AUTHORITY, SuggestionProvider.MODE)
                    .saveRecentQuery(query, null)
            }
        }
    }

    private fun doMySearch(query: String) {
        Toast.makeText(this, query, Toast.LENGTH_SHORT).show()
    }
}
