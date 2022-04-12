
package com.androidpoet.materialintrodemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidpoet.materialintrodemo.databinding.ActivityMainBinding
import com.androidpoet.metaphor.IntroAnimation

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private var list: ArrayList<Int> = arrayListOf()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    list.add(R.layout.layout_one)
    list.add(R.layout.layout_two)
    list.add(R.layout.layout_three)

    with(binding.materialintroView) {
      setViewsList(list)
      nextAnimation = IntroAnimation.Fade
      previousAnimation = IntroAnimation.Fade
      nextDuration = 500
      previousDuration = 500
    }

    setDotsTabLayout()

    binding.materialintroView.setOnIndexChangeListener {
      binding.nextButton.isEnabled = it < list.size - 1
      binding.backButton.isEnabled = it > 0
      binding.tabLayout.apply {
        selectTab(getTabAt(it))
      }
    }

    //  go next view with animation
    binding.nextButton.setOnClickListener {
      binding.materialintroView.next()
    }

    //  go previous view with animation
    binding.backButton.setOnClickListener {
      binding.materialintroView.previous()
    }
  }

  private fun setDotsTabLayout() {

    list.forEach { _ ->
      binding.tabLayout.addTab(binding.tabLayout.newTab())
    }
    binding.tabLayout.touchables.forEach { it.isEnabled = false }
  }
}
