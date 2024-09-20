package com.blur.thumbnail.transformations.gpu;



import android.content.Context;

import jp.co.cyberagent.android.gpuimage.filter.GPUImageToonFilter;


public class ToonFilterTransformation extends GPUFilterTransformation {

  private final float mThreshold;
  private final float mQuantizationLevels;

  public ToonFilterTransformation(Context context) {
    this(context, .2f, 10.0f);
  }

  public ToonFilterTransformation(Context context, float threshold, float quantizationLevels) {
    super(context, new GPUImageToonFilter());
    mThreshold = threshold;
    mQuantizationLevels = quantizationLevels;
    GPUImageToonFilter filter = getFilter();
    filter.setThreshold(mThreshold);
    filter.setQuantizationLevels(mQuantizationLevels);
  }

  @Override
  public String key() {
    return "ToonFilterTransformation(threshold=" + mThreshold +
      ",quantizationLevels=" + mQuantizationLevels + ")";
  }
}
