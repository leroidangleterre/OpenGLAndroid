package com.example.arthurmanoha.openglandroid;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.arthurmanoha.openglandroid.Empty;

public class HelloOpenGLES10 extends AppCompatActivity {

    private GLSurfaceView mGLView;
    private Empty userEmpty;

    public static String TAG = "Arthur App";

    private boolean systemUiVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userEmpty = new Empty();
        userEmpty.setPos(-10, 0, 0);
        userEmpty.setTarget(10, 0, 0);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new HelloOpenGLES10SurfaceView(this, userEmpty);

        setContentView(mGLView);
        systemUiVisible = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        mGLView.onResume();
        showSystemUi(false);
    }

    public void toggleSystemUi(){
        showSystemUi(!systemUiVisible);
    }

    public void showSystemUi(boolean show) {
        // Hide the toolbars
        View decorView = getWindow().getDecorView();
        if(show){
            // Show the UI
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE|
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        else {
            // Hide the UI
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_IMMERSIVE |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
        systemUiVisible=show;
    }
}
