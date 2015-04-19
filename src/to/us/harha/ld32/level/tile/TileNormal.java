package to.us.harha.ld32.level.tile;

import to.us.harha.ld32.gfx.Display;
import to.us.harha.ld32.gfx.Sprite;

public class TileNormal extends Tile
{

	public TileNormal(int x, int y, int index, Sprite sprite, int layer)
	{
		super(x, y, index, sprite, layer);
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
