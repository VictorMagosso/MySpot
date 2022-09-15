package com.victor.myspot.movies.presentation.view

interface FormatInput {
    fun formattedInput(text: String): String {
        var newString = ""

        textSplit(text).forEach {
            var firstChar = ""
            firstChar = "${it.firstOrNull()?.uppercase()}"
            newString += "+${it.replaceFirstChar { firstChar }}"
        }
        return newString.replaceFirstChar { "" }
    }

    fun textSplit(text: String) = text.lowercase().split(" ")
}
