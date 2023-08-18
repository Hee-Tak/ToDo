package view

import model.Todo

class TodoView {
    fun printTodoList(todos: Array<Todo>){
        println("Todo List: ")
        for(todo in todos){
            val status = if(todo.completed) "[O]" else "[ ]"
            println("$status ${todo.title}")
        }
    }
}