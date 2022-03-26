
package com.androidpoet.materialintro

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.androidpoet.metaphor.Metaphor
import com.androidpoet.metaphor.metaphorMaterialFadeThroughInFragment
import com.androidpoet.metaphor.metaphorMaterialSharedAxisInFragment

/**
 *Custom root View for  layouts
 */
public class MaterialIntroFragment(context: Context, attrs: AttributeSet) :
  FrameLayout(context, attrs) {

  /**
   *fragmentList list
   */
  private var fragmentList = ArrayList<Fragment>()

  /**
   *current view index
   */
  private var index: Int = 0

  /**
   * IndexEvent interface
   */

  public interface IndexEventListener {
    public fun onIndexChanged(index: Int)
  }

  /**
   * Index change EventListener
   */
  private var mEventListener: IndexEventListener? = null

  /**
   * go to previous fragment
   */
  public fun previous(name: IntroAnimation) {
    if (indexExists(fragmentList, index - 1)) {
      index -= 1

      showFragment(fragmentList[index], name)
    }

    mEventListener?.onIndexChanged(index)
  }

  /**
   * go to next fragment
   */

  public fun next(name: IntroAnimation) {

    if (indexExists(fragmentList, index + 1)) {
      index
      index += 1
      showFragment(fragmentList[index], name)
    }
    mEventListener?.onIndexChanged(index)
  }

  /**
   * set views list
   */

  public fun setFragmentsList(list: List<Fragment>) {
    fragmentList.clear()
    fragmentList.addAll(list)
    if (fragmentList.isNotEmpty()) {
      context.activity()?.supportFragmentManager?.commit {
        add(R.id.fragment_container, fragmentList[0])
      }
    }
  }

  /**
   * set EventListener
   */

  public fun setEventListener(mEventListener: IndexEventListener?) {
    this.mEventListener = mEventListener
  }

  /**
   * check index is valid or not
   */
  private fun indexExists(list: List<*>, index: Int): Boolean {
    return index >= 0 && index < list.size
  }

  /**
   * show Fragment with with animation
   */

  private fun showFragment(fragment: Fragment, introAnimation: IntroAnimation) {

    if (introAnimation == IntroAnimation.MaterialFadeThrough) {
      fragment.metaphorMaterialFadeThroughInFragment()
    } else if (introAnimation == IntroAnimation.MaterialFade) {
      fragment.metaphorMaterialFadeThroughInFragment()
    } else if (introAnimation == IntroAnimation.MaterialSharedXAxisForeword) {
      fragment.metaphorMaterialSharedAxisInFragment(Metaphor.SharedX, true)
    } else if (introAnimation == IntroAnimation.MaterialSharedYAxisForeword) {
      fragment.metaphorMaterialSharedAxisInFragment(Metaphor.SharedY, true)
    } else if (introAnimation == IntroAnimation.MaterialSharedZAxisForeword) {
      fragment.metaphorMaterialSharedAxisInFragment(Metaphor.SharedZ, true)
    } else if (introAnimation == IntroAnimation.MaterialSharedXAxisBackward) {
      fragment.metaphorMaterialSharedAxisInFragment(Metaphor.SharedX, false)
    } else if (introAnimation == IntroAnimation.MaterialSharedYAxisBackward) {
      fragment.metaphorMaterialSharedAxisInFragment(Metaphor.SharedY, false)
    } else if (introAnimation == IntroAnimation.MaterialSharedZAxisBackward) {
      fragment.metaphorMaterialSharedAxisInFragment(Metaphor.SharedZ, false)
    }

    /**
     * replace fragment with currant fragment
     */
    context.activity()?.supportFragmentManager?.commit {
      replace(R.id.fragment_container, fragment)
    }
  }
}
