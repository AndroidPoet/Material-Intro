
package com.androidpoet.materialintro

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.MainThread
import com.androidpoet.metaphor.IntroAnimation

@DslMarker
internal annotation class MaterialIntroViewInlineDsl

/**
 * Creates an instance of the [MaterialIntroView] by scope of the [MaterialIntroView.Builder] using kotlin dsl.
 *
 * @param Context A context for creating resources of the [MaterialIntroView].
 * @param block A dsl scope lambda from the [MaterialIntroView.Builder].
 * */
@MainThread
@JvmSynthetic
@MaterialIntroViewInlineDsl
public inline fun materialIntroView(
  context: Context,
  crossinline block: MaterialIntroView.Builder.() -> Unit
): MaterialIntroView =
  MaterialIntroView.Builder(context).apply(block).build()

/**
 * MetaphorFragment implements material motion animations.
 *
 * @see [MaterialIntroView](https://github.com/AndroidPoet/Metaphor)
 *
 * @param builder A [MaterialIntroView.Builder] for creating an instance of the [MaterialIntroView].
 */
public class MaterialIntroView : FrameLayout {

  /** views list. */
  public var layoutList: MutableList<Int> = mutableListOf()

  /**current fragment index */
  public var index: Int = 0

  /** duration of enter the animations. */
  public var nextDuration: Long = 300

  /** duration of exit the animations. */
  public var previousDuration: Long = 300

  /** Next Animation of  View. */
  public var nextAnimation: IntroAnimation = IntroAnimation.None

  /** Previous Animation of  View. */
  public var previousAnimation: IntroAnimation = IntroAnimation.None

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

  init {
    val v: View = inflate(context, R.layout.materialintro, this)

    post {
      if (layoutList.isNotEmpty()) {
        showScene(this.layoutList[0], this.nextAnimation, this.nextDuration, this)
      }
    }
  }

  /** Builder class for [MaterialIntroView]. */
  @MaterialIntroViewInlineDsl
  public class Builder(context: Context) {
    private val materialIntroView = MaterialIntroView(context)

    /** sets the duration of the Animation. */
    public fun setEnterDuration(value: Long): Builder =
      apply { this.materialIntroView.nextDuration = value }

    /** sets the duration of the Animation. */
    public fun setExitDuration(value: Long): Builder =
      apply { this.materialIntroView.previousDuration = value }

    /** sets enter the Animation of the Fragment. */
    public fun setNextAnimation(value: IntroAnimation): Builder =
      apply { this.materialIntroView.nextAnimation = value }

    /** sets the enter Overlap of the Fragment. */
    public fun setPreviousAnimation(value: IntroAnimation): Builder =
      apply { this.materialIntroView.previousAnimation = value }

    /** set ViewsList . */
    public fun setViewsList(value: MutableList<Int>): Builder =
      apply {
        this.materialIntroView.layoutList = value
      }

    public fun setOnIndexChangeListener(value: OnIndexChangeListener): Builder = apply {
      this.materialIntroView.onIndexChangeListener = value
    }

    @JvmSynthetic
    public fun setOnIndexChangeListener(block: (Int) -> Unit): Builder = apply {
      this.materialIntroView.onIndexChangeListener =
        OnIndexChangeListener { index -> block(index) }
    }

    public fun build(): MaterialIntroView = materialIntroView
  }

  /*go to previous fragment*/
  public fun previous() {
    if (indexExists(layoutList, index - 1)) {
      index -= 1
      showScene(this.layoutList[index], this.previousAnimation, this.previousDuration, this)
      onIndexChangeListener?.onChange(index)
    }
  }

  /*go to next fragment*/

  public fun next() {
    if (indexExists(layoutList, index + 1)) {
      index += 1
      showScene(this.layoutList[index], this.nextAnimation, this.nextDuration, this)
      onIndexChangeListener?.onChange(index)
    }
  }

  /*check index is valid or not*/
  private fun indexExists(list: List<*>, index: Int): Boolean {
    return index >= 0 && index < list.size
  }

  public fun setViewsList(list: List<Int>) {
    layoutList.clear()
    layoutList.addAll(list)
  }
}
