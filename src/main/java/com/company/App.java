package com.company;

import com.company.db.PostgresDB;
import com.company.models.Course;
import com.company.models.Lesson;
import com.company.models.Page;
import com.company.models.User;
import com.company.repositories.*;
import com.company.controllers.CourseController;
import com.company.config.PlatformConfig;
import com.company.factories.LessonFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public void start() {

        PostgresDB db = new PostgresDB();

        CourseRepository courseRepo = new CourseRepositoryImpl(db);
        UserRepository userRepo = new UserRepositoryImpl(db);
        LessonRepository lessonRepo = new LessonRepositoryImpl(db);
        EnrollmentRepository enrollmentRepo = new EnrollmentRepositoryImpl(db);
        CourseController courseController = new CourseController(courseRepo);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== ONLINE LEARNING PLATFORM ===");
            System.out.println("1. Add course (using Builder)");
            System.out.println("2. List courses");
            System.out.println("3. List courses (paginated)");
            System.out.println("4. Add user");
            System.out.println("5. List users");
            System.out.println("6. Add lesson to course (using Factory)");
            System.out.println("7. Enroll user to course");
            System.out.println("8. View enrolled courses for user");
            System.out.println("9. View lessons for a course");
            System.out.println("10. View course description");
            System.out.println("11. View enrolled course descriptions");
            System.out.println("12. View Platform Config (Singleton)");
            System.out.println("0. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Description: ");
                    String description = sc.nextLine();
                    System.out.print("Tags (comma-separated): ");
                    String tagsInput = sc.nextLine();
                    List<String> tags = new ArrayList<>();
                    if (!tagsInput.isEmpty()) {
                        for (String tag : tagsInput.split(",")) {
                            tags.add(tag.trim());
                        }
                    }
                    
                    courseController.createCourse(title, description, new ArrayList<>(), tags);
                    System.out.println("Course created successfully!");
                    break;

                case 2:
                    courseRepo.findAll()
                            .forEach(course ->
                                    System.out.println(course.id + " " + course.title));
                    break;

                case 3:
                    System.out.print("Page number (1-indexed): ");
                    int pageNum = sc.nextInt();
                    System.out.print("Page size: ");
                    int pageSize = sc.nextInt();
                    List<Course> allCourses = courseRepo.findAll();
                    int totalItems = allCourses.size();
                    int totalPages = (int) Math.ceil((double) totalItems / pageSize);
                    int startIdx = (pageNum - 1) * pageSize;
                    int endIdx = Math.min(startIdx + pageSize, totalItems);
                    
                    List<Course> pagedCourses = allCourses.subList(startIdx, endIdx);
                    Page<Course> coursePage = new Page<>(pagedCourses, totalItems, totalPages, pageNum);
                    
                    System.out.println("Total items: " + coursePage.getTotalItems());
                    System.out.println("Total pages: " + coursePage.getTotalPages());
                    System.out.println("Current page: " + coursePage.getCurrentPage());
                    System.out.println("Items on this page:");
                    coursePage.getItems().forEach(course ->
                            System.out.println(course.id + " " + course.title));
                    break;

                case 4:
                    User u = new User();
                    System.out.print("Name: ");
                    u.name = sc.nextLine();
                    System.out.print("Email: ");
                    u.email = sc.nextLine();
                    userRepo.create(u);
                    break;

                case 5:
                    userRepo.findAll()
                            .forEach(user ->
                                    System.out.println(user.id + " " + user.name));
                    break;

                case 6:
                    System.out.print("Course ID: ");
                    int courseId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Lesson type (Video/Text/Quiz): ");
                    String lessonType = sc.nextLine();
                    System.out.print("Lesson title: ");
                    String lessonTitle = sc.nextLine();
                    
                    try {
                        Lesson l = LessonFactory.createLesson(lessonType);
                        l.courseId = courseId;
                        l.title = lessonTitle;
                        lessonRepo.create(l);
                        System.out.println("Lesson created successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.print("User ID: ");
                    int userId = sc.nextInt();
                    System.out.print("Course ID: ");
                    int cId = sc.nextInt();
                    enrollmentRepo.enroll(userId, cId);
                    break;

                case 8:
                    System.out.print("User ID: ");
                    int uid = sc.nextInt();
                    enrollmentRepo.findCoursesByUserId(uid)
                            .forEach(course ->
                                    System.out.println(course.id + " " + course.title));
                    break;

                case 9:
                    System.out.print("Course ID: ");
                    int cid = sc.nextInt();
                    lessonRepo.findByCourseId(cid)
                            .forEach(lesson ->
                                    System.out.println(lesson.id + " " + lesson.title));
                    break;

                case 10:
                    System.out.print("Course ID: ");
                    int courseDescId = sc.nextInt();
                    sc.nextLine();
                    String desc = courseRepo.getDescriptionById(courseDescId);
                    System.out.println("Description: " + desc);
                    break;

                case 11:
                    System.out.print("User ID: ");
                    int userDescId = sc.nextInt();
                    enrollmentRepo.findCoursesByUserId(userDescId)
                        .forEach(course -> {
                            System.out.println("Course: " + course.title);
                            System.out.println("Description: " + course.description);
                            System.out.println("-------------------------");
                        });
                    break;

                case 12:
                    PlatformConfig config = PlatformConfig.getInstance();
                    System.out.println("Platform Config Instance: " + config);
                    System.out.println("Using Singleton pattern for PlatformConfig");
                    break;

                case 0:
                    return;
            }
        }
    }
}
