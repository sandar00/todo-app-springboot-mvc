package s.d.todo.pojo;

public class ToDoPoJo {

    private String title;

    public ToDoPoJo(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Todopojo{Title => '+ title +'}";
    }
}
