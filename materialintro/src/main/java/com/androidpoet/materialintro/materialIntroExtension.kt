
package com.androidpoet.materialintro

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.Scene
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialSharedAxis

//returns MaterialFade object for Activity
public fun AppCompatActivity.addMaterialFade(): MaterialFade {
  val materialFade = MaterialFade()
  materialFade.duration = 300
  return materialFade
}
//returns Fade object for Activity
public fun AppCompatActivity.addFade(): Fade {
  val fade = Fade()
  fade.duration = 300

  return fade
}
//returns SharedAxis object for Activity
public fun AppCompatActivity.addSharedAxis(
  axis: Int,
  forward: Boolean
): MaterialSharedAxis {
  val sharedAxis = MaterialSharedAxis(axis, forward)
  sharedAxis.duration = 300
  return sharedAxis
}
//returns MaterialFade object for Fragment
public fun Fragment.addMaterialFade(): MaterialFade {
  val materialFade = MaterialFade()
  materialFade.duration = 300

  return materialFade
}
//returns Fade object for Fragment
public fun Fragment.addFade(): Fade {
  val fade = Fade()
  fade.duration = 300

  return fade
}
//returns SharedAxis object for Fragment
public fun Fragment.addSharedAxis(
  axis: Int,
  forward: Boolean
): MaterialSharedAxis {
  val sharedAxis = MaterialSharedAxis(axis, forward)
  sharedAxis.duration = 300
  return sharedAxis
}
//go to next Scene with TransitionManager
public fun Context.showScene(
  @LayoutRes layoutId: Int,
  transition: Transition? = null,
  root: ViewGroup?
) {
  if (root == null) {
    return
  }

  val scene = Scene.getSceneForLayout(root, layoutId, this)

  transition?.let {
    TransitionManager.go(scene, it)
  } ?: run {
    TransitionManager.go(scene)
  }
}
