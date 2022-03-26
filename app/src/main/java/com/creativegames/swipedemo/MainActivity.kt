package com.creativegames.swipedemo

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.creativegames.swipedemo.databinding.ActivityItemDetailBinding
import com.creativegames.swipedemo.recyclerviewenhanced.OnActivityTouchListener
import com.creativegames.swipedemo.recyclerviewenhanced.RecyclerTouchListener


class MainActivity : AppCompatActivity(), RecyclerTouchListener.RecyclerTouchListenerHelper {

  private lateinit var appBarConfiguration: AppBarConfiguration

  private var touchListener: OnActivityTouchListener? = null

  override fun setOnActivityTouchListener(listener: OnActivityTouchListener?) {
    touchListener = listener;
  }

  override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
    touchListener?.getTouchCoordinates(ev)
    return super.dispatchTouchEvent(ev)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityItemDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_item_detail) as NavHostFragment
    val navController = navHostFragment.navController
    appBarConfiguration = AppBarConfiguration(navController.graph)
    setupActionBarWithNavController(navController, appBarConfiguration)
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment_item_detail)
    return navController.navigateUp(appBarConfiguration)
        || super.onSupportNavigateUp()
  }
}