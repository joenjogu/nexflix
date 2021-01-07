package com.joenjogu.nexflix.models

import com.squareup.moshi.Json

data class RecommendationResponse(
        val page: Int,
        @field:Json(name = "results") val recommendationResults: List<RecommendedResult>,
        val total_pages: Int,
        val total_results: Int
)