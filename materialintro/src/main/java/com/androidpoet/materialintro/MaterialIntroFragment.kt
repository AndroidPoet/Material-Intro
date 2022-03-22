
package com.androidpoet.materialintro

import android.content.Context
import android.content.ContextWrapper
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.androidpoet.metaphor.Metaphor
import com.androidpoet.metaphor.metaphorMaterialFadeInFragment
import com.androidpoet.metaphor.metaphorMaterialFadeThroughInFragment
import com.androidpoet.metaphor.metaphorMaterialSharedAxisInFragment

// /Custom root View for  layouts
public class MaterialIntroFragment(context: Context, attrs: AttributeSet) :
  FrameLayout(context, attrs) {
  // fragmentList list
  private var fragmentList = ArrayList<Fragment>()

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

    val v: View = inflate(context, R.layout.materialintro, this)
//
//        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MaterialIntroView)
//        attributes.recycle()
  }

  // go to previous view

  public fun previous(name: String?) {
    if (indexExists(fragmentList, index - 1)) {
      val currentIndex = index
      index -= 1

      showFragment(fragmentList[index], name)
    }

    mEventListener?.onIndexChanged(index)
  }
  // go to next view

  public fun next(name: String?) {

    if (indexExists(fragmentList, index + 1)) {
      val currentIndex = index
      index += 1
      showFragment(fragmentList[index], name)
    }
    mEventListener?.onIndexChanged(index)
  }

  // go to previous view

  public fun previousSharedAxis(axis: String, forword: Boolean) {
    if (indexExists(fragmentList, index - 1)) {
      val currentIndex = index
      index -= 1

      showFragment(fragmentList[index], axis, forword)
    }

    mEventListener?.onIndexChanged(index)
  }
  // go to next view

  public fun nextSharedAxis(axis: String, forword: Boolean) {

    if (indexExists(fragmentList, index + 1)) {
      val currentIndex = index
      index += 1
      showFragment(fragmentList[index], axis, forword)
    }
    mEventListener?.onIndexChanged(index)
  }

  private fun showFragment(fragment: Fragment, name: String?) {

    if (name == Animation.MaterialFade) {
      fragment.metaphorMaterialFadeInFragment()
    } else if (name == Animation.MaterialFadeThrough) {
      fragment.metaphorMaterialFadeThroughInFragment()
    }
    context.activity()?.supportFragmentManager?.commit {
      replace(R.id.fragment_container, fragment)
    }
  }

  private fun showFragment(fragment: Fragment, axis: String, forword: Boolean) {

    if (axis == Animation.AxisX) {
      fragment.metaphorMaterialSharedAxisInFragment(Metaphor.SharedX, forword)
    } else if (axis == Animation.AxisY) {
      fragment.metaphorMaterialSharedAxisInFragment(Metaphor.SharedY, forword)
    } else if (axis == Animation.AxisZ) {
      fragment.metaphorMaterialSharedAxisInFragment(Metaphor.SharedZ, forword)
    }
    context.activity()?.supportFragmentManager?.commit {
      replace(R.id.fragment_container, fragment)
    }
  }

  // set views list
  public fun setFragmentsList(list: List<Fragment>) {
    fragmentList.clear()
    fragmentList.addAll(list)
    if (fragmentList.isNotEmpty()) {
      context.activity()?.supportFragmentManager?.commit {
        add(R.id.fragment_container, fragmentList[0])
      }
    }
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
