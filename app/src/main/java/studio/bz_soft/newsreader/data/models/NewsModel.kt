package studio.bz_soft.newsreader.data.models

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("id") val id: String?,
    @SerializedName("publishDate") val publishDate: String?,
    @SerializedName("discoverDate") val discoverDate: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("text") val text: String?,
    @SerializedName("structuredText") val structuredText: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("elements") val elements: List<Elements>?,
    @SerializedName("website") val website: Website?,
    @SerializedName("metadata") val metadata: Metadata?,
    @SerializedName("highlight") val highlight: String?,
    @SerializedName("score") val score: Double?
)

data class Elements(
    @SerializedName("type") val type: String?,
    @SerializedName("primary") val isPrimary: Boolean?,
    @SerializedName("url") val url: String?,
    @SerializedName("width") val width: String?,
    @SerializedName("height") val height: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("alternative") val alternative: String?
)

data class Website(
    @SerializedName("name") val name: String?,
    @SerializedName("hostName") val hostName: String?,
    @SerializedName("domainName") val domainName: String?,
    @SerializedName("iconURL") val iconURL: String?,
    @SerializedName("countryName") val countryName: String?,
    @SerializedName("countryCode") val countryCode: String?,
    @SerializedName("region") val region: String?
)

data class Metadata(
    @SerializedName("readTime") val readTime: ReadTime?,
    @SerializedName("category") val category: Category?
)

data class ReadTime(
    @SerializedName("type") val type: String?,
    @SerializedName("seconds") val seconds: Int?
)

data class Category(
    @SerializedName("type") val type: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("region") val region: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("countryCode") val countryCode: String?
)
