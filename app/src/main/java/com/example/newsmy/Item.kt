package com.example.newsmy

import java.io.Serializable

data class Item(var date: String,
                var title: String,
                var content: String,
                var image: String): Serializable
