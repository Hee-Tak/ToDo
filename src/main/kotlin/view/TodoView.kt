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
        printBar2()
        println("Current time : $dateTime")
        println("Day of the week : $dayOfWeek")
    }

    fun displayDateTime(hour: Int, minute: Int, second: Int, dayOfWeek: DayOfWeek){
        printBar2()
        println("Current time: $hour : $minute : $second ")
        println("Day of the week : $dayOfWeek ")
    }

    fun displayDateTime2(dateTime: LocalDateTime){
        val year = dateTime.year
        val month = dateTime.monthValue
        val day = dateTime.dayOfMonth
        val hour = dateTime.hour
        val minute = dateTime.minute
        val second = dateTime.second
        val dayOfWeek = dateTime.dayOfWeek

        printBar2()
        println("현재 날짜 : ${year}년 ${month}월 ${day}일")
        println("현재 시각 : ${hour}시 ${minute}분 ${second}초")
        println("Day of the week : $dayOfWeek ")
    }

    private fun printBar2(){
        println("================================================")
    }

}