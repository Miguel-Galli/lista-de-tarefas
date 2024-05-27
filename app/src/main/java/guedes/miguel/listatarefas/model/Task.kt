package guedes.miguel.listatarefas.model

import java.io.Serializable

data class Task(
    val idTask: Int,
    val description: String,
    val registerDate: String

) : Serializable