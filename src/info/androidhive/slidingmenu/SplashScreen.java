package info.androidhive.slidingmenu;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class SplashScreen extends Activity
{
	 private static int SPLASH_TIME_OUT = 4000;
	 SessionManager manager;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#275d7b"));
			getActionBar().setBackgroundDrawable(colorDrawable);
	        setContentView(R.layout.activity_splash);
	        StartAnimations();
	        manager=new SessionManager();
	        
	        new Handler().postDelayed(new Runnable() {
	        	
	            /*
	             * Showing splash screen with a timer. This will be useful when you
	             * want to show case your app logo / company
	             */
	 
	            @Override
	            public void run() 
	            {
	            	
	                // This method will be executed once the timer is over
	                // Start your app main activity
	            	String status=manager.getPreferences(SplashScreen.this,"status");
                    Log.d("status",status);
                    if (status.equals("0")||status.equals(""))
                    {
                        Intent i=new Intent(SplashScreen.this,Login1.class);
                        startActivity(i);
                    }
                    else
                    {
                        Intent i=new Intent(SplashScreen.this,MainActivity.class);
                        startActivity(i);
                    }
	                
	 
	                // close this activity
	                finish();
	            }
	        }, SPLASH_TIME_OUT);
	    }
	    
	    
	    private void StartAnimations() 
	    {
	    	Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
	        anim.reset();
	        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
	        l.clearAnimation();
	        l.startAnimation(anim);
	 
	        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
	        anim.reset();
	        /*ImageView iv = (ImageView) findViewById(R.id.imgLogo);
	        iv.clearAnimation();
	        iv.startAnimation(anim);*/
	      }
	    

}
