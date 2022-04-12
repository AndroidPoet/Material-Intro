
package com.androidpoet.materialintro

import android.content.Context
import android.content.ContextWrapper
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.Scene
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.androidpoet.metaphor.IntroAnimation
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis

/** applies Metaphor form attributes to a View instance. */
@JvmSynthetic
@PublishedApi
internal fun getIntroAnimation(animation: IntroAnimation): Transition? {

  when (animation) {
    IntroAnimation.FadeThrough -> {

      return MaterialFadeThrough()
    }

    IntroAnimation.Fade -> {
      return MaterialFade()
    }
    IntroAnimation.SharedAxisXForward -> {

      return MaterialSharedAxis(MaterialSharedAxis.X, true)
    }

    IntroAnimation.SharedAxisYForward -> {

      return MaterialSharedAxis(MaterialSharedAxis.Y, true)
    }

    IntroAnimation.SharedAxisZForward -> {

      return MaterialSharedAxis(MaterialSharedAxis.Z, true)
    }
    IntroAnimation.SharedAxisXBackward -> {

      return MaterialSharedAxis(MaterialSharedAxis.X, false)
    }

    IntroAnimation.SharedAxisYBackward -> {
      return MaterialSharedAxis(MaterialSharedAxis.Y, false)
    }

    IntroAnimation.SharedAxisZBackward -> {

      return MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }

    IntroAnimation.ElevationScale -> {
      return MaterialElevationScale(false)
    }
    IntroAnimation.ElevationScaleGrow -> {
      return MaterialElevationScale(true)
    }

    IntroAnimation.None -> {
      // trick for no animations
      return null
    }
  }
}

/*go to next Scene with TransitionManager*/
public fun showScene(
  @LayoutRes layoutId: Int,
  introAnimation: IntroAnimation,
  duration: Long,
  root: ViewGroup?
) {
  if (root == null) {
    return
  }
  val transition = getIntroAnimation(introAnimation)
  if (transition != null) {
    transition.duration = duration
  }
  val scene = Scene.getSceneForLayout(root, layoutId, root.context)
  transition?.let {
    TransitionManager.go(scene, transition)
  } ?: run {
    TransitionManager.go(scene)
  }
}
public fun Context.activity(): AppCompatActivity? = when (this) {
  is AppCompatActivity -> this
  else -> (this as? ContextWrapper)?.baseContext?.activity()
}
