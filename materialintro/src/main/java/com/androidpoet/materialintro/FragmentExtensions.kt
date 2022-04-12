
package com.androidpoet.materialintro

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.transition.Transition

/** applies Animation form attributes to a View instance. */
@JvmSynthetic
internal fun Fragment.applyAnimation(
  materialIntroFragment: MaterialIntroFragment
) {

  val enterAnimation =
    getIntroAnimation(materialIntroFragment.enterAnimation)?.let { addAnimationProperties(it, materialIntroFragment, materialIntroFragment.enterDuration) }
  val exitAnimation =
    getIntroAnimation(materialIntroFragment.exitAnimation)?.let { addAnimationProperties(it, materialIntroFragment, materialIntroFragment.exitDuration) }
  val reenterAnimation =
    getIntroAnimation(materialIntroFragment.reenterAnimation)?.let { addAnimationProperties(it, materialIntroFragment, materialIntroFragment.reenterDuration) }
  val returnAnimation =
    getIntroAnimation(materialIntroFragment.returnAnimation)?.let { addAnimationProperties(it, materialIntroFragment, materialIntroFragment.returnDuration) }

  enterTransition = enterAnimation
  exitTransition = exitAnimation
  reenterTransition = reenterAnimation
  returnTransition = returnAnimation
}

/** applies Properties on Animation form attributes. */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@JvmSynthetic
internal fun Fragment.addAnimationProperties(
  transition: Transition,
  materialIntro: MaterialIntroFragment,
  animationDuration: Long
): Transition {
  transition.apply {
    duration = animationDuration
    allowEnterTransitionOverlap = materialIntro.enterTransitionOverlap
    allowReturnTransitionOverlap = materialIntro.returnTransitionOverlap
  }
  return transition
}
