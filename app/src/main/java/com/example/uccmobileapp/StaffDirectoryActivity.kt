package com.example.uccmobileapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uccmobileapp.R
import com.example.uccmobileapp.Staff
import com.example.uccmobileapp.StaffAdapter

class StaffDirectoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_directory)

        // Staff List
        val staffList = listOf(
            Staff("Mr. Peter Ndajah", "Dean, School of Mathematics, Science and Technology", "+1 876 906-3000", "headofschoolsmathit@ucc.edu.jm", R.drawable.staff_peter_ndajah),
            Staff("Mr. Otis Osbourne", "Head of Department", "+1 876 906-3000", "ithod@ucc.edu.jm", R.drawable.staff_otis_osbourne),
            Staff("Mr. Neil Williams", "IT Lecturer", "+1 876 906-3000", "itlecturer@ucc.edu.jm", R.drawable.staff_neil_williams),
            Staff("Mr. Aubryn Smith", "IT Lecturer", "+1 876 906-3000", "mathlecturer2@ucc.edu.jm", R.drawable.staff_aubryn_smith),
            Staff("Ms. Karen Wilson", "IT Lecturer", "+1 876 906-3000", "itlecturer2@ucc.edu.jm", R.drawable.staff_karen_wilson)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.staffRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = StaffAdapter(this, staffList)
    }
}