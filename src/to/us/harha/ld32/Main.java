package to.us.harha.ld32;

import to.us.harha.ld32.core.CoreEngine;
import to.us.harha.ld32.core.util.ConfigUtils;
import to.us.harha.ld32.core.util.ResourceUtils;
import to.us.harha.ld32.gfx.Display;

public class Main
{

    public static final String g_title  = "LD32";
    public static final int    g_width  = 640;
    public static final int    g_height = 360;
    public static final int    g_scale  = 2;

    public static void main(String[] args)
    {
        ConfigUtils.init();
        ResourceUtils.load();
        Display display = new Display(g_width, g_height, g_scale, g_title);
        display.create();
        CoreEngine engine = new CoreEngine(display);
        engine.start();
    }

}
