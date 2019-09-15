package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.PorterDuff.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.annotation.Dimension.DP
import androidx.core.content.ContextCompat
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.utils.Utils
import kotlin.math.min

class CircleImageView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

	companion object {
		private const val DEFAULT_BORDER_WIDTH = 2
		private const val DEFAULT_BORDER_COLOR = Color.WHITE
	}

	private var borderColor = DEFAULT_BORDER_COLOR
	private var borderWidth = Utils.convertDpToPx(context, DEFAULT_BORDER_WIDTH)

	private var text: String? = null
	private var bitmap: Bitmap? = null

	init {
		if (attrs != null) {
			val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
			borderColor = a.getColor(
				R.styleable.CircleImageView_cv_borderColor, DEFAULT_BORDER_COLOR)
			borderWidth = a.getDimensionPixelSize(
				R.styleable.CircleImageView_cv_borderWidth, borderWidth)
			a.recycle()
		}
	}

	@Dimension(unit = DP)
	fun getBorderWidth() : Int = Utils.convertPxToDp(context, borderWidth)

	fun setBorderWidth(@Dimension(unit = DP) dp: Int) {
		borderWidth = Utils.convertDpToPx(context, dp)
		invalidate()
	}

	fun getBorderColor(): Int = borderColor

	fun setBorderColor(hex: String) {
		borderColor = Color.parseColor(hex)
		invalidate()
	}

	fun setBorderColor(@ColorRes colorId: Int) {
		borderColor = ContextCompat.getColor(App.applicationContext(), colorId)
		invalidate()
	}

	override fun onDraw(canvas: Canvas) {
		var bm = getBitmapFromDrawable() ?: return
		if (width == 0 || height == 0) {
			return
		}

		bm = getScaledBitmap(bm, width)
		bm = getCenterCroppedBitmap(bm, width)
		bm = getCircleBitmap(bm)

		if (borderWidth > 0) {
			bm = getStrokedBitmap(bm, borderWidth, borderColor)
		}

		canvas.drawBitmap(bm, 0F, 0F, null)
	}

	fun generateAvatar(text: String?, sizeSp: Int, theme: Resources.Theme) {
		if (bitmap == null || text != this.text) {
			bitmap =
				if (text == null) {
					getDefaultAvatar(theme)
				} else {
					getInitials(text, sizeSp, theme)
				}

			this.text = text
			setImageBitmap(bitmap)
			invalidate()
		}
	}

	private fun getInitials(text: String, sizeSp: Int, theme: Resources.Theme): Bitmap {
		val bmp = getDefaultAvatar(theme)

		val paint = Paint(Paint.ANTI_ALIAS_FLAG)
		paint.textSize = sizeSp.toFloat()
		paint.color = Color.WHITE
		paint.textAlign = Paint.Align.CENTER

		val textBounds = Rect()
		paint.getTextBounds(text, 0, text.length, textBounds)

		val backgroundBounds = RectF()
		backgroundBounds.set(
			0F, 0F, layoutParams.height.toFloat(), layoutParams.height.toFloat())

		val textBottom = backgroundBounds.centerY() - textBounds.exactCenterY()
		val canvas = Canvas(bmp)
		canvas.drawText(text, backgroundBounds.centerX(), textBottom, paint)

		return bmp
	}

	private fun getDefaultAvatar(theme: Resources.Theme): Bitmap {
		val bmp = Bitmap.createBitmap(layoutParams.height, layoutParams.height, ARGB_8888)
		val color = TypedValue()
		theme.resolveAttribute(R.attr.colorAccent, color, true)

		val canvas = Canvas(bmp)
		canvas.drawColor(color.data)

		return bmp
	}

	private fun getStrokedBitmap(squareBmp: Bitmap, strokeWidth: Int, color: Int): Bitmap {
		val inCircle = RectF()
		val strokeStart = strokeWidth/2F
		val strokeEnd = squareBmp.width - strokeWidth/2F

		inCircle.set(strokeStart, strokeStart, strokeEnd, strokeEnd)

		val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
		strokePaint.color = color
		strokePaint.style = Paint.Style.STROKE
		strokePaint.strokeWidth = strokeWidth.toFloat()

		val canvas = Canvas(squareBmp)
		canvas.drawOval(inCircle, strokePaint)

		return squareBmp
	}

	private fun getCircleBitmap(bm: Bitmap): Bitmap {
		val smallest = min(bm.width, bm.height)
		val outputBmp = Bitmap.createBitmap(smallest, smallest, ARGB_8888)
		val cvs = Canvas(outputBmp)

		val pnt = Paint()
		val rct = Rect(0, 0, smallest, smallest)

		pnt.isAntiAlias = true
		pnt.isFilterBitmap = true
		pnt.isDither = true
		cvs.drawARGB(0, 0, 0, 0)
		cvs.drawCircle(smallest/2F, smallest/2F, smallest/2F, pnt)

		pnt.xfermode = PorterDuffXfermode(Mode.SRC_IN)
		cvs.drawBitmap(bm, rct, rct, pnt)

		return outputBmp
	}

	private fun getCenterCroppedBitmap(bm: Bitmap, size: Int): Bitmap {
		val cropStartX = (bm.width - size)/2
		val cropStartY = (bm.height - size)/2

		return Bitmap.createBitmap(bm, cropStartX, cropStartY, size, size)
	}

	private fun getScaledBitmap(bm: Bitmap, minSide: Int): Bitmap =
		if (bm.width != minSide || bm.height != minSide) {
			val factor = min(bm.width, bm.height).toFloat()/minSide
			Bitmap.createScaledBitmap(
				bm, (bm.width/factor).toInt(), (bm.height/factor).toInt(), false)
		} else {
			bm
		}

	private fun getBitmapFromDrawable(): Bitmap? {
		if (bitmap != null) {
			return bitmap
		}

		if (drawable == null) {
			return null
		}

		if (drawable is BitmapDrawable) {
			return (drawable as BitmapDrawable).bitmap
		}

		val bm = Bitmap.createBitmap(
			drawable.intrinsicWidth, drawable.intrinsicHeight, ARGB_8888)
		val cn = Canvas(bm)
		drawable.setBounds(0, 0, cn.width, cn.height)
		drawable.draw(cn)

		return bm
	}

}