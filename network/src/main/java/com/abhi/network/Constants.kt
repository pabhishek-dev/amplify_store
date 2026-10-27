package com.abhi.network

object Constants {
    const val DB_NAME = "app_db"
    // const val API_HOST = "192.168.0.104" // Local
    const val API_HOST = "amplify-shop-backend.onrender.com" // Cloud
    const val API_BASE_URL = "https://$API_HOST/" // Append ":4443/" for local
    const val API_STREAM_URL = "${API_BASE_URL}api/product/stream"
}

object BaseUrl {
    const val FETCH_DATA = "https://jsonplaceholder.typicode.com/posts/1"
}

const val LOGS = "AMPLIFYSTORE_LOGS"
