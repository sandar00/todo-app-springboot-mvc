package s.d.todo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import s.d.todo.model.ToDo;
import s.d.todo.respo.TodoRepo;

@Service
public class TodoService {

    private final TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo){
        this.todoRepo = todoRepo;
    }
    
    public List<ToDo> all(){
        List<ToDo> todos = todoRepo.findAll();
        return todos;
    }

    public void add(String title){
        ToDo todo = new ToDo();
        todo.setTitle(title);
        todo.setCompleted(false);
        todoRepo.save(todo);
    }

    public ToDo getId(Long id){
        ToDo todo = todoRepo.findById(id).orElse(null);
        return todo;
    }

    public void modify(Long id, String title){
        ToDo todo = todoRepo.findById(id).orElse(null);
        if(todo != null){
            todo.setTitle(title);
            todoRepo.save(todo);
        }
    }

    public void drop(Long id){
        if(id != null){
            todoRepo.deleteById(id);
        }
    }

    public void toggle(Long id){
        ToDo todo = todoRepo.findById(id).orElse(null);

        if(todo != null){
            todo.setCompleted(!todo.isCompleted());
            todoRepo.save(todo);
        }
    }

    public List<ToDo> filter(boolean complete){

        List<ToDo> todoList = new ArrayList<ToDo>();
        
        List<ToDo> todos = todoRepo.findAll();
        for(ToDo td : todos){
            if(td.isCompleted() == complete){
                todoList.add(td);
            }
        }

        return todoList;
    }
}
