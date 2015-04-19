package to.us.harha.ld32.gfx;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import to.us.harha.ld32.core.util.ConfigUtils;
import to.us.harha.ld32.core.util.ResourceUtils;

public class SpriteSheet implements Serializable
{

	private int    m_width;
	private int    m_height;
	private int[]  m_pixels;
	private String m_fileName;

	public SpriteSheet(String fileName)
	{
		m_fileName = fileName;

		try
		{
			String path = ConfigUtils.g_res_root + m_fileName;
			ResourceUtils.g_logger.printMsg("Loading a new spritesheet: " + path);
			BufferedImage image = ImageIO.read(new FileInputStream(path));
			m_width = image.getWidth();
			m_height = image.getHeight();
			m_pixels = new int[m_width * m_height];
			image.getRGB(0, 0, m_width, m_height, m_pixels, 0, m_width);
		} catch (IOException e)
		{
			ResourceUtils.g_logger.printErr(e.toString());
			System.exit(1);
		}
	}

	public Sprite[] extractToSpriteArray(int sprite_width, int sprite_height)
	{
		int swidth = (getWidth() / sprite_width);
		int sheight = (getHeight() / sprite_height);
		Sprite[] resource = new Sprite[swidth * sheight];

		for (int y = 0; y < sheight; y++)
		{
			for (int x = 0; x < swidth; x++)
			{
				resource[x + y * swidth] = new Sprite(sprite_width, sprite_height, x, y, sprite_width, sprite_height, this);
			}
		}

		return resource;
	}

	public int getWidth()
	{
		return m_width;
	}

	public int getHeight()
	{
		return m_height;
	}

	public int getPixel(int i)
	{
		return m_pixels[i];
	}

	public String getFileName()
	{
		return m_fileName;
	}

}