package to.us.harha.ld32.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigUtils
{

	public static final LogUtils g_logger   = new LogUtils(ResourceUtils.class.getName());
	public static final String   g_res_root = "./res/";

	public static enum g_tile_types
	{
		NORMAL, SOLID
	}

	private ConfigUtils()
	{

	}

	public static void init()
	{
		File f = new File(g_res_root + "config.cfg");
		if (f.exists() && !f.isDirectory())
		{
			load();
		} else
		{
			create();
			init();
		}
	}

	public static void create()
	{
		Properties p = new Properties();
		OutputStream o = null;
		try
		{
			o = new FileOutputStream(g_res_root + "config.cfg");

			// Set each variable

			// Store the variables
			p.store(o, null);

			// Close the outputstream object
			o.close();

			g_logger.printMsg(g_res_root + "config.cfg" + " Created succesfully!");
		} catch (IOException e)
		{
			g_logger.printErr("Couldn't create the main configuration file, closing program...");
			System.exit(1);
		}
	}

	public static void load()
	{
		Properties p = new Properties();
		InputStream i = null;
		try
		{
			i = new FileInputStream(g_res_root + "config.cfg");

			// Load the file
			p.load(i);

			// Get the properties and set the config variables

			// Close the inputstream object
			i.close();

			g_logger.printMsg(g_res_root + "config.cfg" + " loaded succesfully!");
		} catch (IOException e)
		{
			g_logger.printErr("Couldn't load the main configuration file, closing program...");
			System.exit(1);
		}
	}

}
