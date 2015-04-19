package to.us.harha.ld32.gamestate;

import to.us.harha.ld32.gfx.Display;
import to.us.harha.ld32.level.Level;

public class PlayState extends GameState
{

	private Level m_level;

	public PlayState(GSM gsm)
	{
		super(gsm);
		m_level = new Level(512, 256);
	}

	@Override
	public void update(float dt)
	{

	}

	@Override
	public void render(Display display)
	{
		//m_level.render(display);
	}

}
