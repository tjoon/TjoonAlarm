package comtjoon.github.tjoonalarm

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_Name, null, DB_Version) {

    companion object {
        val DB_Name = "alarm.db"
        val DB_Version = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}