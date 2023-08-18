package view

import model.Todo
import model.TodoList

class TodoView {

    fun printTodoList(todoArray: Array<Todo>){
        printBar()
        printTitle()

        var count = 1
        for(todo in todoArray){
            val status = if(todo.completed) "[O]" else "[ ]"
            println("\t\t $count. $status ${todo.title}")
            count++
        }

        printBar()
    }

    fun printTodoList(todoList: TodoList){
        printBar()
        printTitle()

        var count = 1
        for(todo in todoList.todos){
            val status = if(todo.completed) "[O]" else "[ ]"
            println("\t\t $count. $status ${todo.title}")
            count++

        }

        printBar()
    }

    private fun printBar(){
        println("\t\t ===========================")
    }

    private fun printTitle(){
        println("\t\t\t\t <Todo List> ")
    }

}