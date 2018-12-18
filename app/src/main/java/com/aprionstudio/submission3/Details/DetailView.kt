package com.aprionstudio.submission3.Main

import com.aprionstudio.submission3.EventResult
import com.aprionstudio.submission3.Team

interface DetailView {
    fun showBadgeHome(data: List<Team>)
    fun showBadgeAway(data: List<Team>)
    fun showEventDetails (data: List<EventResult>)
}