package to.us.harha.ld32.gamestate;

import to.us.harha.ld32.gfx.Display;

public abstract class GameState
{

	protected GSM m_gsm;

	protected GameState(GSM gsm)
	{
		m_gsm = gsm;
	}

	public abstract void update(float dt);

	public abstract void render(Display display);

}
