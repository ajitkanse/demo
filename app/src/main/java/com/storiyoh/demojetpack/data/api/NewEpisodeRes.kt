package com.storiyoh.demojetpack.data.api

public data class NewEpisodeRes(
    val data: Data,
    val mesage: String,
    val status: Boolean
) {
    data class Data(
        val items: List<Item>,
        val pages: Int,
        val per_page: Int,
        val total: Int
    ) {
        data class Item(
            val created_date: String,
            val duration: String,
            val explicit: Int,
            val id: Int,
            val image: String,
            val last_updated: String,
            val owner: String,
            val premium: String,
            val purchase: String,
            val selling_price: String,
            val show_id: Int,
            val show_image: String,
            val show_title: String,
            val standalone: String,
            val title: String
        )
    }
}