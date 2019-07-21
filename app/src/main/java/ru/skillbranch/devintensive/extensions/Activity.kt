package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.math.roundToInt

fun Activity.hideKeyboard() {
	val inputMethodManager =
		getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	inputMethodManager.hideSoftInputFromWindow(currentFocus!!.applicationWindowToken, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
	val visibleBounds = Rect()
	val rootView = this.getRootView()
	rootView.getWindowVisibleDisplayFrame(visibleBounds)

	val heightDiff = rootView.height - visibleBounds.height()
	val marginOfError = TypedValue.applyDimension(
		TypedValue.COMPLEX_UNIT_DIP, 50F, resources.displayMetrics
	).roundToInt()
	return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed(): Boolean = !isKeyboardOpen()

fun Activity.getRootView(): View = findViewById(android.R.id.content)