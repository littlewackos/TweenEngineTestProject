package it.sample.tweenengine;

import aurelienribon.tweenengine.TweenAccessor;

public class ViewContainerAccessor implements TweenAccessor<ViewContainer> {

  public static final int POSITION_X = 1;
  public static final int POSITION_Y = 2;
  public static final int POSITION_XY = 3;

  @Override
  public int getValues(ViewContainer target, int tweenType, float[] returnValues) {
      switch (tweenType) {
          case POSITION_X: returnValues[0] = target.getX(); return 1;
          case POSITION_Y: returnValues[0] = target.getY(); return 1;
          case POSITION_XY:
              returnValues[0] = target.getX();
              returnValues[1] = target.getY();
              return 2;
          default: assert false; return -1;
      }
  }
  
  @Override
  public void setValues(ViewContainer target, int tweenType, float[] newValues) {
      switch (tweenType) {
      case POSITION_X: target.setX(newValues[0]); break;
      case POSITION_Y: target.setY(newValues[0]); break;
      case POSITION_XY:
          target.setX(newValues[0]);
          target.setY(newValues[1]);
          break;
          default: assert false; break;
      }
  }
}