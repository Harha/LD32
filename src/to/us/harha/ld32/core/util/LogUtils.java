package to.us.harha.ld32.core.util;

public class LogUtils
{

	private String m_prefix;

	public LogUtils(String prefix)
	{
		m_prefix = prefix;
		printMsg("Logger has started!");
	}

	public void printMsg(String msg)
	{
		System.out.println("[" + m_prefix + "]: " + msg);
	}

	public void printErr(String msg)
	{
		System.err.println("[" + m_prefix + "]: ERROR: " + msg);
	}

}
