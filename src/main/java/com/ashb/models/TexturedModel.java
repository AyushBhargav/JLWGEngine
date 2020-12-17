package com.ashb.models;

import com.ashb.textures.ModelTexture;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TexturedModel {
    private final RawModel rawModel;
    private final ModelTexture modelTexture;
}
