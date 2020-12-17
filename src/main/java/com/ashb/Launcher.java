package com.ashb;

import com.ashb.models.TexturedModel;
import com.ashb.render.Loader;
import com.ashb.models.RawModel;
import com.ashb.render.Renderer;
import com.ashb.shaders.StaticShader;
import com.ashb.textures.ModelTexture;
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

        float[] textureCoords = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };

        RawModel rawModel = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("/textures/lattice.jpg"));
        TexturedModel texturedModel = new TexturedModel(rawModel, texture);

        while (!WindowManager.shouldWindowClose()) {
            renderer.prepare();
            staticShader.start();
            renderer.render(texturedModel);
            staticShader.stop();
            WindowManager.updateWindow();
        }

        staticShader.cleanUp();
        loader.cleanUp();
        WindowManager.destroyWindow();
    }
}
