package com.tequila.core.domain.model

sealed class ActivityLevel(val name: String) {
    object Low : ActivityLevel("low")
    object Medium : ActivityLevel("medium")
    object High : ActivityLevel("height")

    companion object {
        fun fromString(name: String?): ActivityLevel {
            return when (name) {
                "low" -> Low
                "medium" -> Medium
                "height" -> High
                else -> Medium // normally you would throw an exception, but this case will never happen
            }
        }
    }
}
