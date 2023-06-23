package com.example.onecloud.modules.upcomingMeetings.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.ItemProgressViewBinding
import com.example.design.databinding.ItemUpcomingMeetingBinding
import com.example.design.databinding.ItemUpcomingMeetingDateBinding
import com.example.onecloud.Utills.formatDate
import com.example.onecloud.customViews.PopUpFromLayout
import com.example.onecloud.modules.upcomingMeetings.model.MeetingsListSealedClass
import com.example.onecloud.modules.upcomingMeetings.model.UpcomingMeetingsDataResult
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

enum class ViewType {
    Meeting,
    Date,
    ProgressFooter
}

class MeetingsAdapter(
    val copyLink: () -> Unit,
    val editMeeting: () -> Unit,
    val cancelMeeting: (Int) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var meetingList = arrayListOf<MeetingsListSealedClass>()
    var isLoading = false

    override fun getItemViewType(position: Int): Int {
        return if (position == meetingList.count()-1 && isLoading) {
            ViewType.ProgressFooter.ordinal
        } else {
            when (meetingList[position]) {
                is UpcomingMeetingsDataResult -> ViewType.Meeting.ordinal
                is MeetingsListSealedClass.MeetingDate -> ViewType.Date.ordinal
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.Meeting.ordinal -> {
                val binding = ItemUpcomingMeetingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MeetingViewHolder(binding)
            }
            ViewType.ProgressFooter.ordinal -> {
                val binding = ItemProgressViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ProgressViewHolder(binding)
            }
            else -> {
                val binding = ItemUpcomingMeetingDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DateViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = meetingList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MeetingViewHolder -> holder.setUpView(meetingList[position] as UpcomingMeetingsDataResult)
            is DateViewHolder -> holder.setUpView(meetingList[position] as MeetingsListSealedClass.MeetingDate)
        }
    }

    fun showProgress() {
        isLoading = true
    }

    fun hideProgress() {
        isLoading = false
    }

    fun clearList() {
        meetingList.clear()
        notifyDataSetChanged()
    }

    fun submitList(newMeetingList: ArrayList<MeetingsListSealedClass>) {
        val diffCallback = MeetingsDiffUtil(meetingList, newMeetingList)
        val diffCard = DiffUtil.calculateDiff(diffCallback)
        meetingList.clear()
        meetingList.addAll(newMeetingList)
        diffCard.dispatchUpdatesTo(this)
    }

    class MeetingsDiffUtil(
        private val oldItemList: ArrayList<MeetingsListSealedClass>,
        private val newItemList: ArrayList<MeetingsListSealedClass>
        ): DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItemList.size
        }

        override fun getNewListSize(): Int {
            return newItemList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItemList[oldItemPosition] == newItemList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItemList[oldItemPosition] == newItemList[newItemPosition]
        }
    }

    inner class MeetingViewHolder(val binding: ItemUpcomingMeetingBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setUpView(meeting: UpcomingMeetingsDataResult) {
            binding.apply {
                btnMoreOptions.setOnClickListener { setUpPopUpMenu() }
                tvMeetingTitle.text = meeting.schedule.title + " " + meeting.schedule.description
                tvMeetingDate.text = meeting.schedule.startTime.formatDate("dd MMM yyyy, hh mm aaa")
                tvMeetingDuration.text = meeting.schedule.duration
                ContextCompat.getDrawable(binding.ivOrganizerAvatar.context,R.drawable.ic_avatar)?.let {
                    Picasso.get().load(meeting.user.avatar).placeholder(it) .into(binding.ivOrganizerAvatar)
                }
                tvOrgenizerName.text = meeting.user.firstName + " " + meeting.user.lastName
            }
        }

        private fun setUpPopUpMenu() {
            val moreOptionPopup = PopUpFromLayout(
                binding.btnMoreOptions.context,
                R.layout.upcoming_meeting_pop_up,
                binding.btnMoreOptions, -150, 0
            )
            moreOptionPopup.showPopup()
            moreOptionPopup.onItemSelectedListener { id ->
                when (id) {
                    R.id.copy_link_menu_item -> copyLink()
                    R.id.edit_menu_item -> editMeeting()
                    R.id.cancel_menu_item -> cancelMeeting((meetingList[adapterPosition] as UpcomingMeetingsDataResult).schedule.pk)
                }
            }
        }
    }

    class ProgressViewHolder(val binding: ItemProgressViewBinding) : RecyclerView.ViewHolder(binding.root)

    inner class DateViewHolder(val binding: ItemUpcomingMeetingDateBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setUpView(date: MeetingsListSealedClass.MeetingDate) {
            binding.tvDate.text = date.date
        }
    }
}