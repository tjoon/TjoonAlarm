package comtjoon.github.tjoonalarm

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.PRIMARY_KEY
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable

data class AlarmInfo(
        val id: String = "_id",
        val time: String = "No Time Set",
        val ampm: String = "Not AMPM Set",
        val sun: Int = 0,
        val mon: Int = 0,
        val tue: Int = 0,
        val wed: Int = 0,
        val thu: Int = 0,
        val fri: Int = 0,
        val sat: Int = 0,
        val run: Int = 0
)

class DBHandler_Anko(context: Context) : SQLiteOpenHelper(context, DB_Name, null, DB_Version) {

    companion object {
        val DB_Name = "alarm.db"
        val DB_Version = 1
    }

    object AlarmTable {
        val TABLE_NAME = "alarm"
        val ID = "_id"
        val TIME = "time"
        val AMPM = "ampm"
        val SUN = "sun"
        val MON = "mon"
        val TUE = "tue"
        val WED = "sed"
        val THU = "thu"
        val FRI = "fri"
        val SAT = "sat"
        val RUN = "run"
    }

    fun getAlarmAllWithCursor(): Cursor {
        return readableDatabase.query(AlarmTable.TABLE_NAME,
                arrayOf(AlarmTable.ID.toString(),
                        AlarmTable.TIME,
                        AlarmTable.AMPM,
                        AlarmTable.SUN.toString(),
                        AlarmTable.MON.toString(),
                        AlarmTable.TUE.toString(),
                        AlarmTable.WED.toString(),
                        AlarmTable.THU.toString(),
                        AlarmTable.FRI.toString(),
                        AlarmTable.SAT.toString(),
                        AlarmTable.RUN.toString()), null, null, null, null,AlarmTable.AMPM +", "+AlarmTable.ID  )
    }

    fun addAlarm(alarm: AlarmInfo) {
        var info = ContentValues()
        info.put(AlarmTable.ID, alarm.time)
        info.put(AlarmTable.TIME, alarm.time)
        info.put(AlarmTable.AMPM, alarm.ampm)
        info.put(AlarmTable.SUN, alarm.sun)
        info.put(AlarmTable.MON, alarm.mon)
        info.put(AlarmTable.TUE, alarm.tue)
        info.put(AlarmTable.WED, alarm.wed)
        info.put(AlarmTable.THU, alarm.thu)
        info.put(AlarmTable.FRI, alarm.fri)
        info.put(AlarmTable.SAT, alarm.sat)
        info.put(AlarmTable.RUN, alarm.run)

        writableDatabase.use {
            writableDatabase.insert(AlarmTable.TABLE_NAME, null, info)
        }
    }

    fun deleteAlarm(id: String) {
        writableDatabase.use {
            writableDatabase.execSQL(
                    "DELETE FROM ${AlarmTable.TABLE_NAME} WHERE ${AlarmTable.TIME} = ${id}"
            )
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.createTable(AlarmTable.TABLE_NAME, true,
                Pair(AlarmTable.ID, TEXT + PRIMARY_KEY),
                Pair(AlarmTable.TIME, TEXT),
                Pair(AlarmTable.AMPM, TEXT),
                Pair(AlarmTable.SUN, INTEGER),
                Pair(AlarmTable.MON, INTEGER),
                Pair(AlarmTable.TUE, INTEGER),
                Pair(AlarmTable.WED, INTEGER),
                Pair(AlarmTable.THU, INTEGER),
                Pair(AlarmTable.FRI, INTEGER),
                Pair(AlarmTable.SAT, INTEGER),
                Pair(AlarmTable.RUN, INTEGER)
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }


}