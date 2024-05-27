package guedes.miguel.listatarefas.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import guedes.miguel.listatarefas.model.Task

class TaskDAO(context: Context): ITaskDAO {

    private val write = DataBaseHelper(context).writableDatabase
    private val read = DataBaseHelper(context).readableDatabase

    override fun save(task: Task): Boolean {

        val contentValues = ContentValues()
        contentValues.put(DataBaseHelper.COLUMN_DESCRIPTION, task.description)

        try {
            write.insert(
                DataBaseHelper.TABLE_TASK_NAME,
                null,
                contentValues
            )
            Log.i("info_db", "SUCESSO AO SALVAR TASK")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("info_db", "ERRO AO SALVAR TASK")
            return false
        }

        return true

    }

    override fun update(task: Task): Boolean {
        val args = arrayOf(task.idTask.toString())
        val contentValues = ContentValues()
        contentValues.put(DataBaseHelper.COLUMN_DESCRIPTION, task.description)

        try {
            write.update(
                DataBaseHelper.TABLE_TASK_NAME,
                contentValues,
                "${DataBaseHelper.COLUMN_ID_TASK} = ?",
                args
            )
            Log.i("info_db", "SUCESSO AO DELETAR TASK")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("info_db", "ERRO AO DELETAR TASK")
            return false
        }

        return true
    }

    override fun delete(idTask: Int): Boolean {
        val args = arrayOf(idTask.toString())
        try {
            write.delete(
                DataBaseHelper.TABLE_TASK_NAME,
                "${DataBaseHelper.COLUMN_ID_TASK} = ?",
                args
            )
            Log.i("info_db", "SUCESSO AO DELETAR TASK")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("info_db", "ERRO AO DELETAR TASK")
            return false
        }

        return true
    }

    override fun listar(): List<Task> {

        val list = mutableListOf<Task>()
        val sql =  "SELECT ${DataBaseHelper.COLUMN_ID_TASK}, " +
                "${DataBaseHelper.COLUMN_DESCRIPTION}, " +
                "    strftime('%d/%m/%Y %H:%M', ${DataBaseHelper.COLUMN_REGISTER_DATE}) ${DataBaseHelper.COLUMN_REGISTER_DATE} " +
                "FROM ${DataBaseHelper.TABLE_TASK_NAME}"

        val cursor = read.rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex(DataBaseHelper.COLUMN_ID_TASK)
        val indiceDescription = cursor.getColumnIndex(DataBaseHelper.COLUMN_DESCRIPTION)
        val indiceDaata = cursor.getColumnIndex(DataBaseHelper.COLUMN_REGISTER_DATE)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(indiceId)
            val description = cursor.getString(indiceDescription)
            val data = cursor.getString(indiceDaata)

            val task = Task(id, description, data)
            list.add(task)
        }

        return list
    }


}