package com.joenjogu.nexflix.models

import com.squareup.moshi.Json

data class SearchResponse(
        val page: Int,
        @field:Json(name = "results")val movieResults: List<MovieResult>,
        val total_pages: Int,
        val total_results: Int
)