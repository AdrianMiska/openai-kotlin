package com.aallam.openai.api.image

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.OpenAIDsl
import com.aallam.openai.api.file.FileSource
import com.aallam.openai.api.model.ModelId
import kotlin.jvm.JvmInline

/**
 * Image edit request.
 */
public class ImageEdit(
    /**
     * The image(s) to edit. Must be a supported image file or an array of images.
     * For gpt-image-1, each image should be a png, webp, or jpg file less than 25MB. You can provide up to 16 images.
     * For dall-e-2, you can only provide one image, and it should be a square png file less than 4MB.
     */
    public val image: ImageEditInput,

    /**
     * An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where [image] should be
     * edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as [image].
     */
    public val mask: FileSource? = null,

    /**
     * A text description of the desired image(s).
     * The maximum length is 1000 characters for dall-e-2, and 32,000 characters for gpt-image-1.
     */
    public val prompt: String,

    /**
     * The number of images to generate. Must be between 1 and 10.
     */
    public val n: Int? = null,

    /**
     * The size of the generated images.
     *
     * Must be one of 1024x1024, 1536x1024 (landscape), 1024x1536 (portrait), or auto (default value) for gpt-image-1,
     * and one of 256x256, 512x512, or 1024x1024 for dall-e-2.
     */
    public val size: ImageSize? = null,

    /**
     * The quality of the image that will be generated.
     *
     * [Quality.High], [Quality.Medium] and [Quality.Low] are only supported for gpt-image-1.
     * dall-e-2 only supports [Quality.Standard] quality.
     *
     * Defaults to [Quality.Auto].
     */
    public val quality: Quality? = null,

    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     */
    public val user: String? = null,

    /**
     * The model to use for image generation.
     * Only dall-e-2 and gpt-image-1 are supported.
     * Defaults to dall-e-2 unless a parameter specific to gpt-image-1 is used.
     */
    public val model: ModelId? = null,
)


/**
 * The image(s) to edit. Must be a supported image file or an array of images.
 */
public sealed interface ImageEditInput {
    /**
     * A single image input
     */
    @JvmInline
    public value class ImageInput(public val value: FileSource) : ImageEditInput

    /**
     * A list of image inputs
     */
    @JvmInline
    public value class ListInput(public val values: List<FileSource>) : ImageEditInput

    public companion object {
        /**
         * Create a text input from a string.
         */
        public fun from(image: FileSource): ImageEditInput = ImageInput(image)

        /**
         * Create an input list from a list of items.
         */
        public fun from(images: List<FileSource>): ImageEditInput = ListInput(images)
    }
}

/**
 * Image edit request.
 */
@BetaOpenAI
public fun imageEdit(block: ImageEditBuilder.() -> Unit): ImageEdit = ImageEditBuilder().apply(block).build()

/**
 * Builder of [ImageEdit] instances.
 */
@BetaOpenAI
@OpenAIDsl
public class ImageEditBuilder {
    /**
     * The image to edit. Must be a supported image file.
     * For gpt-image-1, the image should be a png, webp, or jpg file less than 25MB.
     * For dall-e-2, it should be a square png file less than 4MB.
     */
    public var image: FileSource? = null

    /**
     * The images to edit. Must be an array of images.
     * For gpt-image-1, each image should be a png, webp, or jpg file less than 25MB. You can provide up to 16 images.
     * For dall-e-2, you can only provide one image, and it should be a square png file less than 4MB.
     */
    public var images: List<FileSource>? = null

    /**
     * An additional [image] whose fully transparent areas (e.g. where alpha is zero) indicate where [image] should be
     * edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as image.
     */
    public var mask: FileSource? = null

    /**
     * A text description of the desired image(s). The maximum length is 1000 characters.
     */
    public var prompt: String? = null

    /**
     * The number of images to generate. Must be between 1 and 10.
     */
    public var n: Int? = null

    /**
     * The size of the generated images.
     */
    public var size: ImageSize? = null

    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     */
    public var user: String? = null

    /**
     * The model used to generate image. Must be one of dall-e-2 or dall-e-3. If not provided, dall-e-2 is used.
     */
    public var model: ModelId? = null

    /**
     * Creates the [ImageEdit] instance
     */
    public fun build(): ImageEdit {
        if (image != null && images != null) {
            throw IllegalArgumentException("You can only provide one of image or images.")
        }

        val imageInput = image?.let { ImageEditInput.from(it) }
            ?: images?.let { ImageEditInput.from(it) }
            ?: throw IllegalArgumentException("one of image or images is required")

        return ImageEdit(
            image = imageInput,
            mask = mask,
            prompt = requireNotNull(prompt) { "prompt field is required" },
            n = n,
            size = size,
            user = user,
            model = model,
        )
    }
}
