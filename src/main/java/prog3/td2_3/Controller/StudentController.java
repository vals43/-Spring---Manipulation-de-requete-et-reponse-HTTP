package prog3.td2_3.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prog3.td2_3.model.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final List<Student> students = new ArrayList<>();

    // A) GET /welcome avec gestion erreur
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name) {

        if (name == null || name.isEmpty()) {
            return ResponseEntity
                    .status(400)
                    .body("Paramètre 'name' manquant");
        }

        return ResponseEntity
                .status(200)
                .body("Welcome " + name);
    }

    // B) POST /students
    @PostMapping("/students")
    public ResponseEntity<?> addStudents(@RequestBody List<Student> newStudents) {

        try {
            students.addAll(newStudents);

            return ResponseEntity
                    .status(201)
                    .body(students);

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Erreur serveur");
        }
    }

    // C) GET /students avec Accept
    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String accept) {

        try {
            if (accept == null) {
                return ResponseEntity
                        .status(400)
                        .body("Header Accept requis");
            }

            if (accept.equals("text/plain")) {
                return ResponseEntity
                        .status(200)
                        .header("Content-Type", "text/plain")
                        .body(students);
            }

            if (accept.equals("application/json")) {
                return ResponseEntity
                        .status(200)
                        .body(students);
            }

            return ResponseEntity
                    .status(501)
                    .body("Format non supporté");

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Erreur serveur");
        }
    }
}