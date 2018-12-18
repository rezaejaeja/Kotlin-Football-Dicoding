package com.aprionstudio.submission3

import com.google.gson.annotations.SerializedName
import java.util.*

data class EventResult(
        @SerializedName("idEvent") var eventId: String? = null,
        @SerializedName("strDate") var eventTanggal: String? = null,
        @SerializedName("idHomeTeam") var homeId: String? = null,
        @SerializedName("idAwayTeam") var awayId: String? = null,
        @SerializedName("strHomeTeam") var homeName: String? = null,
        @SerializedName("intHomeScore") var homeScore: String? = null,
        @SerializedName("strAwayTeam") var awayName: String? = null,
        @SerializedName("intAwayScore") var awayScore: String? = null,
        @SerializedName("strHomeGoalDetails") var homeGoalscorer: String? = null,
        @SerializedName("strHomeLineupGoalkeeper") var homeGK: String? = null,
        @SerializedName("strHomeLineupDefense") var homeDF: String? = null,
        @SerializedName("strHomeLineupMidfield") var homeMF: String? = null,
        @SerializedName("strHomeLineupForward") var homeFW: String? = null,
        @SerializedName("strHomeLineupSubstitutes") var homeSubs: String? = null,
        @SerializedName("strAwayGoalDetails") var awayGoalscorer: String? = null,
        @SerializedName("strAwayLineupGoalkeeper") var awayGK: String? = null,
        @SerializedName("strAwayLineupDefense") var awayDF: String? = null,
        @SerializedName("strAwayLineupMidfield") var awayMF: String? = null,
        @SerializedName("strAwayLineupForward") var awayFW: String? = null,
        @SerializedName("strAwayLineupSubstitutes") var awaySubs: String? = null
)


data class Team(
        @SerializedName("idTeam") var teamId: String? = null,
        @SerializedName("strTeam") var teamName: String? = null,
        @SerializedName("strTeamBadge") var teamBadge: String? = null
)

data class Favorite(val id: Long?, val eventId: String?, val eventDate: String?, val homeId: String?, val awayId: String?, val homeName: String?, val homeScore: String?, val awayName: String?, val awayScore: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID : String = "EVENT_ID"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
        const val HOME_NAME: String = "HOME_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}