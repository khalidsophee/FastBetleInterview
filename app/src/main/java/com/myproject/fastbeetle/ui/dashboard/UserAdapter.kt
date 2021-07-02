package com.myproject.fastbeetle.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.myproject.fastbeetle.R
import com.myproject.fastbeetle.data.Items
import com.myproject.fastbeetle.databinding.UserViewBinding

class UserAdapter(
    private val items: List<Items>
) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val notificationBinding =
            UserViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(notificationBinding, parent)
    }

    override fun getItemCount() = items.size

    class ViewHolder(val view: UserViewBinding, val parent: ViewGroup) :
        RecyclerView.ViewHolder(view.root) {
        fun bindTo(notification: Items) {
            //  view.root.setOnClickListener(listener)
            view.tvName.text = notification.firstName
            view.tvEmail.text = notification.emailId
            view.ivPic.load(notification.imageUrl) {
                crossfade(true)
                placeholder(R.color.black)
                transformations(CircleCropTransformation())
            }


        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(items[position])
    }

}