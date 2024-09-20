package com.blur.thumbnail.transformations.gpu;



import android.content.Context;

import jp.co.cyberagent.android.gpuimage.filter.GPUImagePixelationFilter;


public class PixelationFilterTransformation extends GPUFilterTransformation {

  private final float mPixel;

  public PixelationFilterTransformation(Context context) {
    this(context, 10f);
  }

  public PixelationFilterTransformation(Context context, float pixel) {
    super(context, new GPUImagePixelationFilter());
    mPixel = pixel;
    GPUImagePixelationFilter filter = getFilter();
    filter.setPixel(mPixel);
  }

  @Override
  public String key() {
    return "PixelationFilterTransformation(pixel=" + mPixel + ")";
  }
}
