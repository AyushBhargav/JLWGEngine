package com.ashb;

import com.ashb.model.Loader;
import com.ashb.model.RawModel;
import com.ashb.render.Renderer;
import com.ashb.shaders.StaticShader;
import com.ashb.window.WindowManager;

public class Launcher {
    public static void main(String[] args) {
        WindowManager.createWindow();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader staticShader = new StaticShader();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        RawModel rawModel = loader.loadToVAO(vertices, indices);

        while (!WindowManager.shouldWindowClose()) {
            renderer.prepare();
            staticShader.start();
            renderer.render(rawModel);
            staticShader.stop();
            WindowManager.updateWindow();
        }

        staticShader.cleanUp();
        loader.cleanUp();
        WindowManager.destroyWindow();
    }
}
