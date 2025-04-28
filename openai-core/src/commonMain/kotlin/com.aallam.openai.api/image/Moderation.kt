package com.aallam.openai.api.image

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Control the content-moderation level for images.
 */
@JvmInline
@Serializable
public value class Moderation(public val moderation: String) {

    public companion object {

        public val Low: Moderation = Moderation("low")

        public val Auto: Moderation = Moderation("auto")
    }
}
