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
        view.printTodoList(todos)

    }
}


fun main() {
    val todoList = TodoList()
    val todoView = TodoView()
    val todoController = TodoController(todoList, todoView)
    todoController.run()

    while(true){
        print("Enter a new todo (or 'quit' to exit): ")
        val input = readLine()
        if(input.equals("quit", ignoreCase = true)){
            break
        }

        val todo = Todo(input ?: "")
        todoList.addTodo(todo)
        todoView.printTodoList(todoList)
    }

}