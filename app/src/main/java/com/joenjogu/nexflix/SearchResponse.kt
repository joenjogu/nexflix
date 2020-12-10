package com.joenjogu.nexflix

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class SearchResponse(
    val page: Int,
    @field:Json(name = "results")val searchResults: List<SearchResult>,
    val total_pages: Int,
    val total_results: Int
)