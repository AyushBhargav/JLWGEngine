package com.ashb.shaders;

import org.lwjgl.opengl.GL30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AbstractShader {

    private final int programId;
    private final int vertexShaderId;
    private final int fragmentShaderId;

    public AbstractShader(String vertexFile, String fragmentFile) {
        vertexShaderId = loadShader(vertexFile, GL30.GL_VERTEX_SHADER);
        fragmentShaderId = loadShader(fragmentFile, GL30.GL_FRAGMENT_SHADER);
        programId = GL30.glCreateProgram();
        GL30.glAttachShader(programId, vertexShaderId);
        GL30.glAttachShader(programId, fragmentShaderId);
        GL30.glLinkProgram(programId);
        GL30.glValidateProgram(programId);
    }

    public void start() {
        GL30.glUseProgram(programId);
    }

    public void stop() {
        GL30.glUseProgram(0);
    }

    public void cleanUp() {
        stop();
        GL30.glDetachShader(programId, vertexShaderId);
        GL30.glDetachShader(programId, fragmentShaderId);
        GL30.glDeleteShader(vertexShaderId);
        GL30.glDeleteShader(fragmentShaderId);
        GL30.glDeleteProgram(programId);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variableName) {
        GL30.glBindAttribLocation(programId, attribute, variableName);
    }

    private static int loadShader(String shaderFile, int type) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(AbstractShader.class.getResourceAsStream(shaderFile)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        } catch (IOException e) {
            System.err.println("Couldn't read: " + shaderFile);
            e.printStackTrace();
            System.exit(-1);
        }

        int shaderId = GL30.glCreateShader(type);
        GL30.glShaderSource(shaderId, stringBuilder);
        GL30.glCompileShader(shaderId);
        if (GL30.glGetShaderi(shaderId, GL30.GL_COMPILE_STATUS) == GL30.GL_FALSE) {
            System.out.println(GL30.glGetShaderInfoLog(shaderId));
            System.err.println("Couldn't compile shader: " + shaderFile);
            System.exit(-1);
        }
        return shaderId;
    }
}
