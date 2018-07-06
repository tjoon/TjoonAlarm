package comtjoon.github.tjoonalarm

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_set_alarm.*

class SetAlarmActivity : AppCompatActivity(), View.OnClickListener {

    val mDBHandler = DBHandler_Anko(this)

    var AMPM: Int = 0
    var SUN: Int = 0
    var MON: Int = 0
    var TUE: Int = 0
    var WED: Int = 0
    var THU: Int = 0
    var FRI: Int = 0
    var SAT: Int = 0

    var hour: Int? = null
    var min: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)
        supportActionBar!!.hide()

        alarm_save.setOnClickListener {
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hour=timepicker.hour
                min=timepicker.minute
            }*/
            save()
            Toast.makeText(this, hour.toString() + " : " + min.toString(), Toast.LENGTH_SHORT).show()
        }

        alarm_cancel.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.stay, R.anim.sliding_down)
        }

    }

    override fun onPause() {
        super.onPause()
        finish()
        overridePendingTransition(R.anim.stay, R.anim.sliding_down)

    }

    override fun onClick(v: View?) {
        var id = v!!.id
        var tv = findViewById<TextView>(v!!.id)

        when (id) {
            tv_sunday.id ->
                if (SUN == 1) {
                    tv.setTextColor(Color.parseColor("#AAAAAA"))
                    SUN = 0
                } else {
                    tv.setTextColor(Color.parseColor("#FF4081"))
                    SUN = 1
                }
            tv_monday.id ->
                if (MON == 1) {
                    tv.setTextColor(Color.parseColor("#AAAAAA"))
                    MON = 0
                } else {
                    tv.setTextColor(Color.parseColor("#FF4081"))
                    MON = 1
                }
            tv_tuesday.id ->
                if (TUE == 1) {
                    tv.setTextColor(Color.parseColor("#AAAAAA"))
                    TUE = 0
                } else {
                    tv.setTextColor(Color.parseColor("#FF4081"))
                    TUE = 1
                }
            tv_wednesday.id ->
                if (WED == 1) {
                    tv.setTextColor(Color.parseColor("#AAAAAA"))
                    WED = 0
                } else {
                    tv.setTextColor(Color.parseColor("#FF4081"))
                    WED = 1
                }
            tv_thursday.id ->
                if (THU == 1) {
                    tv.setTextColor(Color.parseColor("#AAAAAA"))
                    THU = 0
                } else {
                    tv.setTextColor(Color.parseColor("#FF4081"))
                    THU = 1
                }
            tv_friday.id ->
                if (FRI == 1) {
                    tv.setTextColor(Color.parseColor("#AAAAAA"))
                    FRI = 0
                } else {
                    tv.setTextColor(Color.parseColor("#FF4081"))
                    FRI = 1
                }
            tv_saturday.id ->
                if (SAT == 1) {
                    tv.setTextColor(Color.parseColor("#AAAAAA"))
                    SAT = 0
                } else {
                    tv.setTextColor(Color.parseColor("#FF4081"))
                    SAT = 1
                }

        }

    }

    fun save() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timepicker.hour
            min = timepicker.minute

        }
        if (hour!! >= 12) {
            Toast.makeText(this, "ampm오후", Toast.LENGTH_SHORT).show()
            AMPM = 2
            hour = hour!! - 12
        } else {
            Toast.makeText(this, "ampm오전", Toast.LENGTH_SHORT).show()
            AMPM = 1
        }

        val alarm: AlarmInfo = AlarmInfo(
                hour.toString() + min.toString(),
                hour.toString() + " : " + min.toString(),
                AMPM.toString(),
                SUN,
                MON,
                TUE,
                WED,
                THU,
                FRI,
                SAT)

        mDBHandler.addAlarm(alarm)
        mDBHandler.close()
        finish()
    }

}
