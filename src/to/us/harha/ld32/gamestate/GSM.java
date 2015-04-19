package to.us.harha.ld32.gamestate;

import java.util.Stack;

import to.us.harha.ld32.core.util.LogUtils;
import to.us.harha.ld32.gfx.Display;

public class GSM
{

	private Display          m_display;
	private LogUtils         m_log;
	private Stack<GameState> m_states;

	public GSM(Display display)
	{
		m_display = display;
		m_log = new LogUtils(this.getClass().getName());
		m_states = new Stack<GameState>();
	}

	public void push(GameState gs)
	{
		m_states.push(gs);
	}

	public void pop()
	{
		m_states.pop();
	}

	public void set(GameState gs)
	{
		m_states.pop();
		m_states.push(gs);
	}

	public void update(float dt)
	{
		m_states.peek().update(dt);
	}

	public void render(Display display)
	{
		m_states.peek().render(display);
	}

	public Display getDisplay()
	{
		return m_display;
	}

	public LogUtils getLogger()
	{
		return m_log;
	}

	public GameState getCurrentState()
	{
		return m_states.peek();
	}

}