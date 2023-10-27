package com.example.BookHUB

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var DrawerLayout: DrawerLayout
    lateinit var CoordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout:FrameLayout
    lateinit var navigationView: NavigationView
    var previousMenuItem:MenuItem?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DrawerLayout=findViewById(R.id.Drawer)
        CoordinatorLayout=findViewById(R.id.coordinator)
        toolbar=findViewById(R.id.toolbar)
        frameLayout=findViewById(R.id.framelayout)
        navigationView=findViewById(R.id.navigation)
        setuptoolbar()
        openDashboard()

        val actionBarDrawerToggle=ActionBarDrawerToggle(this@MainActivity,DrawerLayout,R.string.opening_drawer,R.string.close_drawer)
        DrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
       navigationView.setNavigationItemSelectedListener {
           if(previousMenuItem!=null){
               previousMenuItem?.isCheckable=false
           }
           it.isCheckable=true
           it.isChecked=true
           previousMenuItem=it;
           when(it.itemId){
               R.id.dashboard->{
                  // Toast.makeText(this@MainActivity,"Clicked on Dashboar",Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction()

                    openDashboard()
               DrawerLayout.closeDrawers()}

               R.id.favourite->{
                 //  Toast.makeText(this@MainActivity,"Clicked on favourites",Toast.LENGTH_SHORT).show()
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.framelayout,Favourites_Fragment())
                      // .addToBackStack("favourites")
                       .commit()
                   supportActionBar?.title="Favourites"
                   DrawerLayout.closeDrawers()}

               R.id.About->{
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.framelayout,AboutFragment())
                      // .addToBackStack("About")
                       .commit()
                   supportActionBar?.title="About App"
                   DrawerLayout.closeDrawers()


                  // Toast.makeText(this@MainActivity,"Clicked on AboutPhone",Toast.LENGTH_SHORT).show()
               }
               R.id.Profile->{
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.framelayout,ProfileFragment())
                      // .addToBackStack("Profile")
                       .commit()
                   supportActionBar?.title="Profile"
                   DrawerLayout.closeDrawers()
                   //Toast.makeText(this@MainActivity,"Clicked on Profile",Toast.LENGTH_SHORT).show()
               }
           }
           return@setNavigationItemSelectedListener true
       }


    }
   fun setuptoolbar(){
        setSupportActionBar(toolbar)
       supportActionBar?.title="BOOK HUB"
       supportActionBar?.setHomeButtonEnabled(true)
       supportActionBar?.setDisplayHomeAsUpEnabled(true)



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==android.R.id.home){
            DrawerLayout.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }
    fun openDashboard(){
        val fragment=DashboardFragment()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout,fragment)
        transaction.commit()
        supportActionBar?.title="DashBoard"
        navigationView.setCheckedItem(R.id.dashboard)


    }

    override fun onBackPressed() {
        val frag=supportFragmentManager.findFragmentById(R.id.framelayout)
        when(frag){
            !is DashboardFragment->openDashboard()
            else->super.onBackPressed()
        }
    }

}