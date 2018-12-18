package com.aprionstudio.submission3.Main

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aprionstudio.submission3.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.match_fragment.*
import android.support.v7.widget.DividerItemDecoration

class EventFragment() : Fragment(), MainView {

    private var eventResults: MutableList<EventResult> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MainAdapter(eventResults)
        match_list.layoutManager = LinearLayoutManager(activity)
        match_list.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL));
        match_list.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        presenter.getPastEventList(getString(R.string.event_leagueid))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.match_fragment, container, false)
    }


    override fun showLoading() {
       progressBar.visible()
    }

    override fun hideLoading() {
       progressBar.invisible()
    }

    override fun showEventList(data: List<EventResult>) {
        eventResults.clear()
        eventResults.addAll(data)
        adapter.notifyDataSetChanged()
    }

}