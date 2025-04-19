package com.example.uccmobileapp;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CoursesActivity extends AppCompatActivity {
    private LinearLayout courseList;
    private CourseDatabase dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        Log.d("CoursesActivity", "Activity Loaded Successfully");

        courseList = findViewById(R.id.courseList);
        if (courseList == null) {
            Log.e("CoursesActivity", "courseList is NULL!");
        } else {
            Log.d("CoursesActivity", "onCreate: courseList view found.");
        }

        dbHelper = new CourseDatabase(this);

        loadCourses();
    }

    private void loadCourses() {
        Log.d("CoursesActivity", "loadCourses: Attempting to load courses...");

        Cursor cursor = dbHelper.getAllCourses();
        if (cursor == null) {
            Log.e("CoursesActivity", "loadCourses: Cursor is NULL!");
            return;
        }

        Log.d("CoursesActivity", "loadCourses: Cursor count = " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                try {
                    Log.d("CoursesActivity", "loadCourses: Inflating course_data layout...");
                    View courseView = getLayoutInflater().inflate(R.layout.course_data, null);

                    // Bind views
                    TextView code = courseView.findViewById(R.id.courseCode);
                    TextView name = courseView.findViewById(R.id.courseName);
                    TextView credits = courseView.findViewById(R.id.courseCredits);
                    TextView prerequisites = courseView.findViewById(R.id.coursePrerequisites);
                    TextView desc = courseView.findViewById(R.id.courseDescription);

                    // Extract data from cursor
                    String courseCode = cursor.getString(cursor.getColumnIndexOrThrow("code"));
                    String courseName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    int courseCredits = cursor.getInt(cursor.getColumnIndexOrThrow("credits"));
                    String coursePrerequisites = cursor.getString(cursor.getColumnIndexOrThrow("prerequisites"));
                    String courseDesc = cursor.getString(cursor.getColumnIndexOrThrow("description"));

                    // Log course details
                    Log.d("CoursesActivity", "Course Loaded: " + courseCode + " - " + courseName);

                    // Populate views with data
                    code.setText(courseCode);
                    name.setText(courseName);
                    credits.setText("Credits: " + courseCredits);
                    prerequisites.setText("Prerequisites: " + coursePrerequisites);
                    desc.setText(courseDesc);

                    // Add the courseView to the courseList
                    courseList.addView(courseView);

                } catch (Exception e) {
                    Log.e("CoursesActivity", "loadCourses: Error adding a course view", e);
                }
            } while (cursor.moveToNext());
        } else {
            Log.w("CoursesActivity", "loadCourses: No courses found in database.");
        }

        cursor.close();
    }
}

