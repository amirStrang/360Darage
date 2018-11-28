package com.a360degree.a360darage.Utils;

import android.content.Context;

import com.asha.vrlib.MDVRLibrary;
import com.asha.vrlib.model.MDPinchConfig;
import com.google.android.apps.muzei.render.GLTextureView;

import java.util.LinkedList;
import java.util.List;

import static com.asha.vrlib.MDVRLibrary.INTERACTIVE_MODE_MOTION;


public class VRLibManager {
    private Context context;

    private boolean isResumed;

    private List<MDVRLibrary> libs = new LinkedList<>();

    public VRLibManager(Context context) {
        this.context = context;
    }

    public MDVRLibrary create(MDVRLibrary.IBitmapProvider provider, GLTextureView textureView) {
        MDVRLibrary lib = MDVRLibrary.with(context)
                .asBitmap(provider)
                .pinchConfig(new MDPinchConfig().setMin(0.8f).setSensitivity(1).setDefaultValue(0.8f))
                .interactiveMode(INTERACTIVE_MODE_MOTION)
                .build(textureView);
        add(lib);
        return lib;
    }

    private void add(MDVRLibrary lib) {
        if (isResumed) {
            lib.onResume(context);
        }

        libs.add(lib);
    }

    public void fireResumed() {
        isResumed = true;
        for (MDVRLibrary library : libs) {
            library.onResume(context);
        }
    }

    public void firePaused() {
        isResumed = false;
        for (MDVRLibrary library : libs) {
            library.onPause(context);
        }
    }

    public void fireDestroy() {
        for (MDVRLibrary library : libs) {
            library.onDestroy();
        }
    }
}