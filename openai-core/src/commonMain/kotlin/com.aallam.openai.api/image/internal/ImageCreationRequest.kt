package com.aallam.openai.api.image.internal

import com.aallam.openai.api.InternalOpenAI
import com.aallam.openai.api.image.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Image generation request.
 * Results are expected as URLs.
 */
@Serializable
@InternalOpenAI
public data class ImageCreationRequest(
    @SerialName("prompt") val prompt: String,
    @SerialName("n") val n: Int? = null,
    @SerialName("size") val size: ImageSize? = null,
    @SerialName("user") val user: String? = null,
    @SerialName("model") val model: String? = null,
    @SerialName("background") val background: Background? = null,
    @SerialName("output_format") val outputFormat: OutputFormat? = null,
    @SerialName("output_compression") val outputCompression: Int? = null,
    @SerialName("moderation") val moderation: Moderation? = null,
    @SerialName("quality") val quality: Quality? = null,
    @SerialName("style") val style: Style? = null,
    @SerialName("response_format") val responseFormat: ImageResponseFormat? = null,
)
