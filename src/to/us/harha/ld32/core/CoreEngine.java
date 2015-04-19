package to.us.harha.ld32.core;

import to.us.harha.ld32.core.util.LogUtils;
import to.us.harha.ld32.core.util.TimeUtils;
import to.us.harha.ld32.gamestate.EditorState;
import to.us.harha.ld32.gamestate.GSM;
import to.us.harha.ld32.gfx.Display;

public class CoreEngine implements Runnable
{

	private boolean       m_running;

	private Display       m_display;
	private GSM           m_gsm;
	private InputListener m_iListener;
	private LogUtils      m_log;

	public CoreEngine(Display display)
	{
		m_running = false;

		m_log = new LogUtils(this.getClass().getName());
		m_display = display;
		m_gsm = new GSM(m_display);
		m_iListener = new InputListener();

		m_display.addKeyListener(m_iListener);
		m_display.addMouseListener(m_iListener);
		m_display.addMouseMotionListener(m_iListener);
	}

	public synchronized void start()
	{
		if (!m_running)
		{
			m_gsm.push(new EditorState(m_gsm));
			m_running = true;
			run();
		}
	}

	public synchronized void stop()
	{
		if (m_running)
		{
			m_running = false;
			System.exit(0);
		}
	}

	@Override
	public void run()
	{
		// Update the delta-time once before going into the main loop
		TimeUtils.init();
		TimeUtils.updateDelta();
		TimeUtils.updateFPS();
		// Request focus of the window
		m_display.requestFocus();

		while (m_running)
		{
			TimeUtils.updateDelta();
			TimeUtils.updateFPS();
			m_display.setTitle(String.format("DeltaTime: %2.2f FPS: %2.2f", TimeUtils.getDelta(), TimeUtils.getFPS()));
			update((float) TimeUtils.getDelta());
			render();
		}
		stop();
	}

	public void update(float dt)
	{
		m_gsm.update(dt);
	}

	public void render()
	{
		m_display.clear();
		m_gsm.render(m_display);
		m_display.render();
	}

}
