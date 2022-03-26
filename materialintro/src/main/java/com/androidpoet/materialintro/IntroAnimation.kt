
package com.androidpoet.materialintro

/**
 *Animation types which can be used in in views and fragments
 */
public sealed class IntroAnimation {
  public object MaterialSharedXAxisForeword : IntroAnimation()
  public object MaterialSharedYAxisForeword : IntroAnimation()
  public object MaterialSharedZAxisForeword : IntroAnimation()
  public object MaterialSharedXAxisBackward : IntroAnimation()
  public object MaterialSharedYAxisBackward : IntroAnimation()
  public object MaterialSharedZAxisBackward : IntroAnimation()
  public object MaterialFade : IntroAnimation()
  public object MaterialFadeThrough : IntroAnimation()
}
