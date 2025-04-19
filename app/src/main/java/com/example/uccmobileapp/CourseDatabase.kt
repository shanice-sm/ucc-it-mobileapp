package com.example.uccmobileapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATABASE_NAME = "courses.db" // Name of the databse
const val TABLE_COURSES = "courses"
const val COL_CODE = "code" // Column for course code
const val COL_NAME = "name" // Cp;umn for course name
const val COL_CREDITS = "credits"
const val COL_PREREQUISITES = "prerequisites"
const val COL_DESCRIPTION = "description"

class CourseDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 2) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_COURSES (
                $COL_CODE TEXT PRIMARY KEY,
                $COL_NAME TEXT,
                $COL_CREDITS TEXT,
                $COL_PREREQUISITES TEXT,
                $COL_DESCRIPTION TEXT 
            )
        """.trimIndent()

        db?.execSQL(createTable)
        insertSampleCourses(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_COURSES")
        onCreate(db)
    }

    private fun insertSampleCourses(db: SQLiteDatabase?) {
        val courses = listOf(
            arrayOf("ITT107", "Computer Information Essentials", "3", "None", "Learn basic computer skills."),
            arrayOf("ITT103", "Programming Techniques", "3", "ITT107", "Learn basic python programming techniques."),
            arrayOf("ITT208", "Internet Authoring 1", "3", "ITT103", "Build websites with HTML, CSS, and JavaScript."),
            arrayOf("ITT200", "Object Oriented Programming using C++", "3", "ITT103", "Build software using object-oriented principles."),
            arrayOf("ITT203", "Data Structures and File Mgt. 1", "3", "ITT200", "Learn how to use stacks, queues, trees, and graphs."),
            arrayOf("ITT420", "Mobile App Development", "3", "None", "Develop Android apps using Java or Kotlin."),
            arrayOf("ITT302", "Operating Systems", "3", "None", "Explore OS concepts like memory, processes, and file systems."),
            arrayOf("ITT201", "Data Communications & Networking 1", "3", "ITT107", "Understand core networking principles, data transmission, and communication protocols."),
            arrayOf("ITT210", "Database Management Systems", "3", "ITT103", "Explore relational databases, SQL, and database design concepts."),
            arrayOf("ITT313", "Fundamentals of Computer Graphic Design", "3", "ITT107", "Learn design principles, visual communication, and digital image editing tools.")
        )

        courses.forEach { course ->
            val values = ContentValues().apply {
                put(COL_CODE, course[0])
                put(COL_NAME, course[1])
                put(COL_CREDITS, course[2])
                put(COL_PREREQUISITES, course[3])
                put(COL_DESCRIPTION, course[4])
            }
            db?.insert(TABLE_COURSES, null, values)
        }
    }

    // Retrieves all courses from the databse
    fun getAllCourses(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_COURSES", null)
    }
}
