package s.d.todo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import s.d.todo.model.ToDo;
import s.d.todo.pojo.ToDoPoJo;
import s.d.todo.services.TodoService;

import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.RequestParam;

// import jakarta.websocket.server.PathParam;


@Controller
@RequestMapping("/todos")
public class ToDoController {

    private final TodoService service;

    public ToDoController(TodoService todoService){

        this.service = todoService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<ToDo> todos = service.all();
        model.addAttribute("todos", todos);
        return "todoss/all";
    }
    
    @GetMapping("add")
    public String add() {
        return "todoss/add";
    }

    @PostMapping("add")
    public String create(@ModelAttribute ToDoPoJo todopojo) {
        service.add(todopojo.getTitle());
        return "redirect:/todos";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        ToDo todo = service.getId(id);
        model.addAttribute("todo", todo);
        return "todoss/edit";
    }

    @PostMapping("edit/{id}")
    public String editPost(@PathVariable Long id, @RequestParam String title) {
        service.modify(id, title);
        return "redirect:/todos";
    }

    @GetMapping("drop/{id}")
    public String delete(@PathVariable Long id) {
        // System.out.println("Delete todo "+ id);
        service.drop(id);
        return "redirect:/todos";
    }

    @GetMapping("toggle/{id}")
    public String toggle(@PathVariable Long id) {
        service.toggle(id);
        return "redirect:/todos";
    }

    @GetMapping("filter/{complete}")
    public String filterComplete(@PathVariable boolean complete, Model model) {
        List<ToDo> tds = service.filter(complete);
        model.addAttribute("todos", tds);
        return "todoss/all";
    }
}
