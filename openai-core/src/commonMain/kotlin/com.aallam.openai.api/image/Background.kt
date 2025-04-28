package com.aallam.openai.api.image

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Transparency of the generated images.
 */
@JvmInline
@Serializable
public value class Background(public val background: String) {

    public companion object {

        public val Auto: Background = Background("auto")

        public val Transparent: Background = Background("transparent")

        public val Opaque: Background = Background("opaque")
    }
}
