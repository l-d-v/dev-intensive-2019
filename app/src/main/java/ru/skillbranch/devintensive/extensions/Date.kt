package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") : String {
	val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
	return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
	var time = this.time

	time += when (units) {
		TimeUnits.SECOND -> value * SECOND
		TimeUnits.MINUTE -> value * MINUTE
		TimeUnits.HOUR   -> value * HOUR
		TimeUnits.DAY    -> value * DAY
	}

	this.time = time

	return this
}

fun Date.humanizeDiff(): String {
	val delta = Date().time - this.time

	return when (abs(delta)) {
		in 0 * SECOND .. 1 * SECOND  -> if (delta > 0) "только что" else "вот-вот"
		in 1 * SECOND .. 45 * SECOND -> {
			val value = "несколько секунд"
			return if (delta < 0) "через $value" else "$value назад"
		}
		in 45 * SECOND .. 75 * SECOND -> {
			val value = "минуту"
			return if (delta < 0) "через $value" else "$value назад"
		}
		in 75 * SECOND .. 45 * MINUTE -> {
			val mins = abs(delta / MINUTE)
			val fract = mins.rem(10)
			val value = "$mins минут${if (fract.compareTo(1) == 0) "у" else if (fract in 2..4) "ы" else ""}"
			return if (delta < 0) "через $value" else "$value назад"
		}
		in 45 * MINUTE .. 75 * MINUTE -> {
			val value = "час"
			return if (delta < 0) "через $value" else "$value назад"
		}
		in 75 * MINUTE .. 22 * HOUR -> {
			val hours = abs(delta / HOUR)
			val fract = hours.rem(10)
			val value = "$hours час${if (fract.compareTo(1) == 0) "" else if (fract in 2..4) "а" else "ов"}"
			return if (delta < 0) "через $value" else "$value назад"
		}
		in 22 * HOUR .. 26 * HOUR -> {
			val value = "день"
			return if (delta < 0) "через $value" else "$value назад"
		}
		in 26 * HOUR .. 360 * DAY -> {
			val days = abs(delta / DAY)
			val fract = days.rem(10)
			val value = "$days ${if (fract.compareTo(1) == 0) "день" else if (fract in 2..4) "дня" else "дней"}"
			return if (delta < 0) "через $value" else "$value назад"
		}
		else -> if (delta > 0) "более года назад" else "более чем через год"
	}
}

enum class TimeUnits {
	SECOND,
	MINUTE,
	HOUR,
	DAY
}