#version 400 core

in vec3 colour;

out vec4 out_colour;

void main(void) {
    out_colour = vec4(colour, 1.0);
}