package com.blur.thumbnail.transformations;



import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.squareup.picasso.Transformation;


public class CropTransformation implements Transformation {
  private static final String TAG = "PicassoTransformation";

  public enum GravityHorizontal {
    LEFT,
    CENTER,
    RIGHT
  }

  public enum GravityVertical {
    TOP,
    CENTER,
    BOTTOM
  }

  private float mAspectRatio;
  private int mLeft;
  private int mTop;
  private int mWidth;
  private int mHeight;
  private float mWidthRatio;
  private float mHeightRatio;
  private GravityHorizontal mGravityHorizontal = GravityHorizontal.CENTER;
  private GravityVertical mGravityVertical = GravityVertical.CENTER;

  
  public CropTransformation(int left, int top, int width, int height) {
    mLeft = left;
    mTop = top;
    mWidth = width;
    mHeight = height;
    mGravityHorizontal = null;
    mGravityVertical = null;
  }

  
  public CropTransformation(int width, int height, GravityHorizontal gravityHorizontal,
                            GravityVertical gravityVertical) {
    mWidth = width;
    mHeight = height;
    mGravityHorizontal = gravityHorizontal;
    mGravityVertical = gravityVertical;
  }

  
  public CropTransformation(int width, int height) {
    this(width, height, GravityHorizontal.CENTER, GravityVertical.CENTER);
  }

  
  public CropTransformation(float widthRatio, float heightRatio,
                            GravityHorizontal gravityHorizontal, GravityVertical gravityVertical) {
    mWidthRatio = widthRatio;
    mHeightRatio = heightRatio;
    mGravityHorizontal = gravityHorizontal;
    mGravityVertical = gravityVertical;
  }

  
  public CropTransformation(float widthRatio, float heightRatio) {
    this(widthRatio, heightRatio, GravityHorizontal.CENTER, GravityVertical.CENTER);
  }

  
  public CropTransformation(int width, int height, float aspectRatio,
                            GravityHorizontal gravityHorizontal, GravityVertical gravityVertical) {
    mWidth = width;
    mHeight = height;
    mAspectRatio = aspectRatio;
    mGravityHorizontal = gravityHorizontal;
    mGravityVertical = gravityVertical;
  }

  
  public CropTransformation(float widthRatio, float heightRatio, float aspectRatio,
                            GravityHorizontal gravityHorizontal, GravityVertical gravityVertical) {
    mWidthRatio = widthRatio;
    mHeightRatio = heightRatio;
    mAspectRatio = aspectRatio;
    mGravityHorizontal = gravityHorizontal;
    mGravityVertical = gravityVertical;
  }

  
  public CropTransformation(float aspectRatio, GravityHorizontal gravityHorizontal,
                            GravityVertical gravityVertical) {
    mAspectRatio = aspectRatio;
    mGravityHorizontal = gravityHorizontal;
    mGravityVertical = gravityVertical;
  }

  @Override
  public Bitmap transform(Bitmap source) {
    if (Log.isLoggable(TAG, Log.VERBOSE)) Log.v(TAG, "transform(): called, " + key());

    if (mWidth == 0 && mWidthRatio != 0) {
      mWidth = (int) ((float) source.getWidth() * mWidthRatio);
    }
    if (mHeight == 0 && mHeightRatio != 0) {
      mHeight = (int) ((float) source.getHeight() * mHeightRatio);
    }

    if (mAspectRatio != 0) {
      if (mWidth == 0 && mHeight == 0) {
        float sourceRatio = (float) source.getWidth() / (float) source.getHeight();

        if (Log.isLoggable(TAG, Log.VERBOSE)) {
          Log.v(TAG,
            "transform(): mAspectRatio: " + mAspectRatio + ", sourceRatio: " + sourceRatio);
        }

        if (sourceRatio > mAspectRatio) {
          // source is wider than we want, restrict by height
          mHeight = source.getHeight();
        } else {
          // source is taller than we want, restrict by width
          mWidth = source.getWidth();
        }
      }

      if (Log.isLoggable(TAG, Log.VERBOSE)) {
        Log.v(TAG, "transform(): before setting other of h/w: mAspectRatio: " + mAspectRatio
          + ", set one of width: " + mWidth + ", height: " + mHeight);
      }

      if (mWidth != 0) {
        mHeight = (int) ((float) mWidth / mAspectRatio);
      } else {
        if (mHeight != 0) {
          mWidth = (int) ((float) mHeight * mAspectRatio);
        }
      }

      if (Log.isLoggable(TAG, Log.VERBOSE)) {
        Log.v(TAG,
          "transform(): mAspectRatio: " + mAspectRatio + ", set width: " + mWidth + ", height: "
            + mHeight);
      }
    }

    if (mWidth == 0) {
      mWidth = source.getWidth();
    }

    if (mHeight == 0) {
      mHeight = source.getHeight();
    }

    if (mGravityHorizontal != null) {
      mLeft = getLeft(source);
    }
    if (mGravityVertical != null) {
      mTop = getTop(source);
    }

    Rect sourceRect = new Rect(mLeft, mTop, (mLeft + mWidth), (mTop + mHeight));
    Rect targetRect = new Rect(0, 0, mWidth, mHeight);

    if (Log.isLoggable(TAG, Log.VERBOSE)) {
      Log.v(TAG,
        "transform(): created sourceRect with mLeft: " + mLeft + ", mTop: " + mTop + ", right: "
          + (mLeft + mWidth) + ", bottom: " + (mTop + mHeight));
    }
    if (Log.isLoggable(TAG, Log.VERBOSE)) {
      Log.v(TAG, "transform(): created targetRect with width: " + mWidth + ", height: " + mHeight);
    }

    Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    if (Log.isLoggable(TAG, Log.VERBOSE)) {
      Log.v(TAG, "transform(): copying from source with width: " + source.getWidth() + ", height: "
        + source.getHeight());
    }
    canvas.drawBitmap(source, sourceRect, targetRect, null);

    source.recycle();

    if (Log.isLoggable(TAG, Log.VERBOSE)) {
      Log.v(TAG, "transform(): returning bitmap with width: " + bitmap.getWidth() + ", height: "
        + bitmap.getHeight());
    }

    return bitmap;
  }

  @Override
  public String key() {
    return "CropTransformation(width=" + mWidth + ", height=" + mHeight + ", mWidthRatio="
      + mWidthRatio + ", mHeightRatio=" + mHeightRatio + ", mAspectRatio=" + mAspectRatio
      + ", gravityHorizontal=" + mGravityHorizontal + ", mGravityVertical=" + mGravityVertical
      + ")";
  }

  private int getTop(Bitmap source) {
    switch (mGravityVertical) {
      case TOP:
        return 0;
      case CENTER:
        return (source.getHeight() - mHeight) / 2;
      case BOTTOM:
        return source.getHeight() - mHeight;
      default:
        return 0;
    }
  }

  private int getLeft(Bitmap source) {
    switch (mGravityHorizontal) {
      case LEFT:
        return 0;
      case CENTER:
        return (source.getWidth() - mWidth) / 2;
      case RIGHT:
        return source.getWidth() - mWidth;
      default:
        return 0;
    }
  }
}
