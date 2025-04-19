package com.example.uccmobileapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.core.view.isGone

class StaffAdapter(private val context: Context, private val staffList: List<Staff>) :
    RecyclerView.Adapter<StaffAdapter.StaffViewHolder>() {

    inner class StaffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val staffImage: ImageView = itemView.findViewById(R.id.staffImage)
        val staffName: TextView = itemView.findViewById(R.id.staffName)
        val staffPosition: TextView = itemView.findViewById(R.id.staffPosition)
        val staffPhone: TextView = itemView.findViewById(R.id.staffPhone)
        val staffEmail: TextView = itemView.findViewById(R.id.staffEmail)
        val collapsedView: View = itemView.findViewById(R.id.collapsedView)
        val expandedView: View = itemView.findViewById(R.id.expandedView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.staff_item, parent, false)
        return StaffViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
        val staff = staffList[position]

        // Set collapsed view data
        holder.staffImage.setImageResource(staff.imageResId)
        holder.staffName.text = staff.name
        holder.staffPosition.text = staff.position

        // Set expanded view data
        holder.staffPhone.text = "Phone: ${staff.phone}"
        holder.staffEmail.text = "Email: ${staff.email}"

        // Handle card click to toggle visibility
        holder.collapsedView.setOnClickListener {
            if (holder.expandedView.isGone) {
                holder.expandedView.visibility = View.VISIBLE
            } else {
                holder.expandedView.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return staffList.size
    }
}