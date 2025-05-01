package com.example.kamen_app_01;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.HashMap;

public class AnimationManager {
    private static AnimationManager instance;
    private final HashMap<String, Animation> xmlAnimations;

    // Private constructor to prevent direct instantiation
    private AnimationManager() {
        xmlAnimations = new HashMap<>();
    }

    // Get the singleton instance
    public static synchronized AnimationManager getInstance() {
        if (instance == null) {
            instance = new AnimationManager();
        }
        return instance;
    }

    // Preload XML animations
    public void preloadXmlAnimations(Context context) {
        xmlAnimations.put("customfade", AnimationUtils.loadAnimation(context, R.anim.customfade));
        xmlAnimations.put("rotate", AnimationUtils.loadAnimation(context, R.anim.rotate));
        // Add more XML animations as needed
    }


    // Get an XML animation by name
    public Animation getXmlAnimation(String name) {
        return xmlAnimations.get(name);
    }


    // Clear resources (optional, call when app is destroyed)
    public void clear() {
        xmlAnimations.clear();
    }
}