package com.example.stoxly.api

class StockRepository {
    private val api = RetrofitInstance.api
    private val apiKey = "LKT1YMFIN0W0HD2B"

    suspend fun getCompanyOverview(symbol: String): CompanyOverview {
        return api.getCompanyoverview(symbol = symbol, apiKey = apiKey)
    }
}