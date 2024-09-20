package com.blur.thumbnail.transformations.gpu;



import android.content.Context;
import android.graphics.PointF;

import jp.co.cyberagent.android.gpuimage.filter.GPUImageSwirlFilter;


public class SwirlFilterTransformation extends GPUFilterTransformation {

  private final float mRadius;
  private final float mAngle;
  private final PointF mCenter;

  public SwirlFilterTransformation(Context context) {
    this(context, .5f, 1.0f, new PointF(.5f, .5f));
  }

  
  public SwirlFilterTransformation(Context context, float radius, float angle, PointF center) {
    super(context, new GPUImageSwirlFilter());
    mRadius = radius;
    mAngle = angle;
    mCenter = center;
    GPUImageSwirlFilter filter = getFilter();
    filter.setRadius(mRadius);
    filter.setAngle(mAngle);
    filter.setCenter(mCenter);
  }

  @Override
  public String key() {
    return "SwirlFilterTransformation(radius=" + mRadius +
      ",angle=" + mAngle + ",center=" + mCenter.toString() + ")";
  }
}
