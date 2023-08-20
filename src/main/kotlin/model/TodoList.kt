package model


class TodoList {
    val todos = mutableListOf<Todo>()

    fun addTodo(todo: Todo){
        todos.add(todo)
    }

    fun addTodo(todo: Array<Todo>){
        for(do1 in todo){
            todos.add(do1)
        }
    }

    fun removeTodo(todo: Todo){
        todos.remove(todo)
    }
}