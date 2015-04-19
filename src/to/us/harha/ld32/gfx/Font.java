package to.us.harha.ld32.gfx;

import to.us.harha.ld32.core.util.ResourceUtils;

public class Font
{

	private String   m_characters;
	private Sprite[] m_sprites;
	private int      m_type;
	private int      m_spacing_x;
	private int      m_spacing_y;

	public Font(int type, int spacing_x, int spacing_y)
	{
		// Font types, 'layouts', hardcoded
		m_type = type;
		m_spacing_x = spacing_x;
		m_spacing_y = spacing_y;

		// Font g_font_main_320x54
		if (m_type <= 0)
		{
			m_sprites = ResourceUtils.g_fc_font_main_320x54;
			m_characters = " ! #   '() +,-. 0123" +
						   "456789:;<=>? ABCDEFG" +
						   "HIJKLMNOPQRSTUVWXYZ ";
			return;
		}
		
		// Font g_font_main_320x48
		if (m_type == 1)
		{
			m_sprites = ResourceUtils.g_fc_font_main_320x48;
			m_characters = " ! #   '() +,-. 0123" +
						   "456789:;<=>? ABCDEFG" +
						   "HIJKLMNOPQRSTUVWXYZ ";
			return;
		}
		
		// Font g_font_main_320x500
		if (m_type == 2)
		{
			m_sprites = ResourceUtils.g_fc_font_main_320x500;
			m_characters = "ACEGI" + 
						   "BDFHJ" +
						   "KMOQS" +
						   "LNPRT" +
						   "UWY02" +
						   "VXZ13" +
						   "468()" +
						   "579!:" +
						   "-,\"  " + 
						   ".'?  ";
			return;
		}
		
		// Font NULL
		m_type = -1;
		m_sprites = ResourceUtils.g_fc_font_main_320x54;
		m_characters = " ! #   '() +,-. 0123" +
					   "456789:;<=>? ABCDEFG" +
					   "HIJKLMNOPQRSTUVWXYZ ";
	}
	
	public void render(int x, int y, String text, Display display)
	{
		for (int i = 0; i < text.length(); i++)
		{
			char text_current = text.charAt(i);
			int char_current = m_characters.indexOf(text_current);
			
			if (char_current == -1)
				continue;
			
			boolean space = false;
			
			if (text_current == ' ')
				space = true;
			
			if (space)
				x -= m_spacing_x / 2;

			display.drawSprite(x + i * m_spacing_x, y, false, m_sprites[char_current]);
		}
	}

	public void render(int x, int y, int c, String text, Display display)
	{
		for (int i = 0; i < text.length(); i++)
		{
			char text_current = text.charAt(i);
			int char_current = m_characters.indexOf(text_current);
			
			if (char_current == -1)
				continue;
			
			boolean space = false;
			
			if (text_current == ' ')
				space = true;
			
			if (space)
				x -= m_spacing_x / 2;

			display.drawSpriteColored(x + i * m_spacing_x, y, c, false, m_sprites[char_current]);
		}
	}

	public String getCharacters()
	{
		return m_characters;
	}

	public Sprite[] getSprites()
	{
		return m_sprites;
	}

	public int getType()
	{
		return m_type;
	}

	public int getSpacingX()
	{
		return m_spacing_x;
	}

	public int getSpacingY()
	{
		return m_spacing_y;
	}

	public void setCharacters(String m_characters)
	{
		this.m_characters = m_characters;
	}

	public void setSprites(Sprite[] m_sprites)
	{
		this.m_sprites = m_sprites;
	}

	public void setType(int m_type)
	{
		this.m_type = m_type;
	}

	public void setSpacingX(int m_spacing_x)
	{
		this.m_spacing_x = m_spacing_x;
	}

	public void setSpacingY(int m_spacing_y)
	{
		this.m_spacing_y = m_spacing_y;
	}

}