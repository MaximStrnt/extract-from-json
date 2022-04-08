package com.example.newsmy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsmy.ui.MainFragment
import com.squareup.picasso.Picasso

class Adapter(private val mContext: Context?, private val mItems: ArrayList<Item>) :
    RecyclerView.Adapter<Adapter.MyHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val currentItem = mItems[position]
        val date = currentItem.date
        val title = currentItem.title
        val content = currentItem.content
        holder.mDate.text = date
        holder.mTitle.text = title
        holder.mContent.text = content


        Picasso.get().load(mItems[position].image).into(holder.mImage)

        holder.itemView.setOnClickListener {
        MainFragment.click(mItems[holder.adapterPosition])
        }

    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mDate: TextView
        var mTitle: TextView
        var mContent: TextView
        var mImage: ImageView

        init {
            mDate = itemView.findViewById(R.id.tv_date)
            mTitle = itemView.findViewById(R.id.tv_title)
            mImage = itemView.findViewById(R.id.tv_image)
            mContent = itemView.findViewById(R.id.tv_content)
        }
    }
}
