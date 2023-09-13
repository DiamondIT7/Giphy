package com.example.giphy.model

import com.google.gson.annotations.SerializedName

data class DataResult(
    @SerializedName("data") val res: List<DataObject>
)

data class DataObject(
    @SerializedName("images") val image: DataImage
)

data class DataImage(
    @SerializedName("original") val origImage: OrigImage
)

data class OrigImage(
    val url: String
)
