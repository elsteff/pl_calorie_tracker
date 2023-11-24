package com.tequila.tracker_presentation.tracker_overview

import com.tequila.tracker_domain.model.TrackableFood
import com.tequila.tracker_domain.model.TrackedFood
import com.tequila.tracker_domain.use_case.DeleteTrackedFood

sealed class TrackerOverviewEvent {
    object OnNextDayClick: TrackerOverviewEvent()
    object OnPreviousDayClick: TrackerOverviewEvent()
    data class OnToggleMealClick(val meal: Meal): TrackerOverviewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood): TrackerOverviewEvent()
}