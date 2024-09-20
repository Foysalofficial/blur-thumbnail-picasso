package com.blur.thumbnail.picasso;

import android.content.Context;

public class Utils {

  public static int toDp(Context context, float dp) {
    float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dp * scale + 0.5f);
  }
}
