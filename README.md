

<h1 align="center">Material Intro </h1>

<p align="center">
Sophisticated and cool intro with Material Motion Animations.
</p>






<p align="center">
  <a href="https://devlibrary.withgoogle.com/authors/androidpoet"><img alt="Google" src="https://user-images.githubusercontent.com/13647384/162663007-d911f6ce-ac1b-4754-a63b-eadbef38087f.svg"/></a>
<br>
	<br>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://github.com/AndroidPoet"><img alt="Profile" src="https://user-images.githubusercontent.com/13647384/162662962-82e3c1eb-baf8-4e21-ad26-d4c4e3c31e44.svg"/></a>

</p> <br>


<p align="center">
	<img src="https://user-images.githubusercontent.com/13647384/159128385-773dd6f0-499f-4885-a84b-5d22b7a9f7e5.svg" width="80%" />

</p> <br>

<p align="center">



## Who's using Material Intro?
**👉 [Check out who's using Material Intro](/usecases.md)**

## Include in your project
[![Maven Central](https://img.shields.io/maven-central/v/io.github.androidpoet/materialintro.svg?label=Maven%20Central)](https://search.maven.org/artifact/io.github.androidpoet/materialintro)

### Gradle
Add the dependency below to your **module**'s `build.gradle` file:

```gradle
dependencies {
    implementation("io.github.androidpoet:materialintro:1.0.6")
}
```


## SetUp for Views


```xml

<com.androidpoet.materialintro.MaterialIntroView
android:id="@+id/materialintroView"
android:layout_width="match_parent"
android:layout_height="match_parent">
</com.androidpoet.materialintro.MaterialIntroView>
```

```kotlin
//add views into list
  list.add(R.layout.layout_one)
  list.add(R.layout.layout_two)
  list.add(R.layout.layout_three)


binding.materialIntroFragment.apply {
    setFragmentsList(list)
    enterAnimation = IntroAnimation.SharedAxisXForward
    reenterAnimation = IntroAnimation.SharedAxisXBackward
    enterDuration = 500
    exitDuration = 500
}


//  go next view with animation
binding.nextButton.setOnClickListener {
    binding.materialIntroFragment.next()
}

//  go previous view with animation
binding.backButton.setOnClickListener {
    binding.materialIntroFragment.previous()
}
```



## SetUp for Fragments



```xml
<com.androidpoet.materialintro.MaterialIntroFragment
android:id="@+id/materialintroView"
android:layout_width="match_parent"
android:layout_height="match_parent">
</com.androidpoet.materialintro.MaterialIntroFragment>
```


```kotlin
//add fragments into list
list.add(FragmentOne())
list.add(FragmentTwo())
list.add(FragmentThree())
with(binding.materialintroView) {
    setViewsList(list)
    nextAnimation = IntroAnimation.Fade
    previousAnimation = IntroAnimation.Fade
    nextDuration = 500
    previousDuration = 500
}


//  go next view with animation
binding.nextButton.setOnClickListener {
    binding.materialintroView.next()
}

//  go previous view with animation
binding.backButton.setOnClickListener {
    binding.materialintroView.previous()
}
```



## Supported Animations

```kotlin

IntroAnimation.None
IntroAnimation.FadeThrough
IntroAnimation.Fade
IntroAnimation.SharedAxisXForward
IntroAnimation.SharedAxisYForward
IntroAnimation.SharedAxisZForward
IntroAnimation.SharedAxisXBackward
IntroAnimation.SharedAxisYBackward
IntroAnimation.SharedAxisZBackward
IntroAnimation.ElevationScaleGrow
IntroAnimation.ElevationScale
```

## Create using Builder

This is how to create an instance of the MaterialIntro using kotlin dsl.

```kotlin
val meta = materialIntroFragment(this) {
setEnterAnimation(IntroAnimation.Fade)
setExitAnimation(IntroAnimation.Fade)
setReenterAnimation(IntroAnimation.SharedAxisXBackward)
setReturnAnimation(IntroAnimation.SharedAxisXForward)
setEnterDuration(300)
setExitDuration(300)
setReturnDuration(300)
setReenterDuration(300)
setEnterOverlap(true)
setEnterOverlap(true)
build()
}
meta.next()
meta.previous()
meta.setFragmentsList(list)
```
We can create the MaterialIntro using MaterialIntro.Builder.
```kotlin
val meta = MaterialIntroFragment.Builder(this)
.setEnterAnimation(IntroAnimation.Fade)
.setExitAnimation(IntroAnimation.Fade)
.setReenterAnimation(IntroAnimation.SharedAxisXBackward)
.setReturnAnimation(IntroAnimation.SharedAxisXForward)
.setEnterDuration(300)
.setExitDuration(300)
.setReturnDuration(300)
.setReenterDuration(300)
.setEnterOverlap(true)
.setEnterOverlap(true)
.build()



    meta.next()
    meta.previous()
    meta.setFragmentsList(list)

```








## MaterialFade

<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/159129150-2b75f746-3982-4ed9-8b4a-f7936dc6a9fb.gif" width="30%"  />

</p> <br>


## Fade

<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/159129501-e7af0b29-ff54-4853-8f2f-566c8132af3b.gif" width="30%"  />

</p> <br>

## SharedAxis

<p align="center">
<img src="https://user-images.githubusercontent.com/13647384/159129757-4706821f-a86d-443b-8db3-5d88f6aee841.gif" width="30%"  />

</p> <br>


<a href="https://www.flaticon.com/free-icons/card" title="card icons">Card icons created by Freepik - Flaticon</a>



## Find this library useful? :heart:
Support it by joining __[stargazers](https://github.com/androidpoet/MaterialIntro/stargazers)__ for this repository. :star:

# License
```xml
Copyright 2022 AndroidPoet (Ranbir Singh)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
        limitations under the License.
```





