package comtjoon.github.tjoonalarm

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        tjoon_viewPager.adapter=TjoonViewPagerAdapter(supportFragmentManager)
        tabLayout.addTab(tabLayout.newTab().setText("알람"))
        tabLayout.addTab(tabLayout.newTab().setText("타이머"))
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tjoon_viewPager.setCurrentItem(tab!!.position)
            }

        })

        tjoon_viewPager.addOnPageChangeListener(object:TabLayout.TabLayoutOnPageChangeListener(tabLayout){})

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    class TjoonViewPagerAdapter(fm:FragmentManager):FragmentStatePagerAdapter(fm){

        var fragments=arrayOf(FragmentAlarm(), FragmentTimer())

        // 화면을 넣어주는 부분
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        // 페이지 개수를 새는 부분
        override fun getCount(): Int {
            return fragments.size
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
