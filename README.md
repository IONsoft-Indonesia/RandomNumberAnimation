# RandomNumberAnimation
Easily create random number change animation to a TextView and other subclasses of TextView

Direct subclasses:
Button, CheckedTextView, Chronometer, DigitalClock, EditText, TextClock

Indirect subclasses:
AutoCompleteTextView, CheckBox, CompoundButton, ExtractEditText, MultiAutoCompleteTextView, RadioButton, Switch, ToggleButton

<a href="https://jitpack.io/#IONsoft-Indonesia/RandomNumberAnimation"><img alt="Release" src="https://jitpack.io/v/IONsoft-Indonesia/RandomNumberAnimation.svg"></a>
<a href="http://www.methodscount.com/?lib=com.github.IONsoft-Indonesia%3ARandomNumberAnimation%3A1.3.1"><img src="https://img.shields.io/badge/Methods and size-core: 37 | deps: 29878 | 21 KB-e91e63.svg"/></a>
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RandomNumberAnimation-blue.svg?style=flat)](https://android-arsenal.com/details/1/6640)
[![Build Status](https://travis-ci.org/IONsoft-Indonesia/RandomNumberAnimation.svg?branch=master)](https://travis-ci.org/IONsoft-Indonesia/RandomNumberAnimation)

## Preview
<a href="https://github.com/IONsoft-Indonesia/RandomNumberAnimation/blob/master/art/RandomNumberGenerator.gif"><img src="https://github.com/IONsoft-Indonesia/RandomNumberAnimation/blob/master/art/RandomNumberGenerator.gif" width="250px"/></a>
<a href="https://github.com/IONsoft-Indonesia/RandomNumberAnimation/blob/master/art/RandomNumberAnimation.gif"><img src="https://github.com/IONsoft-Indonesia/RandomNumberAnimation/blob/master/art/RandomNumberAnimation.gif" width="250px"/></a>

## How to use?
### Step 1. Add the JitPack repository to your build file
```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```
### Step 2. Add the dependency
```gradle
dependencies {
  implementation 'com.github.IONsoft-Indonesia:RandomNumberAnimation:1.3.3'
}
```
### Step 3. Initialize
#### Java
```java
RandomNumberAnimation randomNumberAnimation = new RandomNumberAnimation(yourTextView);
```
#### Kotlin
```kotlin
val randomNumberAnimation = RandomNumberAnimation(yourTextView)
```
### Step 4. Start
#### Java
```java
randomNumberAnimation.start();
```
#### Kotlin
```kotlin
randomNumberAnimation.start()
```
### Step 5. Stop
#### Java
```java
randomNumberAnimation.stop();
// or
randomNumberAnimation.stop(true); // to keep the random number change inside the text
```
#### Kotlin
```kotlin
randomNumberAnimation.stop()
// or
randomNumberAnimation.stop(true) // to keep the random number change inside the text
```

### Optional
You can also specify the delay between frames or frame per second
#### Java
```java
randomNumberAnimation.setDelay(16);
// or
randomNumberAnimation.setFPS(60);
```
#### Kotlin
```kotlin
randomNumberAnimation.delay = 16
// or
randomNumberAnimation.setFPS(60)
```
#### Note
This library will stop the animation directly if your Activity being destroyed using LifecycleObserver, so it will avoid Activity leak. However, if your Activity is not implementing LifecycleOwner, you should stop the animation manually when your Activity is finishing. LifecycleOwner is implemented by default by AppCompatActivity. It is also best practice if you stop the animation if it not visible to user and re-start it if it visible to user to gain the best performance**

## Proudly Used By
- [**Pulsaku**](https://play.google.com/store/apps/details?id=ion.rnd.android.indonesia&referrer=utm_source%3Dgithub.com%26utm_medium%3Dlibrary%26utm_term%3DRandomNumberAnimation)

## Contributing
Contributions are very welcome. If you find a bug in the library or want a feature and think you can fix it yourself, fork + pull request and we will greatly appreciate it!

We love getting pull requests for new features as well as bugs. However, when it comes to new features please also explain the use case and way you think the library should include it. If you don't want to start coding a feature without knowing if the feature will have chance of being included, open an issue and we can discuss the feature!

## License
```
MIT License

Copyright (c) 2018 PT. IONsoft

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
