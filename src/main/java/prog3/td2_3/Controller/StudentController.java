package prog3.td2_3.Controller;

import org.springframework.web.bind.annotation.*;
import prog3.td2_3.model.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final List<Student> students = new ArrayList<>();

    // A) GET /welcome?name=xxx
    @GetMapping("/welcome")
    public String welcome(@RequestParam String name) {
        return "Welcome " + name;
    }

    // B) POST /students
    @PostMapping("/students")
    public List<String> addStudents(@RequestBody List<Student> newStudents) {

        students.addAll(newStudents);

        // retourner noms
        return students.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .toList();
    }

    @GetMapping("/students")
    public Object getStudents(@RequestHeader(value = "Accept", required = false) String accept) {

        if (accept == null || accept.equals("text/plain")) {
            return students.stream()
                    .map(s -> s.getFirstName() + " " + s.getLastName())
                    .toList();
        } else {
            return "Format non supporté";
        }
    }
}