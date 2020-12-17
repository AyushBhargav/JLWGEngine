package com.ashb.shaders;

public class StaticShader extends AbstractShader {

    private static final String VERTEX_FILE = "/shaders/vertex.shader";
    private static final String FRAGMENT_FILE = "/shaders/fragment.shader";

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }
}
