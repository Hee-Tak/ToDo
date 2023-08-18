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
        Show()              //view.printTodoList(todos) //뷰에 출력 요청

        while(true){
            print("choose 1-add 2-complete 3-erase 4-quit 5-show : ")
            val input = readLine()      //!!.toInt()
            var quit = false
            when(input){
                "1" -> Add()
                "2" -> Completed()
                "3" -> {}
                "4" -> {quit = Quit()}
                "5" -> Show()
                else -> {}
            }
            if(quit) break

        }

    }


    private fun Add(){
        print("Enter a new todo : ")
        val input = readLine()
        val todo = Todo(input ?: "")
        list.addTodo(todo)
        view.printTodoList(list)
    }

    private fun Completed(){
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

    private fun Quit(): Boolean{
        print("If you want to exit, Enter 'quit' : ")
        val input = readLine()
        if(input.equals("quit", ignoreCase = true)) return true
        return false
    }

    private fun Show(){
        view.printTodoList(list)
    }

}


fun main() {
    val todoList = TodoList()
    val todoView = TodoView()
    val todoController = TodoController(todoList, todoView)
    todoController.run()

}
