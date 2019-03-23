#version 400 core

in vec2 coords;

out vec4 fragColor;

uniform sampler2D sampler;

void main(){

	fragColor = texture(sampler,coords);

}