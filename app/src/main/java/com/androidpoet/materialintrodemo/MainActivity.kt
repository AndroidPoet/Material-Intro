
package com.androidpoet.materialintrodemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidpoet.materialintro.MaterialIntroView
import com.androidpoet.materialintrodemo.databinding.ActivityMainBinding
import com.androidpoet.metaphor.IntroAnimation

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private var list: MutableList<Int> = mutableListOf()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    list.add(R.layout.layout_one)
    list.add(R.layout.layout_two)
    list.add(R.layout.layout_three)

    val meta = MaterialIntroView.Builder(this)
      .setViewsList(list)
      .setNextAnimation(IntroAnimation.Fade)
      .setPreviousAnimation(IntroAnimation.Fade)
      .setEnterDuration(300)
      .setExitDuration(300)
      .build()

    binding.root.addView(meta)
    setDotsTabLayout()
    // binding.materialintroView.setViewsList(list)

    //  go next view with animation
    binding.nextButton.setOnClickListener {
      meta.next()
    }

    //  go previous view with animation
    binding.backButton.setOnClickListener {
      meta.previous()
    }

    meta.setOnIndexChangeListener {
      binding.nextButton.isEnabled = it < list.size - 1
      binding.backButton.isEnabled = it > 0
      binding.tabLayout.apply {
        selectTab(getTabAt(it))
      }
    }

//    with(binding.materialintroView) {
//      nextAnimation = IntroAnimation.Fade
//      previousAnimation = IntroAnimation.Fade
//      nextDuration = 500
//      previousDuration = 500
//
//
//      setViewsList(list)
//
//      //  go next view with animation
//      binding.nextButton.setOnClickListener {
//       next()
//      }
//
//      //  go previous view with animation
//      binding.backButton.setOnClickListener {
//        previous()
//      }
//
//     setOnIndexChangeListener {
//        binding.nextButton.isEnabled = it < list.size - 1
//        binding.backButton.isEnabled = it > 0
//        binding.tabLayout.apply {
//          selectTab(getTabAt(it))
//        }
//      }
//
//    }
  }

  private fun setDotsTabLayout() {

    list.forEach { _ ->
      binding.tabLayout.addTab(binding.tabLayout.newTab())
    }
    binding.tabLayout.touchables.forEach { it.isEnabled = false }
  }
}
