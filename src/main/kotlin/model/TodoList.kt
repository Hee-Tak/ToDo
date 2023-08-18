package model

class TodoList {
    val todos = mutableListOf<Todo>()

    fun addTodo(todo: Todo){
        todos.add(todo)
    }
}