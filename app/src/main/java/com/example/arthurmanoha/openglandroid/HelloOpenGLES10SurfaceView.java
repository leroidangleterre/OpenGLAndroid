package com.example.arthurmanoha.openglandroid;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

import static com.example.arthurmanoha.openglandroid.HelloOpenGLES10.TAG;

class HelloOpenGLES10SurfaceView extends GLSurfaceView {

    public HelloOpenGLES10SurfaceView(Context context, Empty e) {
        super(context);

        // Set the HelloOpenGLES10Renderer for drawing on the GLSurfaceView

        HelloOpenGLES10Renderer myRenderer = new HelloOpenGLES10Renderer(getWidth(), getHeight());
        myRenderer.setEmpty(e);
        setRenderer(myRenderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Perform an action that depends on the coordinates of the event
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (x >= 900 && x <= 1300 && y >= 900) {
            // Bottom center
            // Hide the controls.
            ((HelloOpenGLES10) getContext()).toggleSystemUi();
        }

        return false;
    }


}