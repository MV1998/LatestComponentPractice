package com.example.latestcomponentpractice.scoped_storage.models

data class UserX(
    val accepted_tos: Boolean,
    val bio: String,
    val first_name: String,
    val for_hire: Boolean,
    val id: String,
    val instagram_username: Any,
    val last_name: String,
    val location: String,
    val name: String,
    val portfolio_url: String,
    val social: SocialX,
    val total_collections: Int,
    val total_illustrations: Int,
    val total_likes: Int,
    val total_photos: Int,
    val total_promoted_illustrations: Int,
    val total_promoted_photos: Int,
    val twitter_username: Any,
    val updated_at: String,
    val username: String
)