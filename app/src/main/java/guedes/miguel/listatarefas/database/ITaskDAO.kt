package guedes.miguel.listatarefas.database

import guedes.miguel.listatarefas.model.Task

interface ITaskDAO {

    fun save(task: Task): Boolean
    fun update(task: Task): Boolean
    fun delete(idTask: Int): Boolean
    fun listar(): List<Task>

}