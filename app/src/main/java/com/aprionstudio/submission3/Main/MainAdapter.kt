package com.aprionstudio.submission3

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.style.TextAppearanceSpan
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.aprionstudio.submission3.Main.DetailActivity
import com.aprionstudio.submission3.R.id.*
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*


class MainAdapter (private val eventResults: List<EventResult>)
    : RecyclerView.Adapter<TeamViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = eventResults.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(eventResults[position])
    }

}

val TextView.bold: Typeface get() = Typeface.createFromAsset(context.assets, "fonts/RobotoSlab-Bold.ttf")
class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            verticalLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(5)
                orientation = LinearLayout.VERTICAL
                id = R.id.matchresult

                linearLayout {
                    textView {
                        id = R.id.matchday
                        textSize = 12f
                        textColor = Color.BLUE
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        text = "tanggal disini" //container for anko preview
                    }.lparams(width = matchParent) {
                        margin = dip(5)
                    }

                }

                linearLayout {
                    lparams( width= matchParent, height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL

                    //linear nama home team
                    linearLayout {
                        lparams(width = dip(0), height = wrapContent,  weight = 3f)
                        orientation = LinearLayout.VERTICAL
                        gravity = center

                        textView {
                            id = R.id.home_name
                            textSize = 14f
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            typeface = bold
                            text = "TEAM A" //container for anko preview
                        }

                    }

                    //linear nama score 1 team
                    linearLayout {
                        lparams(width = dip(0), height = wrapContent,  weight = 0.5f)
                        orientation = LinearLayout.VERTICAL
                        gravity = center

                        textView {
                            id = R.id.home_score
                            textSize = 16f
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            typeface = bold
                            text = "3"
                        }
                    }

                    //linear nama vs team
                    linearLayout {
                        lparams(width = dip(0), height = wrapContent,  weight = 1f)
                        orientation = LinearLayout.VERTICAL
                        gravity = center

                        textView {
                            textSize = 16f
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            typeface = bold
                            text = " VS "
                        }
                    }

                    //linear nama score 2 team
                    linearLayout {
                        lparams(width = dip(0), height = wrapContent,  weight = 0.5f)
                        orientation = LinearLayout.VERTICAL
                        gravity = center

                        textView {
                            id = R.id.away_score
                            textSize = 16f
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            typeface = bold
                            text = "0"
                        }
                    }

                    //linear nama away team
                    linearLayout {
                        lparams(width = dip(0), height = wrapContent, weight = 3f)
                        orientation = LinearLayout.VERTICAL
                        gravity = center

                        textView {
                            id = R.id.away_name
                            textSize = 14f
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            typeface = bold
                            text = "TEAM B"
                        }
                    }
                }

            }

        }
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

    //private val teamBadge: ImageView = view.find(team_badge)
    private val homeName: TextView = view.find(home_name)
    private val homeScore: TextView = view.find(home_score)
    private val awayName: TextView = view.find(away_name)
    private val awayScore: TextView = view.find(away_score)
    private val matchDay: TextView = view.find(matchday)


    fun bindItem(eventResults: EventResult) {
        //Picasso.get().load(eventResults.teamBadge).into(teamBadge)
        homeName.text = eventResults.homeName //+ "   " + eventResults.scoreHome + "   VS   "+ eventResults.scoreAway + "   " + eventResults.namaAway
        homeScore.text = eventResults.homeScore
        awayName.text = eventResults.awayName
        awayScore.text = eventResults.awayScore
        matchDay.text = eventResults.eventTanggal

        itemView.setOnClickListener{
        it?.context?.startActivity<DetailActivity>(
                "eventId" to eventResults.eventId,
                "homeId" to eventResults.homeId,
                "awayId" to eventResults.awayId
                )
        }
    }

}