
package com.androidpoet.materialintrodemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidpoet.materialintro.MaterialIntroView
import com.androidpoet.materialintro.MaterialIntroView.IndexEventListener
import com.androidpoet.materialintro.addFade
import com.androidpoet.materialintro.addMaterialFade
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

  private lateinit var materialIntroView: MaterialIntroView
  private lateinit var next: MaterialButton
  private lateinit var prev: MaterialButton
  private lateinit var tabLayout: TabLayout
  private var list: MutableList<Int> = arrayListOf()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    materialIntroView = findViewById(R.id.materialintroView)
    next = findViewById(R.id.next_button)
    prev = findViewById(R.id.back_button)
    list.add(R.layout.layout_one)
    list.add(R.layout.layout_two)
    list.add(R.layout.layout_three)

    tabLayout = findViewById(R.id.tab_layout)

    // /set default view with animation
    materialIntroView.defaultView(R.layout.layout_one, addFade())

    // /set list of views
    materialIntroView.setViewsList(list = list)

    setDotsTabLayout()

    // interface for the observing synonyms
    materialIntroView.setEventListener(object : IndexEventListener {
      override fun onIndexChanged(index: Int) {
        next.isEnabled = index < list.size - 1
        prev.isEnabled = index > 0
        tabLayout.apply {
          selectTab(getTabAt(index))
        }
      }
    })

    //  go next view with animation
    next.setOnClickListener {
      materialIntroView.next(addMaterialFade())
    }

    //  go previous view with animation
    prev.setOnClickListener {
      materialIntroView.previous(addMaterialFade())
    }
  }

  private fun setDotsTabLayout() {

    list.forEach { _ ->
      tabLayout.addTab(tabLayout.newTab())
    }
    tabLayout.touchables.forEach { it.isEnabled = false }
  }
}
