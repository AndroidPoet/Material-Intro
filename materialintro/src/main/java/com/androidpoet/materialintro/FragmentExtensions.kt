
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
  enterTransition = enterAnimation

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
