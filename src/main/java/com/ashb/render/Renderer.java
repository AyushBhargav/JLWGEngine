package com.ashb.render;

import com.ashb.models.RawModel;
import com.ashb.models.TexturedModel;
import org.lwjgl.opengl.GL30;

public class Renderer {

    public void prepare() {
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
        GL30.glClearColor(1, 0, 0, 1);
    }

    public void render(TexturedModel texturedModel) {
        RawModel model = texturedModel.getRawModel();
        GL30.glBindVertexArray(model.getVaoId());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glActiveTexture(GL30.GL_TEXTURE0);
        GL30.glBindTexture(GL30.GL_TEXTURE_2D, texturedModel.getModelTexture().getTextureId());
        GL30.glDrawElements(GL30.GL_TRIANGLES, model.getVertexCount(), GL30.GL_UNSIGNED_INT, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }
}
