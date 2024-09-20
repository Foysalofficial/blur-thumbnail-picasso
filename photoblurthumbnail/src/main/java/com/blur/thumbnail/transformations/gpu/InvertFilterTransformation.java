package com.blur.thumbnail.transformations.gpu;



import android.content.Context;

import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorInvertFilter;


public class InvertFilterTransformation extends GPUFilterTransformation {

  public InvertFilterTransformation(Context context) {
    super(context, new GPUImageColorInvertFilter());
  }

  @Override
  public String key() {
    return "InvertFilterTransformation()";
  }
}
