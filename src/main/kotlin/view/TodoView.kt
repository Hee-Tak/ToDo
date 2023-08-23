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


    fun displayDateTime(dateTime: LocalDateTime){
        val year = dateTime.year
        val month = dateTime.monthValue
        val day = dateTime.dayOfMonth
        val hour = dateTime.hour
        val minute = dateTime.minute
        val second = dateTime.second
        val dayOfWeek = when(dateTime.dayOfWeek) {
            DayOfWeek.MONDAY -> "월요일"
            DayOfWeek.TUESDAY -> "화요일"
            DayOfWeek.WEDNESDAY -> "수요일"
            DayOfWeek.THURSDAY -> "목요일"
            DayOfWeek.FRIDAY -> "금요일"
            DayOfWeek.SATURDAY -> "토요일"
            DayOfWeek.SUNDAY -> "일요일"
        }

        printBar2()
        println("\t\t* 현재 날짜 : ${year}년 ${month}월 ${day}일 \t*")
        println("\t\t* 현재 시각 : ${hour}시 ${minute}분 ${second}초\t*")
        println("\t\t*   요 일  : $dayOfWeek \t\t\t*")
        printBar2()
    }

    private fun printBar2(){
        println("\t\t*****************************")
    }

    fun printAction(){
        println("\t\t\t<Action>")
        println("1-추가\t2-수행완료\t3-삭제")
        println("4-수정\t5-순서변경\t6-현황보기")
        println("7-종료\t")
    }

}