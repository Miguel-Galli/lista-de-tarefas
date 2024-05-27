package guedes.miguel.listatarefas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import guedes.miguel.listatarefas.adapter.TaskAdapter
import guedes.miguel.listatarefas.database.TaskDAO
import guedes.miguel.listatarefas.databinding.ActivityMainBinding
import guedes.miguel.listatarefas.model.Task

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listTask = emptyList<Task>()
    private var taskAdapter: TaskAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarTarefaActivity::class.java)
            startActivity(intent)

        }

        //RecyclerView
        taskAdapter = TaskAdapter(
            { task -> deleteConfirmation(task)},
            { task -> editTask(task)}
        )
        binding.rvList.adapter = taskAdapter

        binding.rvList.layoutManager = LinearLayoutManager(this)


    }

    private fun editTask(task: Task) {
        val intent = Intent(this, AdicionarTarefaActivity::class.java)
        intent.putExtra("task", task)
        startActivity(intent)

    }

    private fun deleteConfirmation(id: Int) {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Excluir Tarefa")
        alertBuilder.setMessage("Deseja excluir a tarefa?")

        alertBuilder.setPositiveButton("Sim") { _, _ ->
            val taskDAO = TaskDAO(this)
            if (taskDAO.delete(id)) {
                updateTasks()
                Toast.makeText(this, "Sucesso ao remover tarefa", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Erro ao remover tarefa", Toast.LENGTH_SHORT).show()
            }

        }

        alertBuilder.setNegativeButton("Não") { dialog, _ ->
            dialog.dismiss()
        }

        alertBuilder.create().show()
    }

    private fun updateTasks() {
        val taskDAO = TaskDAO(this)
        listTask = taskDAO.listar()
        taskAdapter?.updateTasks(listTask)
    }

    override fun onStart() {
        super.onStart()
        updateTasks()
    }


}