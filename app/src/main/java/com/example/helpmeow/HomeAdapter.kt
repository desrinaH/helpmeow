package com.example.helpmeow

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeAdapter(val context: Context, val userlist: List<CatDataPostItem>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val catName: TextView = itemView.findViewById(R.id.catName)
        private val catBreed: TextView = itemView.findViewById(R.id.catBreed)
        private val catDescription: TextView = itemView.findViewById(R.id.catDescription)
        private val catGender: TextView = itemView.findViewById(R.id.catGender)
        private val catUploader: TextView = itemView.findViewById(R.id.userName)
        private val catLocation: TextView = itemView.findViewById(R.id.catLocation)
        private val catPhoto: ImageView = itemView.findViewById(R.id.catPhoto)
        private val catRole: TextView = itemView.findViewById(R.id.userRole)
        private val catAction: ImageView = itemView.findViewById(R.id.catAction)
        private val catAction2: ImageView = itemView.findViewById(R.id.catAction2)
        private val catRescued: TextView = itemView.findViewById(R.id.catRescued)

        fun bind(catData: CatDataPostItem) {
            catName.text = catData.name
            catBreed.text = catData.breed
            catDescription.text = catData.description
            catGender.text = catData.gender
            catUploader.text = catData.upload_by_username
            catLocation.text = catData.location
            Glide.with(itemView).load(catData.photo).into(catPhoto)
            catRole.text = catData.role
            catRescued.text = catData.status

            val emailAddress = catData.upload_by_email ?: ""
            catAction.setOnClickListener {
                openGmail(emailAddress)
            }
            catAction2.setOnClickListener {
                Toast.makeText(itemView.context, "added to favorite", Toast.LENGTH_SHORT).show()
            }
        }

        private fun openGmail(emailAddress: String) {
            val subject = "HelpMeow Adoption Request"
            val message = "-This is a message from HelpMeow app-"

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("mailto:$emailAddress?subject=${Uri.encode(subject)}&body=${Uri.encode(message)}")

            itemView.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.cardview_home, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catData = userlist[position]
        holder.bind(catData)
    }
}