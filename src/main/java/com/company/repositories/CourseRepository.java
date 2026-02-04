package com.company.repositories;

import com.company.models.Course;

// CourseRepository extends BaseRepository for common CRUD operations
public interface CourseRepository extends BaseRepository<Course, Integer> {
    
    // Get course description by id
    String getDescriptionById(int id);
    
    // Check if course is archived
    boolean isArchived(int id);
}
