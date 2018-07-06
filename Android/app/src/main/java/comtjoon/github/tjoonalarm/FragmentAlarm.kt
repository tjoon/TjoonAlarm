package comtjoon.github.tjoonalarm

import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_alarm.*
import kotlinx.android.synthetic.main.item_layout_alarm.*
import kotlinx.android.synthetic.main.item_layout_alarm.view.*

class FragmentAlarm : Fragment(), View.OnLongClickListener, View.OnClickListener {


    var mDBHandler: DBHandler_Anko? = null
    var mAdapter: AlarmCursorRecyclerViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_alarm, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mDBHandler = DBHandler_Anko(activity!!)

        val newOne: Cursor = mDBHandler!!.getAlarmAllWithCursor()

        mAdapter = AlarmCursorRecyclerViewAdapter(newOne)
        mAdapter!!.setOnItemClickListener(this)
        mAdapter!!.setOnItemLongClickListener(this)
        recyclerview_alarm.adapter = mAdapter
        recyclerview_alarm.layoutManager = LinearLayoutManager(activity)
    }

    override fun onResume() {
        super.onResume()
        mDBHandler = DBHandler_Anko(activity!!)

        val newOne: Cursor = mDBHandler!!.getAlarmAllWithCursor()

        mAdapter = AlarmCursorRecyclerViewAdapter(newOne)
        mAdapter!!.setOnItemClickListener(this)
        mAdapter!!.setOnItemLongClickListener(this)
        recyclerview_alarm.adapter = mAdapter
        recyclerview_alarm.layoutManager = LinearLayoutManager(activity)
    }


    override fun onClick(v: View?) {
        if (v!!.id == del_alarm.id) {
            deleteAlarm(v)
            Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "onClick", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onLongClick(v: View?): Boolean {
        Toast.makeText(context, "onLongClick", Toast.LENGTH_SHORT).show()
        return true
    }

    fun deleteAlarm(view: View) {
        mDBHandler!!.deleteAlarm(view.tag as String)
        val newOne = mDBHandler!!.getAlarmAllWithCursor()
        mAdapter?.changeCursor(newOne)
    }


    class AlarmCursorRecyclerViewAdapter(cursor: Cursor?) : CursorRecyclerViewAdapter<RecyclerView.ViewHolder>(cursor) {

        private var onItemLongClick: View.OnLongClickListener? = null
        private var onItemClick: View.OnClickListener? = null

        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, cursor: Cursor?) {
            var alarmItem: AlarmItem = AlarmItem.bindCursor(cursor!!)
            (viewHolder as CursorCustomViewHolder).setAlarmItem(alarmItem, cursor.position)
            viewHolder.mDelAlarm!!.setOnClickListener(onItemClick)
            viewHolder.mDelAlarm!!.tag = cursor.getString(0)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_alarm, parent, false)
            var holder = CursorCustomViewHolder(view.findViewById(R.id.del_alarm))
            view.setOnClickListener(onItemClick)
            view.setOnLongClickListener(onItemLongClick)
            view.checkBox.visibility = View.GONE
            view.tag = holder
            return CursorCustomViewHolder(view)
        }


        class AlarmItem {
            var time: String = ""
            var ampm: String = ""
            var sun: Int = 0
            var mon: String = ""
            var tue: String = ""
            var wed: String = ""
            var thu: String = ""
            var fri: String = ""
            var sat: String = ""
            var run: String = ""

            companion object {

                fun bindCursor(cursor: Cursor): AlarmItem {
                    val alarmItem = AlarmItem()
                    alarmItem.time = cursor.getString(1)
                    alarmItem.ampm = cursor.getString(2)
                    alarmItem.sun = cursor.getInt(3)
                    alarmItem.mon = cursor.getString(4)
                    alarmItem.tue = cursor.getString(5)
                    alarmItem.wed = cursor.getString(6)
                    alarmItem.thu = cursor.getString(7)
                    alarmItem.fri = cursor.getString(8)
                    alarmItem.sat = cursor.getString(9)
                    alarmItem.run = cursor.getString(10)
                    return alarmItem
                }
            }
        }

        class CursorCustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            var mTime: TextView? = null
            var mAMPM: TextView? = null
            var mSun: TextView? = null
            var mMon: TextView? = null
            var mTue: TextView? = null
            var mWed: TextView? = null
            var mThu: TextView? = null
            var mFri: TextView? = null
            var mSat: TextView? = null
            var mDelAlarm: ImageButton? = null

            init {
                mTime = view.findViewById(R.id.tv_time)
                mAMPM = view.findViewById(R.id.tv_ampm)
                mSun = view.findViewById(R.id.tv_sun)
                mMon = view.findViewById(R.id.tv_mon)
                mTue = view.findViewById(R.id.tv_tue)
                mWed = view.findViewById(R.id.tv_wed)
                mThu = view.findViewById(R.id.tv_thu)
                mFri = view.findViewById(R.id.tv_fri)
                mSat = view.findViewById(R.id.tv_sat)
                mDelAlarm = view.findViewById(R.id.del_alarm)

            }

            fun setAlarmItem(item: AlarmItem, position: Int) {
                mTime!!.text = item.time
                mAMPM!!.text = item.ampm
            }
        }

        fun setOnItemLongClickListener(l: View.OnLongClickListener) {
            onItemLongClick = l
        }

        fun setOnItemClickListener(l: View.OnClickListener) {
            onItemClick = l
        }

    }

    /*class AlarmRecyclerviewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var titles = arrayOf("제목1", "제목2", "제목3", "제목4", "제목5")
        var contents = arrayOf("내용1", "내용2", "내용3", "내용4", "내용5")

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_alarm, parent, false)
            return CustomViewHolder(view)
        }

        class CustomViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
            var imageview: ImageView? = null
            var tv1: TextView? = null
            var tv2: TextView? = null

            init {
                tv1 = view!!.findViewById<TextView>(R.id.textView1)
                tv2 = view!!.findViewById<TextView>(R.id.textView2)
            }
        }

        override fun getItemCount(): Int {
            return 5
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var view = holder as CustomViewHolder
            view.tv1!!.text = titles[position]
            view.tv2!!.text = contents[position]
        }

    }*/


}