package com.ashb.model;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private final List<Integer> vaos = new ArrayList<>();
    private final List<Integer> vbos = new ArrayList<>();

    public RawModel loadToVAO(float[] positions, int[] indices) {
        int vaoID = createVAO();
        vaos.add(vaoID);

        bindIndicesBuffer(indices);
        storeDataInAttr(0, positions);
        unbindVAO();
        return new RawModel(vaoID, indices.length);
    }

    public void cleanUp() {
        vaos.forEach(GL32::glDeleteVertexArrays);
        vbos.forEach(GL30::glDeleteBuffers);
    }

    private int createVAO() {
        int vaoId = GL32.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);
        return vaoId;
    }

    private void storeDataInAttr(int attributeNumber, float[] data) {
        int vboId = GL30.glGenBuffers();
        vbos.add(vboId);

        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboId);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, mapToFloatBuffer(data), GL30.GL_STATIC_DRAW);
        GL30.glVertexAttribPointer(attributeNumber, 3, GL30.GL_FLOAT, false, 0, 0);
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
    }

    private void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    private void bindIndicesBuffer(int[] indices) {
        int vboId = GL30.glGenBuffers();
        vbos.add(vboId);

        GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, vboId);
        GL30.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, mapToIntBuffer(indices), GL30.GL_STATIC_DRAW);
    }

    private IntBuffer mapToIntBuffer(int[] data) {
        IntBuffer intBuffer = BufferUtils.createIntBuffer(data.length);
        intBuffer.put(data);
        intBuffer.flip();
        return intBuffer;
    }

    private FloatBuffer mapToFloatBuffer(float[] data) {
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(data.length);
        floatBuffer.put(data);
        floatBuffer.flip();
        return floatBuffer;
    }
}
