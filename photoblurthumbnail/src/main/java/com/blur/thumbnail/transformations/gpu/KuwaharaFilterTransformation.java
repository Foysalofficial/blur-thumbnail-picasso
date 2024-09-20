package com.blur.thumbnail.transformations.gpu;



import android.content.Context;

import jp.co.cyberagent.android.gpuimage.filter.GPUImageKuwaharaFilter;


public class KuwaharaFilterTransformation extends GPUFilterTransformation {

  private final int mRadius;

  public KuwaharaFilterTransformation(Context context) {
    this(context, 25);
  }

  public KuwaharaFilterTransformation(Context context, int radius) {
    super(context, new GPUImageKuwaharaFilter());
    mRadius = radius;
    GPUImageKuwaharaFilter filter = getFilter();
    filter.setRadius(mRadius);
  }

  @Override
  public String key() {
    return "KuwaharaFilterTransformation(radius=" + mRadius + ")";
  }
}
