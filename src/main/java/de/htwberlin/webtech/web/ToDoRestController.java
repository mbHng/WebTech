package de.htwberlin.webtech.web;

import de.htwberlin.webtech.service.ToDoService;
import de.htwberlin.webtech.web.api.ToDo;
import de.htwberlin.webtech.web.api.ToDoManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ToDoRestController {

    private final ToDoService toDoService;

    public ToDoRestController (ToDoService toDoService){
        this.toDoService = toDoService;
    }

    @GetMapping(path ="/api/w1/todo")
    public ResponseEntity<List<ToDo>> fetchTask (){
        return ResponseEntity.ok(toDoService.findAll());
    }

    @GetMapping(path = "/api/w1/todo/{id}")
    public ResponseEntity<ToDo> fetchTaskById(@PathVariable Long id){
        var todo = toDoService.findById(id);
        return todo != null? ResponseEntity.ok(todo): ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/w1/todo")
    public ResponseEntity<Void> createTask(@RequestBody ToDoManipulationRequest request) throws URISyntaxException{
        var todo = toDoService.create(request);
        URI uri = new URI( "/api/w1/todo" + todo.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/w1/todo/{id}")
    public ResponseEntity<ToDo> updateTask(@PathVariable Long id, @RequestBody ToDoManipulationRequest request){
        var todo = toDoService.update(id, request);
        return todo != null? ResponseEntity.ok(todo): ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/w1/todo/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id){
        boolean successful = toDoService.deleteById(id);
        return successful? ResponseEntity.ok().build(): ResponseEntity.notFound().build();

    }
}
