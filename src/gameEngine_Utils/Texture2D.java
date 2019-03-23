package gameEngine_Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

public class Texture2D {

	int textureId;
	int width, height;

	public Texture2D(String imageName) {
		BufferedImage image;

		try {
			image = ImageIO.read(new File(imageName));

			width = image.getWidth();
			height = image.getHeight();

			int[] pixelsRaw = new int[width * height * 4];
			pixelsRaw = image.getRGB(0, 0, width, height, null, 0, width);

			ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
			
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					int pixel = pixelsRaw[i * height + j];					
					pixels.put((byte) ((pixel >> 16) & 0xff));
					pixels.put((byte) ((pixel >> 8) & 0xff));
					pixels.put((byte) (pixel & 0xff));
					pixels.put((byte) ((pixel >> 24) & 0xff));
				}
			}
			
			pixels.flip();
			
			textureId = GL30.glGenTextures();
			
			GL30.glBindTexture(GL30.GL_TEXTURE_2D, textureId);
			
			GL30.glTexParameterIi(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_NEAREST);
			GL30.glTexParameterIi(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_NEAREST);
			
			GL30.glTexImage2D(GL30.GL_TEXTURE_2D, 0, GL30.GL_RGBA, width, height, 0, GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE, pixels);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void bind() {
			GL30.glBindTexture(GL30.GL_TEXTURE_2D, textureId);
	}

}
