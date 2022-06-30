package com.androidpoet.materialintrodemo

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
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
        binding.materialIntroFragment.apply {
            setFragmentsList(list)
            enterAnimation = IntroAnimation.SharedAxisXForward
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



        if (Build.VERSION.SDK_INT >= 30) {

            // Root ViewGroup of my activity
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val decor: View = window.decorView
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            }
            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->

                val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

                // Apply the insets as a margin to the view. Here the system is setting
                // only the bottom, left, and right dimensions, but apply whichever insets are
                // appropriate to your layout. You can also update the view padding
                // if that's more appropriate.

                view.layoutParams = (view.layoutParams as FrameLayout.LayoutParams).apply {
                    leftMargin = insets.left
                    bottomMargin = insets.bottom
                    rightMargin = insets.right
                }

                // Return CONSUMED if you don't want want the window insets to keep being
                // passed down to descendant views.
                WindowInsetsCompat.CONSUMED
            }
        }
    
    }

    private fun setDotsTabLayout() {

        list.forEach { _ ->
            binding.tabLayout.addTab(binding.tabLayout.newTab())
        }
        binding.tabLayout.touchables.forEach { it.isEnabled = false }
    }


}


