Picasso Transformations Update
======================
 
# Demo

### Original Image
<img src="art/demo-org.jpg" width="30%">

### Transformations
<img src="art/demo.gif" width="50%">

# How do I use it?

## Step 1

#### Gradle
```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.github.Foysalofficial:blur-thumbnail-picasso:17.0'
}

## For Updated Android Studio

dependencies {
    implementation ("com.github.Foysalofficial:blur-thumbnail-picasso:17.0")
}

```

## Step 2

Set Picasso Transform.

```java
Picasso.with(mContext).load(R.drawable.demo)
    .transform(transformation).into((ImageView) findViewById(R.id.image));
```

## Advanced Step 3

You can set a multiple transformations.

```java
Picasso.with(mContext).load(R.drawable.demo)
    .transform(transformation)
    .transform(new CropCircleTransformation())
    .into(holder.image);
```

## Transformations

### Crop
`CropTransformation`, `CropCircleTransformation`, `CropSquareTransformation`,
`RoundedCornersTransformation`

### Color
`ColorFilterTransformation`, `GrayscaleTransformation`

### Blur
`BlurTransformation`

### Mask
`MaskTransformation`

### GPU Filter (use [GPUImage](https://github.com/CyberAgent/android-gpuimage))
**Will require add dependencies for GPUImage.**

`ToonFilterTransformation`, `SepiaFilterTransformation`, `ContrastFilterTransformation`
`InvertFilterTransformation`, `PixelationFilterTransformation`, `SketchFilterTransformation`
`SwirlFilterTransformation`, `BrightnessFilterTransformation`, `KuwaharaFilterTransformation`
`VignetteFilterTransformation`


Applications using Picasso Transformations
---

Please [ping](mailto:smictfoysal@gmail.com) me or send a pull request if you would like to be added here.

Icon | Application
------------ | -------------
<img src="https://lh6.ggpht.com/6zKH_uQY1bxCwXL4DLo_uoFEOXdShi3BgmN6XRHlaJ-oA1svmq6y1PZkmO50nWQn2Lg=w300-rw" width="48" height="48" /> | [Ameba Ownd](https://play.google.com/store/apps/details?id=com.foysaltech.alquran)
<img src="http://quitnowapp.com/xtra/QuitNow!-114.png" width="48" height="48" /> | [QuitNow!](https://play.google.com/store/apps/details?id=com.foysaltech.deeproot)

Developed By
-------
Foysal Tech (Foysal Official) - <smictfoysal@gmail.com>

<a href="=https://twitter.com/foysal_official">
<img alt="Follow me on Twitter"
src="https://raw.githubusercontent.com/wasabeef/art/master/twitter.png" width="75"/>
</a>

Thanks
-------

* Inspired by `Picasso Transformations` in [TannerPerrien](https://github.com/TannerPerrien).

License
-------

    Copyright (C) 2024 Foysal Tech

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
