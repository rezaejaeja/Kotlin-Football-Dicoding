package com.aprionstudio.submission3

import com.aprionstudio.submission3.ApiRepository
import com.aprionstudio.submission3.MainView
import com.aprionstudio.submission3.TheSportDBApi
import com.aprionstudio.submission3.TeamResponse

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

        fun getPastEventList(leagueId: String?) {
            view.showLoading()
            doAsync {
                val dataMatch = gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPastMatches(leagueId)),
                        EventResultResponse::class.java
                )

                uiThread {
                    view.hideLoading()
                    view.showEventList(dataMatch.events)
                }
            }
        }

        fun getNextEventList(leagueId: String?) {
            view.showLoading()
            doAsync {
                val dataMatch = gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getUpcomingMatches(leagueId)),
                        EventResultResponse::class.java
                )

                uiThread {
                    view.hideLoading()
                    view.showEventList(dataMatch.events)
                }
            }
        }
}