
package com.androidpoet.materialintro

import android.content.Context
import android.content.ContextWrapper
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.transition.Scene
import androidx.transition.Transition
import androidx.transition.TransitionManager

// /Custom root View for  layouts
public class MaterialIntroView(context: Context, attrs: AttributeSet) :
  FrameLayout(context, attrs) {
  // fragmentList list
  private var fragmentList = ArrayList<Fragment>()

  // views list
  private var layoutList = ArrayList<Int>()

  // current view index
  private var index: Int = 0

  // IndexEvent interface

  public interface IndexEventListener {
    public fun onIndexChanged(index: Int)
  }

  // Index change EventListener
  private var mEventListener: IndexEventListener? = null

  init {
    // inflate(context, R.layout.intro_view, this)
//
//        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MaterialIntroView)
//        attributes.recycle()
  }

  // go to previous view

  public fun previous(transition: Transition?) {
    if (indexExists(layoutList, index - 1)) {
      val currentIndex = index
      index -= 1

      context.showScene(layoutList[index], transition, this)
    }

    mEventListener?.onIndexChanged(index)
  }
  // go to next view

  public fun next(transition: Transition?) {

    if (indexExists(layoutList, index + 1)) {
      val currentIndex = index
      index += 1
      context.showScene(layoutList[index], transition, this)
    }
    mEventListener?.onIndexChanged(index)
  }

  // set views list
  public fun setViewsList(list: List<Int>) {
    layoutList.clear()
    layoutList.addAll(list)
    val s = Scene.getSceneForLayout(this, layoutList[0], context)
    TransitionManager.go(s)
  }

//    //set default view to show
//    public fun defaultView(scene: Int, transition: Transition?) {
//        context.showScene(scene, transition, this)
//    }

  // set EventListener
  public fun setEventListener(mEventListener: IndexEventListener?) {
    this.mEventListener = mEventListener
  }

  // check index is valid or not
  private fun indexExists(list: List<*>, index: Int): Boolean {
    return index >= 0 && index < list.size
  }

  public fun Context.activity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    else -> (this as? ContextWrapper)?.baseContext?.activity()
  }
}
