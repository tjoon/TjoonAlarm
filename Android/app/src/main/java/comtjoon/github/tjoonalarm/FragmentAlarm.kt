package comtjoon.github.tjoonalarm

import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_alarm.*


class FragmentAlarm : Fragment() {

    var mDBHandler: DBHandler_Anko? = null
    var mAdapter: AlarmCursorRecyclerViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_alarm, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mDBHandler = DBHandler_Anko(activity!!)


        //recyclerview_alarm.adapter = AlarmRecyclerviewAdapter()
        //recyclerview_alarm.adapter = AlarmCursorRecyclerViewAdapter(null)
        //recyclerview_alarm.layoutManager = LinearLayoutManager(activity)

        val newOne: Cursor = mDBHandler!!.getAlarmAllWithCursor()
        if (newOne.count != 0) {
            mAdapter = AlarmCursorRecyclerViewAdapter(newOne)
            recyclerview_alarm.adapter = mAdapter
            recyclerview_alarm.layoutManager = LinearLayoutManager(activity)
        }

    }

    class AlarmCursorRecyclerViewAdapter(cursor: Cursor?) : CursorRecyclerViewAdapter<RecyclerView.ViewHolder>(cursor) {
        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, cursor: Cursor?) {
            var alarmItem: AlarmItem = AlarmItem.bindCursor(cursor!!)
            (viewHolder as CursorCustomViewHolder).setAlarmItem(alarmItem, cursor.position)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_alarm, parent, false)
            return CursorCustomViewHolder(view)
        }

        class AlarmItem {
            var time: String = ""
            var ampm: String = ""
            var sun: Int=0
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
                    alarmItem.time = cursor.getString(0)
                    alarmItem.ampm = cursor.getString(1)
                    alarmItem.sun = cursor.getInt(2)
                    alarmItem.mon = cursor.getString(3)
                    alarmItem.tue = cursor.getString(4)
                    alarmItem.wed = cursor.getString(5)
                    alarmItem.thu = cursor.getString(6)
                    alarmItem.fri = cursor.getString(7)
                    alarmItem.sat = cursor.getString(8)
                    alarmItem.run = cursor.getString(9)
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
            }

            fun setAlarmItem(item: AlarmItem, position: Int) {
                mTime!!.text = item.time
                mAMPM!!.text = item.ampm
            }
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