package com.tequila.tracker_domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.core.data.preferences.Preferences
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.model.UserInfo
import com.tequila.tracker_domain.model.MealType
import com.tequila.tracker_domain.model.TrackedFood
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientTest {

    private lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setup() {
        val preferences = mockk<Preferences>(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            goalType = GoalType.KeepWeight,
            fatRatio = 0.3f,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            activityLevel = ActivityLevel.Medium
        )
        calculateMealNutrients = CalculateMealNutrients(preferences)
    }

    @Test
    fun `Calories for breakfast properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.Breakfast,
                imageUrl = null,
                date = LocalDate.now(),
                amount = 100,
                calories = Random.nextInt(2000)
            )
        }

        val result = calculateMealNutrients(trackedFoods)

        val breakfastCalories = result.mealNutrients.values.sumOf { it.calories }
        val expectedCalories = trackedFoods.sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(expectedCalories)
    }
}