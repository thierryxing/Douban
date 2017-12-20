package com.thierry.douban.extension

/**
 * 重点：Extension
 *
 * Created by Thierry on 2017/7/2.
 */
import android.graphics.*
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

fun ImageView.loadUrl(url: String) {
    if (!url.isEmpty()) {
        Picasso.with(this.context).load(url).into(this)
    }
}

fun ImageView.loadRound(url: String) {
    if (!url.isEmpty()) {
        Picasso.with(this.context).load(url).transform(CircleTransform()).into(this)
    }
}

class CircleTransform : Transformation {

    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2
        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap !== source) {
            source.recycle()
        }
        val bitmap = Bitmap.createBitmap(size, size, source.config)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true
        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)
        squaredBitmap.recycle()
        return bitmap
    }

    override fun key(): String {
        return "circle"
    }
}