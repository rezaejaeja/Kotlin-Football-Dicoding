package com.aprionstudio.submission3.Main

import com.aprionstudio.submission3.ApiRepository
import com.aprionstudio.submission3.EventResultResponse
import com.aprionstudio.submission3.TeamResponse
import com.aprionstudio.submission3.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getBadge(teamId: String?, isHome: Boolean) {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                    TeamResponse::class.java
            )

            uiThread {
                if(isHome)
                    view.showBadgeHome(data.teams)
                else view.showBadgeAway(data.teams)
            }
        }

    }

    fun getEventDetails(eventId: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getEventDetails(eventId)),
                    EventResultResponse::class.java
            )

            uiThread {
                view.showEventDetails(data.events)
            }
        }
    }


}