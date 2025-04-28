package com.aallam.openai.api.image

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * The format in which the generated images are returned.
 */
@JvmInline
@Serializable
public value class OutputFormat(public val format: String) {

    public companion object {

        public val Png: OutputFormat = OutputFormat("png")

        public val Jpeg: OutputFormat = OutputFormat("jpeg")

        public val Webp: OutputFormat = OutputFormat("webp")
    }
}
