package controller

import model.Todo
import model.TodoList
import view.TodoView
import java.io.File
import java.io.FileWriter
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
            print("choose 1-add 2-complete 3-delete 4-modify 5-quit 6-show : ")
            val input = readLine()      //!!.toInt()
            var quit = false
            when(input){
                "1" -> Add()
                "2" -> Check()
                "3" -> Delete(list)
                "4" -> Modify(list)
                "5" -> {quit = Quit()}
                "6" -> Show()
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

    private fun Check(){
        print("Enter the number of the check(or Uncheck) todo : ")
        val input = readLine()
        val todoNumber = input!!.toInt()
        if(todoNumber != null && todoNumber in 1..list.todos.size){
            val todo = list.todos[todoNumber-1]
            todo.completed = !todo.completed
            view.printTodoList(list)
        } else {
            println("Invalid input. Please enter a valid number.")
        }
    }

    private fun Delete(todoList: TodoList){
        print("Enter the title you want to delete : ")
        val targetTitle = readLine()
        val todoToRemove = todoList.todos.find { it.title == targetTitle }
        if(todoToRemove != null){
            todoList.removeTodo(todoToRemove)
            println("Todo deleted successfully ! ")
        } else {
            println("Todo with the specified title not found. ")
        }
    }

    private fun Modify(todoList: TodoList){
        print("Enter the title you want to modify : ")
        val targetTitle = readLine()
        val todoToUpdate = todoList.todos.find{ it.title == targetTitle }
        if(todoToUpdate != null){
            print("Enter the new title : ")
            val newTitle = readLine()
            todoToUpdate.title = newTitle ?: todoToUpdate.title
            println("Todo title modified successfully ! ")
        } else {
            println("Todo with the specified title not found.")
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


fun saveDataToFile(todoList: TodoList){
    val file = File("todos.txt")
    val writer = FileWriter(file)

    for(todo in todoList.todos){
        writer.write("${todo.title},${todo.completed}\n")
    }

    writer.close()
}

fun loadDataFromFile(): TodoList {
    val file = File("todos.txt")
    val todoList = TodoList()

    if(file.exists()){
        val lines = file.readLines()
        for(line in lines){
            val (title, completed) = line.split(",")
            val todo = Todo(title, completed.toBoolean())
            todoList.addTodo(todo)
        }
    }

    return todoList
}