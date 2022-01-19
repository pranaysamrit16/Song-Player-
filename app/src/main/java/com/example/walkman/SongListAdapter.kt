package com.example.walkman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongListAdapter(val clickListener: ClickListener) : RecyclerView.Adapter<SongListAdapter.ViewHolder>() {
    private var mClickListener : ClickListener? = null
    init {
        mClickListener = clickListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var itemText: TextView? = null
        var itemImage: ImageView? = null
        init {
            itemText = itemView.findViewById(R.id.textSong)
            itemImage = itemView.findViewById(R.id.imageSong)
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            mClickListener?.onClick(itemView,this.adapterPosition)
        }

//        override fun onClick(view: View?) {
//            mClickListener.onClick(itemView, this.adapterPosition)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView:View = LayoutInflater.from(parent.context).inflate(R.layout.song_list_recycler_item,
            parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemImage?.setImageBitmap(SongsDataObject.songFiles[position].icon)
        holder.itemText?.setText(SongsDataObject.songFiles[position].name)
    }

    override fun getItemCount(): Int {
        return SongsDataObject.songFiles.size
    }
}