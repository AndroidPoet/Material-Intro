
package com.androidpoet.materialintro

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.androidpoet.metaphor.IntroAnimation

@DslMarker
internal annotation class MaterialFragmentInlineDsl

/**
 * Creates an instance of the [MaterialIntroFragment] by scope of the [MaterialIntroFragment.Builder] using kotlin dsl.
 *
 * @param Context A context for creating resources of the [MaterialIntroFragment].
 * @param block A dsl scope lambda from the [MaterialIntroFragment.Builder].
 * */
@MainThread
@JvmSynthetic
@MaterialFragmentInlineDsl
public inline fun materialIntroFragment(
  context: Context,
  crossinline block: MaterialIntroFragment.Builder.() -> Unit
): MaterialIntroFragment =
  MaterialIntroFragment.Builder(context).apply(block).build()

/**
 * MetaphorFragment implements material motion animations.
 *
 * @see [MaterialIntroFragment](https://github.com/AndroidPoet/Metaphor)
 *
 * @param builder A [MaterialIntroFragment.Builder] for creating an instance of the [MaterialIntroFragment].
 */
public class MaterialIntroFragment : FrameLayout {

  /** fragments list. */

  public var fragmentList: MutableList<Fragment> = mutableListOf()

  /**current fragment index */
  private var index: Int = 0

  /** duration of enter the animations. */
  public var enterDuration: Long = 300

  /** duration of reenter the animations. */
  public var reenterDuration: Long = 300

  /** duration of exit the animations. */
  public var exitDuration: Long = 300

  /** duration of return the animations. */
  public var returnDuration: Long = 300

  /** Enter Animation of  fragment. */
  public var enterAnimation: IntroAnimation = IntroAnimation.None

  /**   Enter AnimationOverlap of  fragment. */
  public var enterTransitionOverlap: Boolean = false

  /**   Return AnimationOverlap of  fragment. */
  public var returnTransitionOverlap: Boolean = false

  /** interface for listening to the progress is changed. */
  private var onIndexChangeListener: OnIndexChangeListener? = null

  /** sets a progress change listener. */
  public fun setOnIndexChangeListener(onIndexChangeListener: OnIndexChangeListener) {
    this.onIndexChangeListener = onIndexChangeListener
  }

  /** sets a progress change listener. */
  @JvmSynthetic
  public fun setOnIndexChangeListener(block: (Int) -> Unit) {
    this.onIndexChangeListener = OnIndexChangeListener { index -> block(index) }
  }

  init {
    val v: View = inflate(context, R.layout.materialintro, this)

    post {
      if (fragmentList.isNotEmpty()) {
        if (fragmentList.isNotEmpty()) {
          showFragment(fragmentList[0])
        }
      }
    }
  }

  public constructor(context: Context) : super(context)

  public constructor(
    context: Context,
    attributeSet: AttributeSet
  ) : this(context, attributeSet, 0)

  public constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyle: Int
  ) : super(
    context,
    attributeSet,
    defStyle
  ) {
  }

  /** Builder class for [MaterialIntroView]. */
  @MaterialIntroViewInlineDsl
  public class Builder(context: Context) {
    private val materialIntroFragment = MaterialIntroFragment(context)

    /** sets the duration of the Animation. */
    public fun setEnterDuration(value: Long): Builder =
      apply { this.materialIntroFragment.enterDuration = value }

    /** sets the duration of the Animation. */
    public fun setExitDuration(value: Long): Builder =
      apply { this.materialIntroFragment.exitDuration = value }

    /** sets the duration of the Animation. */
    public fun setReenterDuration(value: Long): Builder =
      apply { this.materialIntroFragment.reenterDuration = value }

    /** sets the duration of the Animation. */
    public fun setReturnDuration(value: Long): Builder =
      apply { this.materialIntroFragment.returnDuration = value }

    /** sets enter the Animation of the Fragment. */
    public fun setEnterAnimation(value: IntroAnimation): Builder =
      apply { this.materialIntroFragment.enterAnimation = value }

    /** sets the enter Overlap of the Fragment. */
    public fun setEnterOverlap(value: Boolean): Builder =
      apply { this.materialIntroFragment.enterTransitionOverlap = value }

    /** sets the return Overlap of the Fragment. */
    public fun setReturnOverlap(value: Boolean): Builder =
      apply { this.materialIntroFragment.returnTransitionOverlap = value }

    /** sets the ScrimColor of the Fragment. */
    public fun setFragmentList(value: MutableList<Fragment>): Builder =
      apply { this.materialIntroFragment.fragmentList = value }

    public fun setOnIndexChangeListener(value: OnIndexChangeListener): Builder = apply {
      this.materialIntroFragment.onIndexChangeListener = value
    }

    @JvmSynthetic
    public fun setOnIndexChangeListener(block: (Int) -> Unit): Builder = apply {
      this.materialIntroFragment.onIndexChangeListener =
        OnIndexChangeListener { index -> block(index) }
    }

    public fun build(): MaterialIntroFragment = materialIntroFragment
  }

  /*go to previous fragment*/
  public fun previous() {
    if (indexExists(fragmentList, index - 1)) {
      index -= 1
      showFragment(fragmentList[index])
      onIndexChangeListener?.onChange(index)
    }
  }

  /*go to next fragment*/

  public fun next() {

    if (indexExists(fragmentList, index + 1)) {
      index += 1
      showFragment(fragmentList[index])
      onIndexChangeListener?.onChange(index)
    }
  }

  /*check index is valid or not*/
  private fun indexExists(list: List<*>, index: Int): Boolean {
    return index >= 0 && index < list.size
  }

  /** starts  animation. */
  private fun showFragment(fragment: Fragment) {
    fragment.applyAnimation(this)

    /*replace fragment with currant fragment*/
    context.activity()?.supportFragmentManager?.commit {
      replace(R.id.fragment_container, fragment)
    }
  }




  /*set views list*/
  public fun setFragmentsList(list: List<Fragment>) {
    fragmentList.clear()
    fragmentList.addAll(list)
  }
}
