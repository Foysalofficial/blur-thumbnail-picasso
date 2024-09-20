package com.blur.thumbnail.transformations.gpu;



import android.content.Context;

import jp.co.cyberagent.android.gpuimage.filter.GPUImageSketchFilter;

public class SketchFilterTransformation extends GPUFilterTransformation {

  public SketchFilterTransformation(Context context) {
    super(context, new GPUImageSketchFilter());
  }

  @Override
  public String key() {
    return "SketchFilterTransformation()";
  }
}
