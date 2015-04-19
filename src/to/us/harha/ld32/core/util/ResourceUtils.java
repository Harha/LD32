package to.us.harha.ld32.core.util;

import to.us.harha.ld32.gfx.Font;
import to.us.harha.ld32.gfx.Sprite;
import to.us.harha.ld32.gfx.SpriteSheet;

public class ResourceUtils
{

	public static final LogUtils g_logger = new LogUtils(ResourceUtils.class.getName());

	// Spritesheets
	public static SpriteSheet    g_sh_menu;
	public static SpriteSheet    g_sh_font_main_320x54;
	public static SpriteSheet    g_sh_font_main_320x48;
	public static SpriteSheet    g_sh_font_main_320x500;
	public static SpriteSheet    g_sh_level;

	// Sprites
	public static Sprite         g_sp_menu_button_0_off;
	public static Sprite         g_sp_menu_button_0_on;
	public static Sprite         g_sp_menu_field_0_off;
	public static Sprite         g_sp_menu_field_0_on;

	// Sprite arrays
	public static Sprite[]       g_fc_font_main_320x54;
	public static Sprite[]       g_fc_font_main_320x48;
	public static Sprite[]       g_fc_font_main_320x500;
	public static Sprite[]       g_sa_level;

	// Fonts
	public static Font           g_font_main_320x54;
	public static Font           g_font_main_320x48;
	public static Font           g_font_main_320x500;

	public static void load()
	{
		g_logger.printMsg("Loading resources...");

		// Spritesheets
		g_sh_menu = new SpriteSheet("spritesheets/sh_menu.png");
		g_sh_font_main_320x54 = new SpriteSheet("fonts/font_main_320x54.png");
		g_sh_font_main_320x48 = new SpriteSheet("fonts/font_main_320x48.png");
		g_sh_font_main_320x500 = new SpriteSheet("fonts/font_main_320x500.png");
		g_sh_level = new SpriteSheet("spritesheets/sh_level.png");

		// Sprites
		g_sp_menu_button_0_off = new Sprite(32, 32, 0, 0, 16, 16, g_sh_menu);
		g_sp_menu_button_0_on = new Sprite(32, 32, 2, 0, 16, 16, g_sh_menu);
		g_sp_menu_field_0_off = new Sprite(32, 32, 0, 3, 16, 16, g_sh_menu);
		g_sp_menu_field_0_on = new Sprite(32, 32, 2, 3, 16, 16, g_sh_menu);

		// Sprite Arrays
		g_fc_font_main_320x54 = g_sh_font_main_320x54.extractToSpriteArray(16, 18);
		g_fc_font_main_320x48 = g_sh_font_main_320x48.extractToSpriteArray(16, 16);
		g_fc_font_main_320x500 = g_sh_font_main_320x500.extractToSpriteArray(64, 50);
		g_sa_level = g_sh_level.extractToSpriteArray(16, 16);

		// Fonts
		g_font_main_320x54 = new Font(0, 16, 18);
		g_font_main_320x48 = new Font(1, 16, 16);
		g_font_main_320x500 = new Font(2, 64, 50);
	}

}
