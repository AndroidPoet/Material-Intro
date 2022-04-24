
package com.androidpoet.materialintrodemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.androidpoet.materialintro.MaterialIntroFragment
import com.androidpoet.materialintro.materialIntroFragment
import com.androidpoet.materialintrodemo.databinding.ActivityFragmentBinding
import com.androidpoet.metaphor.IntroAnimation

class FragmentActivity : AppCompatActivity() {
  private lateinit var binding: ActivityFragmentBinding
  private var list: MutableList<Fragment> = arrayListOf()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityFragmentBinding.inflate(layoutInflater)
    setContentView(binding.root)

    list.add(FragmentOne())
    list.add(FragmentTwo())
    list.add(FragmentThree())





    val meta = MaterialIntroFragment.Builder(this)
      .setEnterAnimation(IntroAnimation.Fade)
      .setExitAnimation(IntroAnimation.Fade)
      .setReenterAnimation(IntroAnimation.SharedAxisXBackward)
      .setReturnAnimation(IntroAnimation.SharedAxisXForward)
      .setEnterDuration(300)
      .setExitDuration(300)
      .setReturnDuration(300)
      .setReenterDuration(300)
      .setEnterOverlap(true)
      .setEnterOverlap(true)
      .build()



    meta.next()
    meta.previous()
    meta.setFragmentsList(list)


    binding.materialIntroFragment.apply {
      setFragmentsList(list)
      enterAnimation = IntroAnimation.SharedAxisXForward
      reenterAnimation = IntroAnimation.SharedAxisXBackward
      enterDuration = 500
      exitDuration = 500
    }

    setDotsTabLayout()

    binding.materialIntroFragment.setOnIndexChangeListener {
      binding.nextButton.isEnabled = it < list.size - 1
      binding.backButton.isEnabled = it > 0
      binding.tabLayout.apply {
        selectTab(getTabAt(it))
      }
    }

//  go next view with animation
    binding.nextButton.setOnClickListener {
      binding.materialIntroFragment.next()
    }

//  go previous view with animation
    binding.backButton.setOnClickListener {
      binding.materialIntroFragment.previous()
    }
  }

  private fun setDotsTabLayout() {

    list.forEach { _ ->
      binding.tabLayout.addTab(binding.tabLayout.newTab())
    }
    binding.tabLayout.touchables.forEach { it.isEnabled = false }
  }
}
