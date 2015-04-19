package to.us.harha.ld32.level.tile;

import java.io.*;

import to.us.harha.ld32.core.util.ConfigUtils;
import to.us.harha.ld32.gfx.Display;
import to.us.harha.ld32.gfx.Sprite;

public abstract class Tile implements Serializable
{

    protected int                            m_x;
    protected int                            m_y;
    protected int                            m_index;
    protected Sprite                         m_sprite;
    protected Enum<ConfigUtils.g_tile_types> m_type;
    protected int                            m_layer;

    protected Tile(int x, int y, int index, Sprite sprite, int layer)
    {
        m_x = x;
        m_y = y;
        m_index = index;
        m_sprite = sprite;
        m_type = ConfigUtils.g_tile_types.NORMAL;
        m_layer = layer;
    }

    public abstract void update(float dt);

    public abstract void render(Display display);

    public int getX()
    {
        return m_x;
    }

    public int getY()
    {
        return m_y;
    }

    public int getIndex()
    {
        return m_index;
    }

    public Sprite getSprite()
    {
        return m_sprite;
    }

    public Enum<ConfigUtils.g_tile_types> getType()
    {
        return m_type;
    }
    
    public int getLayer()
    {
        return m_layer;
    }

}
