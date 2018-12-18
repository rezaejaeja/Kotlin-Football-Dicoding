package com.aprionstudio.submission3

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<EventResult>)
}