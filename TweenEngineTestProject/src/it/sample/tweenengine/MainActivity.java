package it.sample.tweenengine;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Bounce;
import it.sample.tweenengine.R;

public class MainActivity extends Activity {

  private TweenManager tweenManager;
  private boolean isAnimationRunning = true;

  private LinearLayout ll;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ll = (LinearLayout) findViewById(R.id.main_cont);
    setTweenEngine();
  }

  private void setTweenEngine() {
    tweenManager = new TweenManager();
    
    Tween.registerAccessor(ViewContainer.class, new ViewContainerAccessor());

  }

  public void startAnimation(View v) {

	  
	startAnimationThread();
	  
    ViewContainer cont = new ViewContainer();
    
    cont.view = ll;
    
    Timeline.createSequence()
    	.push(Tween.to(cont, ViewContainerAccessor.POSITION_XY, 0.5f).target(200, 0).ease(Bounce.OUT).delay(0.0f) )
    	.push(Tween.to(cont, ViewContainerAccessor.POSITION_XY, 0.5f).target(450, 200).ease(aurelienribon.tweenengine.equations.Linear.INOUT).delay(0.0f) )
    	.start(tweenManager).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				
				stopAnimationThread();
			}
		});
  }

  private Thread _thread = null;
  
  private void stopAnimationThread()
  {
	  setAnimationFalse();
		
	  _thread = null;

	  
  }
  
  private void startAnimationThread() {

	  setAnimationTrue();
	  
	  if(_thread == null)
	  {
		  Runnable r = new Runnable() {
			  private long lastMillis = -1;
	
		      @Override public void run() {
		    	  
		        while (isAnimationRunning) {
		        	
		          if (lastMillis > 0) {
		            long currentMillis = System.currentTimeMillis();
		            final float delta = (currentMillis - lastMillis) / 1000f;
		
		            runOnUiThread(new Runnable() {
		
		              @Override public void run() {
		                tweenManager.update(delta);
		                
		                Log.v("TIME",String.valueOf(delta));
		
		              }
		            });
		
		            lastMillis = currentMillis;
		          } else {
		            lastMillis = System.currentTimeMillis();
		          }
		
		          try {
		            Thread.sleep(1000 / 60);
		          } catch (InterruptedException ex) {
		          }
		        }
		      }
		  
		  };
		  
		  _thread = new Thread(r);
		 
	  }
	  
	  if(!_thread.isAlive())
		  _thread.start();

  }

  private void setAnimationFalse() {
    isAnimationRunning = false;
  }

  private void setAnimationTrue() {
    isAnimationRunning = true;
  }

}
