# SimpleImageView

## Installation
Add it in your root build.gradle at the end of repositories:

```
allprojects {
   repositories {
      ...
      maven { url 'https://jitpack.io' }
   }
}
```

Add the dependency

```
compile 'com.github.renaldi2108:SimpleImageView:1.0'
```

## Usage

use xml:
```xml
<id.renaldi.simpleimageview.SimpleImageView
        android:layout_width="100dp"
        android:layout_height="wrap_content"
        app:isSquare="true"
        android:layout_centerInParent="true"
        app:siv_top_left_radius="30dp"
        app:siv_bottom_right_radius="30dp"
        android:src="@color/colorPrimary"/>
```
