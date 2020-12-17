package com.ashb.window;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class WindowManager {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final String TITLE = "Game engine";
    private static final int FPS_CAPS = 120;

    private static long window;
    private static Sync sync;

    public static void createWindow() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        window = glfwCreateWindow(WIDTH, HEIGHT, TITLE, NULL, NULL);
        if (window == NULL) {
            throw new IllegalStateException("Failed to create window.");
        }
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        GL.createCapabilities();

        sync = new Sync();
    }

    public static void updateWindow() {
        sync.sync(FPS_CAPS);
        glfwPollEvents();
        glfwSwapBuffers(window);
    }

    public static boolean shouldWindowClose() {
        return glfwWindowShouldClose(window);
    }

    public static void destroyWindow() {
        glfwDestroyWindow(window);
        window = NULL;
    }
}
