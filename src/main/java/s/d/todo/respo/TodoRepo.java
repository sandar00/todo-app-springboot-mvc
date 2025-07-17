package s.d.todo.respo;

import org.springframework.data.jpa.repository.JpaRepository;

import s.d.todo.model.ToDo;

public interface TodoRepo extends JpaRepository<ToDo, Long> {

}
