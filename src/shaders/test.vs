#version 400 core

in vec3 vertices;
in vec2 uvs;

out vec2 coords;

uniform mat4 transformationMatrix;

void main(){

	vec4 pos = transformationMatrix * vec4(vertices,1.0);

	gl_Position = pos;
	
	coords = uvs;

}