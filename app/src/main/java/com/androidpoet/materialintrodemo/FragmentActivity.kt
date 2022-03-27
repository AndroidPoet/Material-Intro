
package com.androidpoet.materialintrodemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.androidpoet.materialintro.IntroAnimation
import com.androidpoet.materialintro.MaterialIntroFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout

class FragmentActivity : AppCompatActivity() {
  private lateinit var materialIntroView: MaterialIntroFragment
  private lateinit var next: MaterialButton
  private lateinit var prev: MaterialButton
  private lateinit var tabLayout: TabLayout
  private var list: MutableList<Fragment> = arrayListOf()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fragment)
    materialIntroView = findViewById(R.id.materialintroView)
    next = findViewById(R.id.next_button)
    prev = findViewById(R.id.back_button)

    list.add(FragmentOne())
    list.add(FragmentTwo())
    list.add(FragmentThree())

    tabLayout = findViewById(R.id.tab_layout)

    // /set list of views
    materialIntroView.setFragmentsList(list = list)

    setDotsTabLayout()

    // interface for the observing
    materialIntroView.setEventListener(object : MaterialIntroFragment.IndexEventListener {
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
      materialIntroView.next(IntroAnimation.MaterialSharedXAxisForeword)
    }

    //  go previous view with animation
    prev.setOnClickListener {
      materialIntroView.previous(IntroAnimation.MaterialSharedYAxisForeword)
    }
  }

  private fun setDotsTabLayout() {

    list.forEach { _ ->
      tabLayout.addTab(tabLayout.newTab())
    }
    tabLayout.touchables.forEach { it.isEnabled = false }
  }
}
