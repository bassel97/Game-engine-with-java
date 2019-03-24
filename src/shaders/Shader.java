package shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

public class Shader {

	int vertexShaderId, fragShaderId, programId;

	String vertexShader, fragmentShader;

	public Shader(String vertexShader, String fragmentShader) {
		this.vertexShader = vertexShader;
		this.fragmentShader = fragmentShader;
	}

	public void loadToGPU() {

		programId = GL30.glCreateProgram();

		vertexShaderId = GL30.glCreateShader(GL30.GL_VERTEX_SHADER);
		GL30.glShaderSource(vertexShaderId, readShaderProgram(vertexShader));
		GL30.glCompileShader(vertexShaderId);

		if (GL30.glGetShaderi(vertexShaderId, GL30.GL_COMPILE_STATUS) == GL30.GL_FALSE) {
			System.err.println("Error: Vertex Shader - " + GL30.glGetShaderInfoLog(vertexShaderId));
		}
		
		fragShaderId = GL30.glCreateShader(GL30.GL_FRAGMENT_SHADER);
		GL30.glShaderSource(fragShaderId, readShaderProgram(fragmentShader));
		GL30.glCompileShader(fragShaderId);

		if (GL30.glGetShaderi(fragShaderId, GL30.GL_COMPILE_STATUS) == GL30.GL_FALSE) {
			System.err.println("Error: Fragment Shader - " + GL30.glGetShaderInfoLog(fragShaderId));
		}
		
		GL30.glAttachShader(programId, vertexShaderId);
		GL30.glAttachShader(programId, fragShaderId);
		
		bindAttribute(0, "vertices");
		bindAttribute(1, "uvs");
		
		GL30.glLinkProgram(programId);
		if (GL30.glGetProgrami(programId, GL30.GL_LINK_STATUS) == GL30.GL_FALSE) {
			System.err.println("Error: Program Linking - " + GL30.glGetShaderInfoLog(fragShaderId));
		}
		GL30.glValidateProgram(programId);
		if (GL30.glGetProgrami(programId, GL30.GL_VALIDATE_STATUS) == GL30.GL_FALSE) {
			System.err.println("Error: Program Validate - " + GL30.glGetShaderInfoLog(fragShaderId));
		}
		
	}
	
	public void bindAttribute(int index,String name) {
		GL30.glBindAttribLocation(programId, index, name);
	}
	
	public void use() {
		GL30.glUseProgram(programId);
	}
	
	public void removeFromGPU() {
		GL30.glDetachShader(programId, vertexShaderId);
		GL30.glDetachShader(programId, fragShaderId);
		
		GL30.glDeleteShader(vertexShaderId);
		GL30.glDeleteShader(fragShaderId);
		
		GL30.glDeleteProgram(programId);
	}

	public void setUniformVec4(String name, Vector4f vec4) {
		int id = GL30.glGetUniformLocation(programId, name);
		GL30.glUniform4f(id, vec4.x, vec4.y, vec4.z, vec4.w);		
	}
	
	public void setUniformMat4(String name, Matrix4f vec4) {
		int id = GL30.glGetUniformLocation(programId, name);
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		vec4.get(buffer);
		GL30.glUniformMatrix4fv(id, false, buffer);
	}
	
	public void setUniformFloat(String name, float val) {
		int id = GL30.glGetUniformLocation(programId, name);
		GL30.glUniform1f(id, val);		
	}
	
	String readShaderProgram(String fileName) {
		StringBuilder prog = new StringBuilder();

		String line;
		try {
			FileReader fReader = new FileReader(fileName);
			BufferedReader bReader = new BufferedReader(fReader);

			while ((line = bReader.readLine()) != null) {
				prog.append(line + "\n");
			}

			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prog.toString();
	}

}
