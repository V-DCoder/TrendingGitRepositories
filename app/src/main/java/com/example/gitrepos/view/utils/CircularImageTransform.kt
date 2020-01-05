package com.example.gitrepos.view.utils


import android.graphics.*
import com.squareup.picasso.Transformation


class CircularImageTransform : Transformation {


    override fun key(): String {
        return "CircularImageTransform"
    }

    override fun transform(source: Bitmap?): Bitmap {

        val output = Bitmap.createBitmap(
            source!!.width, source
                .height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)

        val color = -0xbdbdbe
        val paint = Paint()
        paint.isAntiAlias = true


        val r: Float = (source.width / 2).toFloat()
        val rect = Rect(0, 0, source.width, source.height)
        paint.color = color
        
        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawCircle(r, r, r, paint)


        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(source, rect, rect, paint)
        source.recycle()
        return output
    }
}