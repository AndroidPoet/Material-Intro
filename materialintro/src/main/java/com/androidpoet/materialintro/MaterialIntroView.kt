
package com.androidpoet.materialintro

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.transition.Scene
import androidx.transition.TransitionManager

/**
 * Custom root View for  layouts
 */
public class MaterialIntroView(context: Context, attrs: AttributeSet) :
  FrameLayout(context, attrs) {

  /**
   * views list
   */
  private var layoutList = ArrayList<Int>()

  /**
   * current view index
   */
  private var index: Int = 0

  /**
   * IndexEvent interface
   */

  public interface IndexEventListener {
    public fun onIndexChanged(index: Int)
  }

  /**
   *  Index change EventListener
   */

  private var mEventListener: IndexEventListener? = null

  /**
   *  go to previous view
   */

  public fun previous(animation: IntroAnimation) {
    if (indexExists(layoutList, index - 1)) {
      index -= 1

      context.showScene(layoutList[index], context.getTransition(animation), this)
    }

    mEventListener?.onIndexChanged(index)
  }

  /**
   * go to next view
   */

  public fun next(animation: IntroAnimation) {
    if (indexExists(layoutList, index + 1)) {
      index += 1
      context.showScene(layoutList[index], context.getTransition(animation), this)
    }
    mEventListener?.onIndexChanged(index)
  }

  /**
   * set views list
   */

  public fun setViewsList(list: List<Int>) {
    layoutList.clear()
    layoutList.addAll(list)
    val s = Scene.getSceneForLayout(this, layoutList[0], context)
    TransitionManager.go(s)
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
}
