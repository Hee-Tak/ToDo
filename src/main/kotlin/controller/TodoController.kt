package controller

import model.Todo
import model.TodoList
import view.TodoView
import java.io.File
import java.io.FileWriter
import java.time.LocalDateTime
import java.util.*
import kotlin.math.min

class TodoController (private val list: TodoList, private val view: TodoView){

    fun run(){
        //임시 데이터 생성
        val todos = arrayOf(
            Todo("Maple Story"),
            Todo("Lost Ark"),
            Todo("GYM"),
            Todo("Study for exam"),
            Todo("Drink water"),
            Todo("Do homework"),
            Todo("Shower"),
            Todo("계획")
        )
        list.addTodo(todos)
        Show()              //view.printTodoList(todos) //뷰에 출력 요청

        while(true){
            print("choose 1-add 2-complete 3-delete 4-modify 5-change the order 6-show 7-quit : ")
            val input = readLine()      //!!.toInt()
            var quit = false
            when(input){
                "1" -> Add()
                "2" -> Check()
                "3" -> Delete(list)
                "4" -> Modify(list)
                "5" -> ChangeTheOrder()
                "6" -> Show()
                "7" -> {quit = Quit()}
                else -> {
                    println("Invalid number. Please enter a valid number. ")
                }
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
        print("Enter the number(or its title) of the check(or Uncheck) todo : ")
        val input = readLine()

        val todoToCheck = list.todos.find { it.title == input }

        if(input!!.toIntOrNull() != null){
            val todoNumber = input!!.toInt()
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
        print("Enter the title(or its number) you want to delete : ")
        val targetTitle = readLine()
        val todoToRemove = todoList.todos.find { it.title == targetTitle }
        if(targetTitle!!.toIntOrNull() != null){
            val number = targetTitle!!.toInt()
            if(number in 1..list.todos.size){
                list.removeTodo(list.todos[number-1])
                println("Todo deleted successfully ! ")
                Show()
            } else{
                println("Invalid input.")
            }
        } else {
            if(todoToRemove != null){
                todoList.removeTodo(todoToRemove)
                println("Todo deleted successfully ! ")
                Show()
            } else {
                println("Todo with the specified title not found")
            }
        }
    }

    private fun Modify(todoList: TodoList){
        print("Enter the title(or its number) you want to modify : ")
        val targetTitle = readLine()
        val todoToUpdate = todoList.todos.find{ it.title == targetTitle }
        if(targetTitle!!.toIntOrNull() != null){
            val number = targetTitle!!.toInt()
            if(number in 1..todoList.todos.size){
                print("Enter the new title : ")
                val newTitle = readLine()
                todoList.todos[number-1].title = newTitle ?: todoList.todos[number-1].title
                println("Todo title modified successfully ! ")
                Show()
            } else {
                println("Invalid input.")
            }
        } else {
            if(todoToUpdate != null){
                print("Enter the new title : ")
                val newTitle = readLine()
                todoToUpdate.title = newTitle ?: todoToUpdate.title
                println("Todo title modified successfully ! ")
                Show()
            } else {
                println("Todo with the specified title not found.")
            }
        }
    }

    private fun ChangeTheOrder(){
        print("Enter the first title (or its number) to change the order : ")
        //====================================================================
        val first = readLine()
        val firstTodo = list.todos.find{ it.title == first }
        if(first!!.toIntOrNull() != null && !(first!!.toInt() in 1..list.todos.size)){
            println("Invalid input.")
            return
        }
        if(!(first!!.toIntOrNull() != null)){
            if(firstTodo == null){
                println("Todo with the specified title not found.")
                return
            }
        }
        //=====================================================================
        val second = if(first!!.toIntOrNull() != null){   //이거 지금보니깐 first에 따라 올바른 입력값 유도만 한다 뿐이지 대충 맞으면 뭘해도 됨 first-숫자,second-title 조합도 가능하다는 뜻
            print("Enter the number of the second title to change the order : ")
            readLine()
        } else {
            print("Enter the second title to change the order : ")
            readLine()
        }
        val secondTodo = list.todos.find{ it.title == second }

        if(second!!.toIntOrNull() != null && !(second!!.toInt() in 1..list.todos.size)){
            println("Invalid input.")
            return
        }
        if(second!!.toIntOrNull() == null){
            val todoToChange = list.todos.find{ it.title == second }
            if(todoToChange == null){
                println("Todo with the specified title not found.")
                return
            }
        }
        //======================================================================

        val index1 = if(first!!.toIntOrNull() == null){ //숫자가 아니라면. 문자라면
            list.todos.indexOf(firstTodo)
        } else {
            first!!.toInt() - 1
        }

        val index2 = if(second!!.toIntOrNull() == null){
            list.todos.indexOf(secondTodo)
        } else {
            second!!.toInt() - 1
        }

        if(index1 == -1 || index2 == -1){ //안전장치 하나 더
            println("An error occurred while changing the order.")
            return
        }

        //======================================================================
        if (firstTodo != null && secondTodo != null) {
            list.todos[index1] = secondTodo
            list.todos[index2] = firstTodo
        } else {
            val temp = list.todos[index1]
            list.todos[index1] = list.todos[index2]
            list.todos[index2] = temp
        }

        Show()
        println("Completed the order exchange. ")
    }


    private fun Quit(): Boolean{
        print("If you want to exit, Enter 'quit' : ")
        val input = readLine()
        if(input.equals("quit", ignoreCase = true)) return true
        return false
    }

    private fun Show(){
        getCurrentDataTime() //일일Todo List가 핵심아이디어다 보니, 시간데이터를 가지고올 필요가 있었음
        view.printTodoList(list) //메인 기능
    }

    fun getCurrentDataTime() {
        val now = LocalDateTime.now()
        val hour = now.hour
        val minute = now.minute
        val second = now.second

        val dayOfWeek = now.dayOfWeek

        view.displayDateTime(now, dayOfWeek)
        view.displayDateTime(hour, minute, second, dayOfWeek)
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