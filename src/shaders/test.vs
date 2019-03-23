#version 400 core

in vec3 vertices;
in vec2 uvs;

out vec4 color;
out vec2 coords;

uniform float xOffset;

void main(){

	vec4 pos = vec4(vertices,1.0);

	pos.x = pos.x + xOffset;

	gl_Position = pos;
	
	color = vec4(0.0, 0.0, 0.0, 1.0);
	
	coords = uvs;

}