package guedes.miguel.listatarefas.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper(context: Context): SQLiteOpenHelper(
    context, NAME_DATA_BASE, null, VERSION
) {

    companion object {
        const val NAME_DATA_BASE = "TaskList.db"
        const val VERSION = 1
        const val TABLE_TASK_NAME = "task"
        const val COLUMN_ID_TASK = "id_task"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_REGISTER_DATE = "register_date"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val sql = "CREATE TABLE IF NOT EXISTS $TABLE_TASK_NAME (" +
                "$COLUMN_ID_TASK INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_DESCRIPTION VARCHAR(70)," +
                "$COLUMN_REGISTER_DATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                ");"

        try {
            db?.execSQL(sql)
            Log.i("info_db", "SUCESSO AO CRIAR TABELA")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("info_db", "SUCESSO AO CRIAR TABELA")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}