package org.wordpress.android.ui.activitylog.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import org.wordpress.android.R

class ActivityLogViewHolder(
    parent: ViewGroup,
    private val itemClickListener: (ActivityLogListItem) -> Unit,
    private val rewindClickListener: (ActivityLogListItem) -> Unit
) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.activity_log_list_item, parent, false)) {
    private val summary: TextView = itemView.findViewById(R.id.action_summary)
    private val text: TextView = itemView.findViewById(R.id.action_text)
    private val thumbnail: ImageView = itemView.findViewById(R.id.action_icon)
    private val progressBarContainer: View = itemView.findViewById(R.id.rewind_progress_bar_container)
    private val container: View = itemView.findViewById(R.id.activity_content_container)
    private val rewindButton: ImageButton = itemView.findViewById(R.id.rewind_button)
    private val header: TextView = itemView.findViewById(R.id.activity_header_text)

    private lateinit var activity: ActivityLogListItem

    fun bind(activity: ActivityLogListItem, previous: ActivityLogListItem?, next: ActivityLogListItem?) {
        this.activity = activity

        summary.text = activity.title
        text.text = activity.description
        header.text = activity.formattedDate

        progressBarContainer.visibility = if (activity.isProgressBarVisible) View.VISIBLE else View.GONE
        header.visibility = if (activity.isHeaderVisible) View.VISIBLE else View.GONE
        rewindButton.visibility = if (activity.isButtonVisible) View.VISIBLE else View.GONE

        thumbnail.setImageResource(activity.icon.drawable)
        thumbnail.setBackgroundResource(activity.status.color)
        container.setOnClickListener {
            itemClickListener(activity)
        }

        rewindButton.setOnClickListener {
            rewindClickListener(activity)
        }
    }
}
