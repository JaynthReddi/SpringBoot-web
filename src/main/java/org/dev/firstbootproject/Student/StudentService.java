package org.dev.firstbootproject.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentModel> getStudents() {
        return studentRepository.findAll();

    }

    public void addNewStudent(StudentModel studentModel) {
        Optional<StudentModel> studentModelsByEmail = studentRepository.findStudentModelsByEmail(studentModel.getEmail());
        if(studentModelsByEmail.isPresent())
        {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(studentModel);
    }

    public void deleteStudent(long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with Id "+ studentId +" does not exists ");
        }
        studentRepository.deleteById(studentId);

    }

@Transactional
    public void updateStudent(Long studentId, String name, String email) {
    StudentModel studentModel = studentRepository.findById(studentId)
            .orElseThrow(()-> new IllegalStateException("student with id "+ studentId + "does not exist"));


    if(name !=null && name.length()>0 && !Objects.equals(studentModel.getName(),name)){
        studentModel.setName(name);
    }

    if(email !=null && email.length()>0 && !Objects.equals(studentModel.getEmail(),email)){
        Optional<StudentModel> studentModelsByEmail = studentRepository.findStudentModelsByEmail(email);

        if(studentModelsByEmail.isPresent())
        {
            throw new IllegalStateException("email is already Taken");
        }
        studentModel.setEmail(email);
    }
}
}
