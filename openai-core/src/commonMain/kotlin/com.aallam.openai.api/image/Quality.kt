package com.aallam.openai.api.image

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * The quality of the image that will be generated
 */
@Serializable
@JvmInline
public value class Quality(public val value: String) {
    public companion object {
        public val Auto: Quality = Quality("auto")
        public val High: Quality = Quality("high")
        public val Medium: Quality = Quality("medium")
        public val Low: Quality = Quality("low")
        public val HD: Quality = Quality("hd")
        public val Standard: Quality = Quality("standard")
    }
}
