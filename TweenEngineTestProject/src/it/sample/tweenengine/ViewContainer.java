package it.sample.tweenengine;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ViewContainer {
	
  private float x, y;
  public View view;
  
  public float getX() {
      return x;
  }
  
  public float getY() {
      return y;
  }

  public void setX(float x) {
      this.x = x;
      
      RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)view.getLayoutParams();
      params.leftMargin= (int)x;
      view.setLayoutParams(params);
     
  }

  public void setY(float y) {
      this.y = y;

      RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)view.getLayoutParams();
      params.topMargin = (int)y;
      view.setLayoutParams(params);

  }
}