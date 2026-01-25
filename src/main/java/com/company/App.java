package com.company;

import com.company.db.PostgresDB;
import com.company.models.Course;
import com.company.models.Lesson;
import com.company.models.User;
import com.company.repositories.*;

import java.util.Scanner;

public class App {

    public void start() {

        PostgresDB db = new PostgresDB();

        CourseRepository courseRepo = new CourseRepositoryImpl(db);
        UserRepository userRepo = new UserRepositoryImpl(db);
        LessonRepository lessonRepo = new LessonRepositoryImpl(db);
        EnrollmentRepository enrollmentRepo = new EnrollmentRepositoryImpl(db);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== ONLINE LEARNING PLATFORM ===");
            System.out.println("1. Add course");
            System.out.println("2. List courses");
            System.out.println("3. Add user");
            System.out.println("4. List users");
            System.out.println("5. Add lesson to course");
            System.out.println("6. Enroll user to course");
            System.out.println("7. View enrolled courses for user");
            System.out.println("8. View lessons for a course");
            System.out.println("9. View course description");
            System.out.println("10. View enrolled course descriptions");
            System.out.println("0. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    Course c = new Course();
                    System.out.print("Title: ");
                    c.title = sc.nextLine();
                    System.out.print("Description: ");
                    c.description = sc.nextLine();
                    courseRepo.create(c);
                    break;

                case 2:
                    courseRepo.findAll()
                            .forEach(course ->
                                    System.out.println(course.id + " " + course.title));
                    break;

                case 3:
                    User u = new User();
                    System.out.print("Name: ");
                    u.name = sc.nextLine();
                    System.out.print("Email: ");
                    u.email = sc.nextLine();
                    userRepo.create(u);
                    break;

                case 4:
                    userRepo.findAll()
                            .forEach(user ->
                                    System.out.println(user.id + " " + user.name));
                    break;

                case 5:
                    Lesson l = new Lesson();
                    System.out.print("Course ID: ");
                    l.courseId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Lesson title: ");
                    l.title = sc.nextLine();
                    lessonRepo.create(l);
                    break;

                case 6:
                    System.out.print("User ID: ");
                    int userId = sc.nextInt();
                    System.out.print("Course ID: ");
                    int courseId = sc.nextInt();
                    enrollmentRepo.enroll(userId, courseId);
                    break;

                case 7:
                    System.out.print("User ID: ");
                    int uid = sc.nextInt();
                    enrollmentRepo.findCoursesByUserId(uid)
                            .forEach(course ->
                                    System.out.println(course.id + " " + course.title));
                    break;

                case 8:
                    System.out.print("Course ID: ");
                    int cid = sc.nextInt();
                    lessonRepo.findByCourseId(cid)
                            .forEach(lesson ->
                                    System.out.println(lesson.id + " " + lesson.title));
                    break;
                case 9:
                    System.out.print("Course ID: ");
                    int courseDescId = sc.nextInt();
                    sc.nextLine();
                    String desc = courseRepo.getDescriptionById(courseDescId);
                    System.out.println("Description: " + desc);
                    break;

                case 10:
                    System.out.print("User ID: ");
                    int userDescId = sc.nextInt();
                    enrollmentRepo.findCoursesByUserId(userDescId)
                        .forEach(course -> {
                            System.out.println("Course: " + course.title);
                            System.out.println("Description: " + course.description);
                            System.out.println("-------------------------");
                        });
                    break;

                case 0:
                    return;
            }
        }
    }
}
