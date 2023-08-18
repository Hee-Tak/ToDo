package controller

import model.Todo
import model.TodoList
import view.TodoView
import java.util.*

class TodoController (private val list: TodoList, private val view: TodoView){

    fun run(){

        //임시 데이터 생성
        val todos = arrayOf(
            Todo("Maple Story"),
            Todo("Lost Ark"),
            Todo("GYM"),
            Todo("Study for exam"),
            Todo("Drink water")
        )


        list.addTodo(todos)
        //뷰에 출력 요청
        //view.printTodoList(todos)



        while(true){
            print("choose 1-add 2-complete 3-erase 4-quit : ")
            val input = readLine()      //!!.toInt()
            var quit = false
            when(input){
                "1" -> Add(list, view)
                "2" -> {}
                "3" -> {}
                "4" -> {quit = Quit()}
                else -> {}
            }
            if(quit) break

        }

    }


    fun Add(list: TodoList, view: TodoView){
        print("Enter a new todo : ")
        val input = readLine()
        val todo = Todo(input ?: "")
        list.addTodo(todo)
        view.printTodoList(list)
    }

    fun Completed(list: TodoList){
        print("Enter the number of the completed todo : ")
        val input = readLine()
        val todoNumber = input!!.toInt()
        if(todoNumber != null && todoNumber in 1..list.todos.size){
            val todo = list.todos[todoNumber-1]
            todo.completed = true
            view.printTodoList(list)
        } else {
            println("Invalid input. Please enter a valid number.")
        }
    }

    fun Quit(): Boolean{
        print("If you want to exit, Enter 'quit' : ")
        val input = readLine()
        if(input.equals("quit", ignoreCase = true)) return true
        return false
    }

}


fun main() {
    val todoList = TodoList()
    val todoView = TodoView()
    val todoController = TodoController(todoList, todoView)
    todoController.run()

}
