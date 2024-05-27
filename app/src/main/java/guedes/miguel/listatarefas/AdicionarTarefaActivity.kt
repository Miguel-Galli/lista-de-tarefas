package guedes.miguel.listatarefas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import guedes.miguel.listatarefas.database.TaskDAO
import guedes.miguel.listatarefas.databinding.ActivityAdicionarTarefaBinding
import guedes.miguel.listatarefas.databinding.ActivityMainBinding
import guedes.miguel.listatarefas.model.Task

class AdicionarTarefaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdicionarTarefaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var task: Task? = null
        val bundle = intent.extras
        if (bundle != null) {
            task = bundle.getSerializable("task") as Task
            binding.editTarefa.setText(task.description)
        }

        binding.btnSalvar.setOnClickListener {

           if (binding.editTarefa.text.isNotEmpty()) {

               if (task != null) {
                   edit(task)
               } else {
                   save()
               }
           } else {
               Toast.makeText(
                   this,
                   "Preencha com uma tarefa",
                   Toast.LENGTH_SHORT
               ).show()
           }

        }
    }

    private fun edit(task: Task) {
        val description = binding.editTarefa.text.toString()
        val taskAtt = Task(
            task.idTask,
            description,
            "default"
        )

        val taskDAO = TaskDAO(this)
        taskDAO.update(taskAtt)
        Toast.makeText(
            this,
            "Tarefa Atualizada",
            Toast.LENGTH_SHORT
        ).show()
        finish()
    }

    private fun save() {
        val description = binding.editTarefa.text.toString()
        val task = Task(
            -1,
            description,
            "default"
        )

        val taskDAO = TaskDAO(this)
        if (taskDAO.save(task)) {
            Toast.makeText(
                this,
                "Tarefa salva com sucesso",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

}