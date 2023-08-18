package controller

import model.Todo
import view.TodoView

class TodoController (private val view: TodoView){

    fun run(){

        //임시 데이터 생성
        val todos = arrayOf(
            Todo("Maple Story"),
            Todo("Lost Ark"),
            Todo("GYM"),
            Todo("Study for exam"),
            Todo("Drink water")
        )


        //뷰에 출력 요청
        view.printTodoList(todos)

    }
}


fun main() {
    val todoView = TodoView()
    val todoController = TodoController(todoView)
    todoController.run()
}