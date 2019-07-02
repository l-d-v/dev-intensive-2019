package ru.skillbranch.devintensive.utils

object Utils {
	private val dictionary: Map<String, String> = mapOf(
		Pair("а", "a"),  Pair("б", "b"),  Pair("в", "v"),
		Pair("г", "g"),  Pair("д", "d"),  Pair("е", "e"),
		Pair("ё", "e"),  Pair("ж", "zh"), Pair("з", "z"),
		Pair("и", "i"),  Pair("й", "i"),  Pair("к", "k"),
		Pair("л", "l"),  Pair("м", "m"),  Pair("н", "n"),
		Pair("о", "o"),  Pair("п", "p"),  Pair("р", "r"),
		Pair("с", "s"),  Pair("т", "t"),  Pair("у", "u"),
		Pair("ф", "f"),  Pair("х", "h"),  Pair("ц", "c"),
		Pair("ч", "ch"), Pair("ш", "sh"), Pair("щ", "sh'"),
		Pair("ъ", ""),   Pair("ы", "i"),  Pair("ь", ""),
		Pair("э", "e"),  Pair("ю", "yu"), Pair("я", "ya")
	)

	fun parseFullName(fullName: String?): Pair<String?, String?> {
		val parts: List<String>? = fullName?.split(" ")?.filter { it.isNotEmpty() }

		val firstName = parts?.getOrNull(0)
		val lastName = parts?.getOrNull(1)

//		return Pair(firstName, lastName)
		return firstName to lastName
	}

	fun transliteration(payload: String, devider: String = " "): String {
		// А - 1040, Я - 1071, а - 1072, я - 1103
		val parts: List<String> = payload.split(" ").filter { it.isNotEmpty() }
		val result = StringBuilder()
		var isCapital: Boolean
		var smallChar: Char
		var newChar: String
		var charCode: Int

		for (part in parts) {
			if (result.isNotEmpty()) {
				result.append(devider)
			}

			for (char in part.toCharArray()) {
				smallChar = char.toLowerCase()

				if (dictionary.containsKey(smallChar.toString())) {
					charCode  = char.toInt()
					isCapital = charCode in 1040..1071

					newChar = dictionary[smallChar.toString()].toString()
					if (isCapital) {
						newChar =
							if (newChar.length > 1)
								newChar[0].toUpperCase() + newChar.substring(1)
							else
								newChar.toUpperCase()
					}

					result.append(newChar)
				} else {
					result.append(char)
				}
			}
		}

		return result.toString()
	}

	fun toInitials(firstName: String?, lastName: String?): String? {
		val trimF = firstName?.trim() ?: ""
		val trimL = lastName?.trim() ?: ""

		val result = "${
			if (trimF.isNotEmpty()) trimF[0].toUpperCase() else ""
		}${
			if (trimL.isNotEmpty()) trimL[0].toUpperCase() else ""
		}"

		return if (result.isNotEmpty()) result else null
	}
}