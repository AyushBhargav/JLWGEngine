#version 400 core

in vec2 out_textureCoords;

out vec4 out_colour;

uniform sampler2D textureSampler;

void main(void) {
    out_colour = texture(textureSampler, out_textureCoords);

}