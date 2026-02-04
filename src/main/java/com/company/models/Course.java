
package com.company.models;

import java.util.List;

// Represents a course
public class Course {
    public List<Lesson> lessons;
    public List<String> tags; // Added for tags

    public int id;
    public String title;
    public String description;
}
