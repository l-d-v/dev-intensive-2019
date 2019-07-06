package ru.skillbranch.devintensive.utils

object Utils {
	private val dictionary: Map<Char, String> = mapOf(
		'а' to "a",  'б' to "b",  'в' to "v",
		'г' to "g",  'д' to "d",  'е' to "e",
		'ё' to "e",  'ж' to "zh", 'з' to "z",
		'и' to "i",  'й' to "i",  'к' to "k",
		'л' to "l",  'м' to "m",  'н' to "n",
		'о' to "o",  'п' to "p",  'р' to "r",
		'с' to "s",  'т' to "t",  'у' to "u",
		'ф' to "f",  'х' to "h",  'ц' to "c",
		'ч' to "ch", 'ш' to "sh", 'щ' to "sh'",
		'ъ' to "",   'ы' to "i",  'ь' to "",
		'э' to "e",  'ю' to "yu", 'я' to "ya"
	)

	val htmlSpecChars: Map<String, String> = mapOf(
		"&quot;"   to "\"", "&num;"    to "#", "&dollar;"  to "$",  "&percnt;" to "%", "&amp;"      to "&",
		"&apos;"   to "'",  "&lpar;"   to "(", "&rpar;"    to ")",  "&ast;"    to "*", "&plus;"     to "+",
		"&comma;"  to ",",  "&minus;"  to "-", "&period;"  to ".",  "&sol;"    to "/", "&colon;"    to ":",
		"&semi;"   to ";",  "&lt;"     to "<", "&equals;"  to "=",  "&gt;"     to ">", "&quest;"    to "?",
		"&commat;" to "@",  "&lsqb;"   to "[", "&bsol;"    to "\\", "&rsqb;"   to "]", "&Hat;"      to "^",
		"&lowbar;" to "_",  "&grave;"  to "`", "&lcub;"    to "{",  "&verbar;" to "|", "&rcub;"     to "}",
		"&nbsp;"   to " ",  "&iexcl;"  to "¡", "&cent;"    to "¢",  "&pound;"  to "£", "&curren;"   to "¤",
		"&yen;"    to "¥",  "&#x20B9;" to "₹", "&brvbar;"  to "¦",  "&sect;"   to "§", "&uml;"      to "¨",
		"&copy;"   to "©",  "&ordf;"   to "ª", "&laquo;"   to "«",  "&not;"    to "¬", "&shy;"      to "",
		"&reg;"    to "®",  "&macr;"   to "¯", "&deg;"     to "°",  "&plusmn;" to "±", "&sup2;"     to "²",
		"&sup3;"   to "³",  "&acute;"  to "´", "&micro;"   to "µ",  "&para;"   to "¶", "&middot;"   to "·",
		"&cedil;"  to "¸",  "&sup1;"   to "¹", "&ordm;"    to "º",  "&raquo;"  to "»", "&frac14;"   to "¼",
		"&frac12;" to "½",  "&frac34;" to "¾", "&iquest;"  to "¿",  "&Agrave;" to "À", "&Aacute;"   to "Á",
		"&Acirc;"  to "Â",  "&Atilde;" to "Ã", "&Auml;"    to "Ä",  "&Aring;"  to "Å", "&AElig;"    to "Æ",
		"&Ccedil;" to "Ç",  "&Egrave;" to "È", "&Eacute;"  to "É",  "&Ecirc;"  to "Ê", "&Euml;"     to "Ë",
		"&Igrave;" to "Ì",  "&Iacute;" to "Í", "&Icirc;"   to "Î",  "&Iuml;"   to "Ï", "&ETH;"      to "Ð",
		"&Ntilde;" to "Ñ",  "&Ograve;" to "Ò", "&Oacute;"  to "Ó",  "&Ocirc;"  to "Ô", "&Otilde;"   to "Õ",
		"&Ouml;"   to "Ö",  "&times;"  to "×", "&Oslash;"  to "Ø",  "&Ugrave;" to "Ù", "&Uacute;"   to "Ú",
		"&Ucirc;"  to "Û",  "&Uuml;"   to "Ü", "&Yacute;"  to "Ý",  "&THORN;"  to "Þ", "&szlig;"    to "ß",
		"&agrave;" to "à",  "&aacute;" to "á", "&acirc;"   to "â",  "&atilde;" to "ã", "&auml;"     to "ä",
		"&aring;"  to "å",  "&aelig;"  to "æ", "&ccedil;"  to "ç",  "&egrave;" to "è", "&eacute;"   to "é",
		"&ecirc;"  to "ê",  "&euml;"   to "ë", "&igrave;"  to "ì",  "&iacute;" to "í", "&icirc;"    to "î",
		"&iuml;"   to "ï",  "&eth;"    to "ð", "&ntilde;"  to "ñ",  "&ograve;" to "ò", "&oacute;"   to "ó",
		"&ocirc;"  to "ô",  "&otilde;" to "õ", "&ouml;"    to "ö",  "&divide;" to "÷", "&oslash;"   to "ø",
		"&ugrave;" to "ù",  "&uacute;" to "ú", "&ucirc;"   to "û",  "&uuml;"   to "ü", "&yacute;"   to "ý",
		"&thorn;"  to "þ",  "&yuml;"   to "ÿ", "&OElig;"   to "Œ",  "&oelig;"  to "œ", "&Scaron;"   to "Š",
		"&scaron;" to "š",  "&Yuml;"   to "Ÿ", "&fnof;"    to "ƒ",  "&circ;"   to "ˆ", "&tilde;"    to "˜",
		"&Alpha;"  to "Α",  "&Beta;"   to "Β", "&Gamma;"   to "Γ", "&Delta;"   to "Δ", "&Epsilon;"  to "Ε",
		"&Zeta;"   to "Ζ",  "&Eta;"    to "Η", "&Theta;"   to "Θ", "&Iota;"    to "Ι", "&Kappa;"    to "Κ",
		"&Lambda;" to "Λ",  "&Mu;"     to "Μ", "&Nu;"      to "Ν", "&Xi;"      to "Ξ", "&Omicron;"  to "Ο",
		"&Pi;"     to "Π",  "&Rho;"    to "Ρ", "&Sigma;"   to "Σ", "&Tau;"     to "Τ", "&Upsilon;"  to "Υ",
		"&Phi;"    to "Φ",  "&Chi;"    to "Χ", "&Psi;"     to "Ψ", "&Omega;"   to "Ω", "&alpha;"    to "α",
		"&beta;"   to "β",  "&gamma;"  to "γ", "&delta;"   to "δ", "&epsilon;" to "ε", "&zeta;"     to "ζ",
		"&eta;"    to "η",  "&theta;"  to "θ", "&iota;"    to "ι", "&kappa;"   to "κ", "&lambda;"   to "λ",
		"&mu;"     to "μ",  "&nu;"     to "ν", "&xi;"      to "ξ", "&omicron;" to "ο", "&pi;"       to "π",
		"&rho;"    to "ρ",  "&sigmaf;" to "ς", "&sigma;"   to "σ", "&tau;"     to "τ", "&upsilon;"  to "υ",
		"&phi;"    to "φ",  "&chi;"    to "χ", "&psi;"     to "ψ", "&omega;"   to "ω", "&thetasym;" to "ϑ",
		"&upsih;"  to "ϒ",  "&piv;"    to "ϖ", "&ensp;"    to " ", "&emsp;"    to " ", "&thinsp;"  to " ",
		"&zwnj;"   to "‌",   "&zwj;"    to "‍",  "&lrm;"     to "‎",  "&rlm;"     to "",  "&ndash;"    to "–",
		"&mdash;"  to "—", "&lsquo;"  to "‘", "&rsquo;"   to "’", "&sbquo;"   to "‚", "&ldquo;"    to "“",
		"&rdquo;"  to "”",  "&bdquo;"  to "„", "&dagger;"  to "†", "&Dagger;"  to "‡",  "&permil;"   to "‰",
		"&lsaquo;" to "‹",  "&rsaquo;" to "›",  "&bull;"    to "•", "&hellip;"  to "…", "&prime;"    to "′",
		"&Prime;"  to "″",  "&oline;"  to "‾",  "&frasl;"   to "⁄", "&weierp;"  to "℘", "&image;"    to "ℑ",
		"&real;"   to "ℜ", "&trade;"  to "™", "&alefsym;" to "ℵ", "&larr;"   to "←", "&uarr;"     to "↑",
		"&rarr;"   to "→", "&darr;"   to "↓",  "&harr;"   to "↔", "&crarr;"   to "↵", "&lArr;"     to "⇐",
		"&uArr;"   to "⇑",  "&rArr;"   to "⇒", "&dArr;"   to "⇓", "&hArr;"    to "⇔", "&forall;"   to "∀",
		"&part;"   to "∂",  "&exist;"  to "∃", "&empty;"  to "∅", "&nabla;"   to "∇", "&isin;"     to "∈",
		"&notin;"  to "∉",  "&ni;"     to "∋", "&prod;"   to "∏", "&sum;"     to "∑", "&minus;"    to "−",
		"&lowast;" to "∗",  "&radic;"  to "√", "&prop;"   to "∝", "&infin;"   to "∞", "&ang;"      to "∠",
		"&and;"    to "∧",  "&or;"     to "∨", "&cap;"    to "∩", "&cup;"     to "∪", "&int;"      to "∫",
		"&there4;" to "∴",  "&sim;"    to "∼", "&cong;"   to "≅", "&asymp;"   to "≈", "&ne;"       to "≠",
		"&equiv;"  to "≡",  "&le;"     to "≤", "&ge;"     to "≥", "&sub;"     to "⊂", "&sup;"      to "⊃",
		"&nsub;"   to "⊄",  "&sube;"   to "⊆", "&supe;"   to "⊇", "&oplus;"   to "⊕", "&otimes;"   to "⊗",
		"&perp;"   to "⊥",  "&sdot;"   to "⋅",  "&lceil;"  to "⌈", "&rceil;"   to "⌉", "&lfloor;"   to "⌊",
		"&rfloor;" to "⌋",  "&lang;"   to "〈",  "&rang;"   to "〉",  "&loz;"     to "◊", "&spades;"   to "♠",
		"&clubs;"  to "♣",  "&hearts;" to "♥", "&diams;"  to "♦"
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

				if (dictionary.containsKey(smallChar)) {
					charCode  = char.toInt()
					isCapital = charCode in 1040..1071

					newChar = dictionary[smallChar].toString()
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