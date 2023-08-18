package view

import model.Todo
import model.TodoList

class TodoView {

    fun printTodoList(todoArray: Array<Todo>){
        println("Todo List: ")
        var count = 1
        for(todo in todoArray){
            val status = if(todo.completed) "[O]" else "[ ]"
            println("$count. $status ${todo.title}")
            count++
        }
    }

    fun printTodoList(todoList: TodoList){
        println("Todo List: ")
        var count = 1
        for(todo in todoList.todos){
            val status = if(todo.completed) "[O]" else "[ ]"
            println("$count. $status ${todo.title}")
            count++

        }
    }

}