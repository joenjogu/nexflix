package com.joenjogu.nexflix.models

import com.squareup.moshi.Json

data class TrendingResponse(
    val page: Int,
    @field:Json(name = "results")val trendingResults: List<TrendingResult>,
    val total_pages: Int,
    val total_results: Int
)
