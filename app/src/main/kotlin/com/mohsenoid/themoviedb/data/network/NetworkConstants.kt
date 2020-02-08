package com.mohsenoid.themoviedb.data.network

object NetworkConstants {

    const val QUERY_PARAM_LANGUAGE_EN_US: String = "en-US"
    const val QUERY_PARAM_VIDEO_TYPE_TRAILER: String = "Trailer"
    const val QUERY_PARAM_VIDEO_SITE_YOUTUBE: String = "YouTube"

    const val POSTER_URL_BASE_URL = "https://image.tmdb.org/t/p/w300/"
    const val YOUTUBE_URL_BASE_URL = "https://youtu.be/"

    const val HTTP_CACHE_PATH = "http-cache"

    const val NETWORK_CONNECTION_TIMEOUT_SEC = 30
    const val CACHE_SIZE_MB = 10
    const val CACHE_MAX_AGE_MIN = 2
    const val CACHE_MAX_STALE_DAY = 7
    const val RETRY_COUNT = 3
}
