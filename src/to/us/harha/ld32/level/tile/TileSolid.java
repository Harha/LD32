package to.us.harha.ld32.level.tile;

import to.us.harha.ld32.core.util.ConfigUtils;
import to.us.harha.ld32.gfx.Display;
import to.us.harha.ld32.gfx.Sprite;

public class TileSolid extends Tile
{

	public TileSolid(int x, int y, int index, Sprite sprite, int layer)
	{
		super(x, y, index, sprite, layer);
		m_type = ConfigUtils.g_tile_types.SOLID;
	}

	@Override
	public void update(float dt)
	{

	}

	@Override
	public void render(Display display)
	{
		display.drawSprite(m_x, m_y, true, m_sprite);
	}

}
