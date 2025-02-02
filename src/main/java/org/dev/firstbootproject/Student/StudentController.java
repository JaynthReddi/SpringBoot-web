package org.dev.firstbootproject.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<StudentModel> getStudents() {

        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody StudentModel studentModel) {
        studentService.addNewStudent(studentModel);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") long studentId) {
        studentService.deleteStudent(studentId);

    }

    @PutMapping(path = "{studentId}")
    public void UpdateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }
}

