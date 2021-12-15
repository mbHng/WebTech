package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.ToDoEntity;
import de.htwberlin.webtech.persistence.ToDoRepository;
import de.htwberlin.webtech.web.api.ToDo;
import de.htwberlin.webtech.web.api.ToDoManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService (ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }
    public List<ToDo> findAll(){
        List<ToDoEntity> todos = toDoRepository.findAll();
        return todos.stream().map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public ToDo findById(Long id){
        var toDoEntity = toDoRepository.findById(id);
        return toDoEntity.map(this::transformEntity).orElse(null);
    }

    public ToDo create(ToDoManipulationRequest request){
        var toDoEntity = new ToDoEntity(request.getTask(), request.getDueTo(), request.isCompleted());
       toDoEntity = toDoRepository.save(toDoEntity);
       return transformEntity(toDoEntity);
    }

    public ToDo update(Long id, ToDoManipulationRequest request){
        var todoEntityOptional = toDoRepository.findById(id);
        if (todoEntityOptional.isEmpty()){
            return null;
        }

        var todoEntity = todoEntityOptional.get();
        todoEntity.setTask(request.getTask());
        todoEntity.setDueTo(request.getDueTo());
        todoEntity.setCompleted(request.isCompleted());
        toDoRepository.save(todoEntity);

        return transformEntity(todoEntity);
    }

    public boolean deleteById(Long id){
        if (!toDoRepository.existsById(id)){
            return false;
        }
        toDoRepository.deleteById(id);
        return true;
    }

    private ToDo transformEntity (ToDoEntity toDoEntity){
        return new ToDo(
                toDoEntity.getId(),
                toDoEntity.getTask(),
                toDoEntity.getDueTo(),
                toDoEntity.getCompleted()
        );
    }
}
