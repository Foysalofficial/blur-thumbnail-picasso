package com.blur.thumbnail.transformations.gpu;


import android.content.Context;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBrightnessFilter;

public class BrightnessFilterTransformation extends GPUFilterTransformation {

  private final float mBrightness;

  public BrightnessFilterTransformation(Context context) {
    this(context, 0.0f);
  }

  public BrightnessFilterTransformation(Context context, float brightness) {
    super(context, new GPUImageBrightnessFilter());
    mBrightness = brightness;
    GPUImageBrightnessFilter filter = getFilter();
    filter.setBrightness(mBrightness);
  }

  @Override
  public String key() {
    return "BrightnessFilterTransformation(brightness=" + mBrightness + ")";
  }
}
