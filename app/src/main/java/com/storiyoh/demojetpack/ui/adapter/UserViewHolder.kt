package com.ahmedabdelmeged.pagingwithrxjava.kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.storiyoh.demojetpack.R
import com.storiyoh.demojetpack.data.api.NewEpisodeRes
import kotlinx.android.synthetic.main.item_user.view.*


/**
 * Created by Ahmed Abd-Elmeged on 2/20/2018.
 */
class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(user: NewEpisodeRes.Data.Item?) {
        itemView.UserName.text = user?.title

        Glide.with(itemView.context)
                .load(user?.image)
                .into(itemView.UserAvatar)

        itemView.siteAdminIcon.visibility = if (true) View.VISIBLE else View.GONE

        itemView.setOnClickListener(View.OnClickListener {
          //  Toast.makeText(this, "${user?.title}",1);

        })
    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_user, parent, false)
            return UserViewHolder(view)
        }
    }

}