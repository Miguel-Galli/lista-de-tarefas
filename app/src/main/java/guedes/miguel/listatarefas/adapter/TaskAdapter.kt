package guedes.miguel.listatarefas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import guedes.miguel.listatarefas.databinding.ItemTarefaBinding
import guedes.miguel.listatarefas.model.Task

class TaskAdapter(
    val onClickDelete: (Int) -> Unit,
    val onClickEdit: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var tasks: List<Task> = emptyList()

    fun updateTasks(list: List<Task>) {
        this.tasks = list
        notifyDataSetChanged()
    }


    inner class TaskViewHolder(itemBinding: ItemTarefaBinding) : RecyclerView.ViewHolder(itemBinding.root) {

       private val binding: ItemTarefaBinding = itemBinding

       fun bind(task: Task){
           binding.textDescricao.text = task.description
           binding.textData.text = task.registerDate

           binding.btnExcluir.setOnClickListener {
               onClickDelete(task.idTask)
           }
           binding.btnEditar.setOnClickListener {
               onClickEdit(task)
           }

       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = ItemTarefaBinding.inflate(
            layoutInflater, parent, false
        )

        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {

        val task = tasks[position]
        holder.bind(task)

    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}