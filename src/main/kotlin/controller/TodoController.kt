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
            print("choose 1-add 2-complete 3-delete 4-modify 5-show 6-quit : ")
            val input = readLine()      //!!.toInt()
            var quit = false
            when(input){
                "1" -> Add()
                "2" -> Check()
                "3" -> Delete(list)
                "4" -> Modify(list)
                "5" -> Show()
                "6" -> {quit = Quit()}
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
        //view.printTodoList(list)
        Show()
    }

    private fun Check(){
        print("Enter the number(or title) of the check(or Uncheck) todo : ")
        val input = readLine()
        val todoNumber = input!!.toInt()
        val todoToCheck = list.todos.find { it.title == input }

        if(input!!.toIntOrNull() != null){
            if(todoNumber != null && todoNumber in 1..list.todos.size){
                val todo = list.todos[todoNumber-1]
                todo.completed = !todo.completed
                //view.printTodoList(list)
                Show()
            } else {
                println("Invalid input. Please enter a valid number.")
            }
        } else {
            if(todoToCheck != null){
                todoToCheck.completed = !todoToCheck.completed
                Show()
            } else {
                println("Todo not found. Please enter a valid title or number. ")
            }
        }


    }

    private fun Delete(todoList: TodoList){
        print("Enter the title(or the number) you want to delete : ")
        val targetTitle = readLine()
        val todoToRemove = todoList.todos.find { it.title == targetTitle }
        if(targetTitle!!.toIntOrNull() != null){
            val number = targetTitle!!.toInt()
            if(number in 1..list.todos.size){
                todoList.removeTodo(list.todos[number-1])
                println("Todo deleted successfully ! ")
            } else{
                println("Invalid input.")
            }
        } else {
            if(todoToRemove != null){
                todoList.removeTodo(todoToRemove)
                println("Todo deleted successfully ! ")
            } else {
                println("Todo with the specified title not found")
            }
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