# MathRenderer
Simple and easy library written in kotlin language for displaying mathematical equations or formulas using [MathJax](https://www.mathjax.org/). `MathRenderView` is bacically based on `WebView` and render mathematical equations using javascript. It is also compatable with `DataBinding` and allows users to customize the `MathRendererView`.

# MathJax
MathJax is an open-source JavaScript display engine for LaTeX, MathML, and AsciiMath notation that works in all modern browsers. It was designed with the goal of consolidating the recent advances in web technologies into a single, definitive, math-on-the-web platform supporting the major browsers and operating systems. It requires no setup on the part of the user (no plugins to download or software to install), so the page author can write web documents that include mathematics and be confident that users will be able to view it naturally and easily. Simply include MathJax and some mathematics in a web page, and MathJax does the rest.

# How to build equations
If you use basic text string then this `MathRendererView` will work as a `TextView` but to write inline equation you need to use parentheses and backslash to escape Kotlin String Interpolation.
```kotlin
	var eqn = "\\(a^2+2x+6\\)"
```
If you need a new line and display your equation then do the same given below-
```kotlin
	var eqn = "\\[a^4+8x+9 \\over 3x\\]"
```
You can use LaTeX for writing the equations or go to [here](http://docs.mathjax.org/en/latest/basic/mathematics.html#putting-math-in-javascript-strings)

# Installation
[![](https://jitpack.io/v/quantaDot/MathRenderer.svg)](https://jitpack.io/#quantaDot/MathRenderer)

Add below lines to app's build.gradle

```groovy
repositories {
	maven { url 'https://jitpack.io' }
}
```
```groovy
dependencies {
	implementation 'com.github.quantaDot:MathRenderer:1.0.2'
}
```
