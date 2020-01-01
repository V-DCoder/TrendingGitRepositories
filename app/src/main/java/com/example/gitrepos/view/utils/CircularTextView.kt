package com.example.gitrepos.view.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView

class CirculImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ImageView(context, attrs, defStyle) {

    private var customcolor: String? = null

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        customcolor?.apply {
            val paint = Paint()
            val color = Color.parseColor(customcolor)
            paint.color = color
            paint.isAntiAlias = true
            val r: Float = (width / 2).toFloat()
            canvas?.drawCircle(r, r, r, paint)
        }


    }

    public fun setCustomColor(color: String?) {
        this.customcolor = color
        invalidate()

    }

}