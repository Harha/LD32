package to.us.harha.ld32.gfx.menu;

import to.us.harha.ld32.gfx.Display;
import to.us.harha.ld32.gfx.Font;
import to.us.harha.ld32.gfx.Sprite;

public abstract class MenuComponent
{

    protected String  m_identifier;
    protected int     m_x;
    protected int     m_y;
    protected int     m_width;
    protected int     m_height;
    protected boolean m_selected;
    protected boolean m_clicked;
    protected boolean m_mouseover;
    protected String  m_text;
    protected Font    m_font;
    protected Sprite  m_sprite_off;
    protected Sprite  m_sprite_on;

    protected MenuComponent(String identifier, int x, int y, int width, int height, String text, Font font, Sprite sprite_off, Sprite sprite_on)
    {
        m_identifier = identifier;
        m_x = x;
        m_y = y;
        m_width = width;
        m_height = height;
        m_selected = false;
        m_clicked = false;
        m_mouseover = false;
        m_text = text;
        m_font = font;
        m_sprite_off = sprite_off;
        m_sprite_on = sprite_on;
    }

    public abstract void update(float dt);

    public abstract void render(Display display);

    public String getIdentifier()
    {
        return m_identifier;
    }

    public boolean getClicked()
    {
        return m_clicked;
    }

    public String getText()
    {
        return m_text;
    }

    public Sprite getSpriteOff()
    {
        return m_sprite_off;
    }

    public Sprite getSpriteOn()
    {
        return m_sprite_on;
    }

}
