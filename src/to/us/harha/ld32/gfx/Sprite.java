package to.us.harha.ld32.gfx;

import java.io.Serializable;

import to.us.harha.ld32.core.util.ResourceUtils;

public class Sprite implements Serializable
{

	private int         m_width;
	private int         m_height;
	private int         m_sheet_x;
	private int         m_sheet_y;
	private int[]       m_pixels;
	private SpriteSheet m_sheet;

	public Sprite(int width, int height, int sheet_x, int sheet_y, int sheet_stepx, int sheet_stepy, SpriteSheet sheet)
	{
		m_width = width;
		m_height = height;
		m_sheet_x = sheet_x * sheet_stepx;
		m_sheet_y = sheet_y * sheet_stepy;
		m_pixels = new int[m_width * m_height];
		m_sheet = sheet;

		ResourceUtils.g_logger.printMsg("Loading a new sprite... " + sheet.getFileName() + "[" + sheet_x + " " + sheet_y + "]");

		for (int y = 0; y < m_height; y++)
		{
			for (int x = 0; x < m_width; x++)
			{
				m_pixels[x + y * m_width] = m_sheet.getPixel((x + m_sheet_x) + (y + m_sheet_y) * m_sheet.getWidth());
			}
		}
	}

	public int getWidth()
	{
		return m_width;
	}

	public int getHeight()
	{
		return m_height;
	}

	public int getSheetX()
	{
		return m_sheet_x;
	}

	public int getSheetY()
	{
		return m_sheet_y;
	}

	public int getPixel(int i)
	{
		return m_pixels[i];
	}

	public SpriteSheet getSheet()
	{
		return m_sheet;
	}

}