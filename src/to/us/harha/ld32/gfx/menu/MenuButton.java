package to.us.harha.ld32.gfx.menu;

import to.us.harha.ld32.Main;
import to.us.harha.ld32.core.InputListener;
import to.us.harha.ld32.gfx.Display;
import to.us.harha.ld32.gfx.Font;
import to.us.harha.ld32.gfx.Sprite;

public class MenuButton extends MenuComponent
{

	public MenuButton(String identifier, int x, int y, int width, int height, String text, Font font, Sprite sprite_off, Sprite sprite_on)
	{
		super(identifier, x, y, width, height, text, font, sprite_off, sprite_on);
		m_x -= m_width / 2;
		m_y -= m_height / 2;
	}

	@Override
	public void update(float dt)
	{
		int mousex = InputListener.getMouseX() / Main.g_scale;
		int mousey = InputListener.getMouseY() / Main.g_scale;

		if (m_selected && m_mouseover && !InputListener.getMouseButtons()[1])
		{
			m_clicked = true;
		} else
		{
			m_clicked = false;
		}

		if (mousex >= m_x && mousex < m_x + m_width && mousey >= m_y && mousey < m_y + m_height || m_selected)
		{
			m_mouseover = true;
		} else
		{
			m_mouseover = false;
		}

		if (m_mouseover && InputListener.getMouseButtons()[1])
		{
			m_selected = true;
		} else
		{
			m_selected = false;
		}
	}

	@Override
	public void render(Display display)
	{
		display.drawSpriteStretched(m_x, m_y, m_width, m_height, false, m_sprite_off);

		if (m_selected)
			display.drawSpriteStretched(m_x, m_y, m_width, m_height, false, m_sprite_on);

		if (m_font != null)
		{
			if (!m_mouseover)
				m_font.render(m_x + m_font.getSpacingX() / 2, m_y + m_font.getSpacingY() / 3, 0xFFFFFFFF, m_text, display);
			else
				m_font.render(m_x + m_font.getSpacingX() / 2, m_y + m_font.getSpacingY() / 3, 0xFF00FFFF, m_text, display);
		}

	}

}