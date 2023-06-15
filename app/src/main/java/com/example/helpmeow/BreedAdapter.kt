package com.example.helpmeow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class BreedAdapter(private val breedList: List<BreedItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return breedList.size
    }

    override fun getItem(position: Int): Any {
        return breedList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_info, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val breedItem = breedList[position]
        viewHolder.imageView.setImageResource(breedItem.imageResId)
        viewHolder.textView.text = breedItem.breed

        // Set click listener for the breed item
        view.setOnClickListener {
            val intent = Intent(parent?.context, BreedInfoActivity::class.java)
            intent.putExtra(BreedInfoActivity.EXTRA_BREED_ITEM, breedItem)
            parent?.context?.startActivity(intent)
        }

        return view
    }

    private class ViewHolder(view: View) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textView: TextView = view.findViewById(R.id.textView)
    }
}