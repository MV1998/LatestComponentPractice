package com.example.latestcomponentpractice.scoped_storage.models

data class CoverPhoto(
    val alt_description: String,
    val alternative_slugs: AlternativeSlugs,
    val asset_type: String,
    val blur_hash: String,
    val breadcrumbs: List<Breadcrumb>,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: String,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val plus: Boolean,
    val premium: Boolean,
    val promoted_at: String,
    val slug: String,
    val sponsorship: Any,
    val updated_at: String,
    val user: User,
    val width: Int
)