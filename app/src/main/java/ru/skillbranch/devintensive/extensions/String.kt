package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils

fun String.truncate(length: Int = 16) : String {
	return if (length > 0) {
		val result = this.trimEnd()

		if (result.isNotEmpty() && result.length > length)
			"${this.substring(0, length).trimEnd()}..."
		else
			result
	} else {
		""
	}
}

fun String.stripHtml(): String {
	var result = Regex("""\s{2,}""").replace(
		Regex("<.*?>|<|>|'|\"").replace(this, ""), " ")

	for (match in Regex("""&.+?;""").findAll(result)) {
			result = Utils.htmlSpecChars[match.value]?.let {
				result.replace(match.value, it)
			}?: result
	}

	return result.replace("&", "")
}
