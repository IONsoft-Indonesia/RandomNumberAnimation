# RandomNumberAnimation
Easily create random number change animation to a TextView

# How to use?
## Step 1. Add the JitPack repository to your build file
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
## Step 2. Add the dependency
```gradle
dependencies {
  compile 'com.github.IONsoft-Indonesia:RandomNumberAnimation:1.0'
}
```
## Step 3. Initialize
```kotlin
val randomNumberAnimation = RandomNumberAnimation(yourTextView)
```
## Step 4. Start
```kotlin
randomNumberAnimation.start()
```
## Step 5. Stop
```kotlin
randomNumberAnimation.stop()
// or
randomNumberAnimation.stop(true)// to keep the random number change inside the text
```
