package com.aprionstudio.submission3.Main

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.aprionstudio.submission3.*
import com.aprionstudio.submission3.R.drawable.ic_add_to_favorites
import com.aprionstudio.submission3.R.drawable.ic_added_to_favorites
import com.google.gson.Gson
import kotlinx.android.synthetic.main.detail_mockup.*
import com.bumptech.glide.Glide
import com.aprionstudio.submission3.R.menu.*
import com.aprionstudio.submission3.R.id.*
import org.jetbrains.anko.db.*
import org.jetbrains.anko.design.snackbar


class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var eventResults : EventResult
    private lateinit var presenter: DetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        menuItem?.findItem(R.id.add_to_favorite)?.setVisible(false)
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to eventResults.eventId,
                        Favorite.EVENT_DATE to eventResults.eventTanggal,
                        Favorite.HOME_ID to eventResults.homeId,
                        Favorite.AWAY_ID to eventResults.awayId,
                        Favorite.HOME_NAME to eventResults.homeName,
                        Favorite.HOME_SCORE to eventResults.homeScore,
                        Favorite.AWAY_NAME to eventResults.awayName,
                        Favorite.AWAY_SCORE to eventResults.awayScore)
            }
            snackbar(view, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(view, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to id)
            }
            snackbar(view, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(view, e.localizedMessage).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_mockup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Hit Api to get TeamIcon
        eventResults = EventResult()
        val request = ApiRepository()
        val gson = Gson()
        val intent = intent
        id = intent.getStringExtra("eventId")
        favoriteState()
        presenter = DetailPresenter(this, request, gson)
        presenter.getBadge(intent.getStringExtra("homeId"),true)
        presenter.getBadge(intent.getStringExtra("awayId"),false)
        presenter.getEventDetails(intent.getStringExtra("eventId"))

    }


    override fun showBadgeHome(data: List<Team>) {
        Glide.with(this).load(data[0].teamBadge).into(ev_homeBadge)

    }

    override fun showBadgeAway(data: List<Team>) {
        Glide.with(this).load(data[0].teamBadge).into(ev_awayBadge)
    }

    override fun showEventDetails(data: List<EventResult>) {
        eventResults = EventResult(data[0].eventId, data[0].eventTanggal, data[0].homeId, data[0].awayId, data[0].homeName, data[0].homeScore, data[0].awayName,data[0].awayScore)
        menuItem?.findItem(R.id.add_to_favorite)?.setVisible(true)
        ev_homeName.text = data[0].homeName
        ev_awayName.text = data[0].awayName
        ev_matchDate.text = data[0].eventTanggal
        if(data[0].homeScore!=null) ev_scoreTotal.text = data[0].homeScore + " - " + data[0].awayScore
        ev_homeGoalScorer.text = data[0].homeGoalscorer
        ev_awayGoalScorer.text = data[0].awayGoalscorer
        ev_homeGK.text = data[0].homeGK
        ev_homeDF.text = data[0].homeDF
        ev_homeMF.text = data[0].homeMF
        ev_homeFW.text = data[0].homeFW
        ev_homeSubs.text = data[0].homeSubs
        ev_awayGK.text = data[0].awayGK
        ev_awayDF.text = data[0].awayDF
        ev_awayMF.text = data[0].awayMF
        ev_awayFW.text = data[0].awayFW
        ev_awaySubs.text = data[0].awaySubs
    }
}