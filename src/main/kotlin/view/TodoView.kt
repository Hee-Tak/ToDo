package view

import model.Todo
import model.TodoList
import java.time.DayOfWeek
import java.time.LocalDateTime

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


    fun displayDateTime(dateTime: LocalDateTime, dayOfWeek: DayOfWeek){
        println("Current time : $dateTime")
        println("Day of the week : $dayOfWeek")
    }

}