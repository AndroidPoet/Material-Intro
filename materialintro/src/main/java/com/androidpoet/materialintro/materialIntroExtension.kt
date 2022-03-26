
package com.androidpoet.materialintro

import android.content.Context
import android.content.ContextWrapper
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.Fade
import androidx.transition.Scene
import androidx.transition.Transition
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis

/**
 *go to next Scene with TransitionManager
 */
public fun Context.showScene(
  @LayoutRes layoutId: Int,
  transition: Transition? = null,
  root: ViewGroup?
) {
  if (root == null) {
    return
  }

  val transitionSet = TransitionSet()
  if (transition != null) {
    transitionSet.addTransition(transition)
  }

  val scene = Scene.getSceneForLayout(root, layoutId, this)
  transition?.let {
    TransitionManager.go(scene, transitionSet)
  } ?: run {
    TransitionManager.go(scene)
  }
}

/**
 *returns MaterialFade object for Context
 */

public fun Context.addMaterialFade(): MaterialFade {
  val materialFade = MaterialFade()
  materialFade.duration = 300
  return materialFade
}

/**
 *returns addMaterialFadeThrough object for Context
 */
public fun Context.addMaterialFadeThrough(): MaterialFadeThrough {
  val materialFade = MaterialFadeThrough()
  materialFade.duration = 300
  return materialFade
}

/**
 *returns Fade object for Context
 */
public fun Context.addFade(): Fade {
  val fade = Fade()
  fade.duration = 300

  return fade
}

/**
 *returns SharedAxis object for Context
 */
public fun Context.addSharedAxis(
  axis: Int,
  forward: Boolean
): MaterialSharedAxis {
  val sharedAxis = MaterialSharedAxis(axis, forward)
  sharedAxis.duration = 300
  return sharedAxis
}

/**
 *returns Transition object for Context
 */
public fun Context.getTransition(animation: IntroAnimation): Transition {
  when (animation) {
    is IntroAnimation.MaterialFade -> {
      return addMaterialFade()
    }
    is IntroAnimation.MaterialFadeThrough -> {
      return addMaterialFadeThrough()
    }

    is IntroAnimation.MaterialSharedXAxisForeword -> {
      return addSharedAxis(MaterialSharedAxis.X, true)
    }

    is IntroAnimation.MaterialSharedYAxisForeword -> {
      return addSharedAxis(MaterialSharedAxis.Y, true)
    }

    is IntroAnimation.MaterialSharedZAxisForeword -> {
      return addSharedAxis(MaterialSharedAxis.Z, true)
    }

    is IntroAnimation.MaterialSharedXAxisBackward -> {
      return addSharedAxis(MaterialSharedAxis.X, false)
    }

    is IntroAnimation.MaterialSharedYAxisBackward -> {
      return addSharedAxis(MaterialSharedAxis.Y, false)
    }

    is IntroAnimation.MaterialSharedZAxisBackward -> {
      return addSharedAxis(MaterialSharedAxis.Z, false)
    }
  }
}
/**
 * get context from Activity
 */

public fun Context.activity(): AppCompatActivity? = when (this) {
  is AppCompatActivity -> this
  else -> (this as? ContextWrapper)?.baseContext?.activity()
}
