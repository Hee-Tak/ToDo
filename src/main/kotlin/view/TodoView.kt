package view

import model.Todo
import model.TodoList

class TodoView {

    var count = 1
    fun printTodoList(todoArray: Array<Todo>){
        println("Todo List: ")
        for(todo in todoArray){
            val status = if(todo.completed) "[O]" else "[ ]"
            println("$count. $status ${todo.title}")
            //count++
            counting()
        }
    }

    fun printTodoList(todoList: TodoList){
        println("Todo List: ")
        for(todo in todoList.todos){
            val status = if(todo.completed) "[O]" else "[ ]"
            println("$count. $status ${todo.title}")
            //count++
            counting()
        }
    }

    fun counting(){
        this.count = count+1
    }


}