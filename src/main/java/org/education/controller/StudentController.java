package org.education.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.education.entity.Course;
import org.education.entity.Student;
import org.education.service.CourseService;
import org.education.service.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    @GetMapping()
    public String openStudentProfile(Model model){
//        model.addAttribute("student", loggedInStudent);
        return "home-student";
    }
    @GetMapping("/chat")
    public String openchat()
    {
        return "chat";
    }

//    @GetMapping
//    public String getStudents(Model model){
//        model.addAttribute("students", studentService.getAllStudents());
//        return "students";
//    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Optional<Student> student = studentService.findById(id);
        model.addAttribute("student", student.get());
        return "update-student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, Student student){
        studentService.updateStudent(id, student.getFirstName(), student.getLastName(), student.getEmail());
        return "redirect:/students?update_success";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return "redirect:/students?delete_success";
    }

//    @GetMapping("/purchase")
//    public String addCourseToStudent(@RequestParam("studentEmail") String studentEmail,
//                                     @RequestParam("courseName") String courseName,
//                                     Model model,
//                                     @ModelAttribute("loggedInStudent") Student loggedInStudent) {
//        // Fetch the student from the database
//        Optional<Student> student = studentService.findByEmail(studentEmail);
//        Course course = courseService.getCourseDetailsService(courseName);
//        Set<Course> courses = student.get().getCourses();
//        // Add the fetched courses to the student's set of courses
//        if (courses == null) {
//            courses = new HashSet<>();
//            courses.add(course);
//        }else {
//            // If employees are already assigned, simply add the new employee to the set
//            courses.add(course);
//        }
//        model.addAttribute("student", loggedInStudent);
//        return "redirect:/students/home-student";
//    }
}
