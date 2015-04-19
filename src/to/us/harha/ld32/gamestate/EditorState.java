package to.us.harha.ld32.gamestate;

import java.util.ArrayList;

import to.us.harha.ld32.Main;
import to.us.harha.ld32.core.InputListener;
import to.us.harha.ld32.core.util.ConfigUtils;
import to.us.harha.ld32.core.util.ResourceUtils;
import to.us.harha.ld32.core.util.math.Vec2f;
import to.us.harha.ld32.gfx.Display;
import to.us.harha.ld32.gfx.Sprite;
import to.us.harha.ld32.gfx.SpriteSheet;
import to.us.harha.ld32.gfx.menu.MenuButton;
import to.us.harha.ld32.gfx.menu.MenuComponent;
import to.us.harha.ld32.level.Level;
import to.us.harha.ld32.level.tile.TileNormal;

public class EditorState extends GameState
{

    private int                            m_gridSize;
    private Vec2f                          m_cursor_pos;
    private Vec2f                          m_cursor_pos_tile;
    private Sprite                         m_cursor_sp;
    private Sprite                         m_cursor_sp_selected;
    private SpriteSheet                    m_sh_selected;
    private Enum<ConfigUtils.g_tile_types> m_tile_selected_type;
    private Sprite                         m_tile_selected_sprite;
    private int                            m_currentTileLayer;
    private ArrayList<MenuComponent>       m_sp_buttons;
    private Level                          m_level;

    public EditorState(GSM gsm)
    {
        super(gsm);

        m_gridSize = 16;
        m_cursor_pos = new Vec2f();
        m_cursor_pos_tile = new Vec2f();
        m_cursor_sp = new Sprite(16, 16, 0, 6, 16, 16, ResourceUtils.g_sh_menu);
        m_cursor_sp_selected = new Sprite(16, 16, 1, 6, 16, 16, ResourceUtils.g_sh_menu);
        m_sh_selected = ResourceUtils.g_sh_level;
        m_tile_selected_type = ConfigUtils.g_tile_types.NORMAL;
        m_tile_selected_sprite = new Sprite(16, 16, (int) (4.0 * Math.random()), 0, 16, 16, m_sh_selected);
        m_currentTileLayer = 0;
        m_sp_buttons = new ArrayList<MenuComponent>();

        updateSpriteSheet(m_sh_selected, ResourceUtils.g_sa_level);

        m_level = new Level(1028, 512);
    }

    @Override
    public void update(float dt)
    {

        // Update the current level
        m_level.update(dt);

        // Update the selected spritesheet
        if (!m_sp_buttons.isEmpty())
        {
            for (MenuComponent mc : m_sp_buttons)
            {
                mc.update(dt);
                if (mc.getClicked())
                {
                    m_tile_selected_sprite = mc.getSpriteOff();
                }
            }
        }

        int borderX = (int) (m_gsm.getDisplay().getWidth() / 1.6) + 32;
        int sheetY = (int) (m_gsm.getDisplay().getHeight() / 1.6) - m_sh_selected.getHeight() / 4 - 1;

        // Update the cursor position & snap it to grid
        int cursorX = (InputListener.getMouseX() / Main.g_scale);
        int cursorY = (InputListener.getMouseY() / Main.g_scale);
        int offsetX = (int) m_gsm.getDisplay().getOffset().x;
        int offsetY = (int) m_gsm.getDisplay().getOffset().y;
        m_cursor_pos.x = (cursorX - cursorX % m_gridSize) + (offsetX % m_gridSize);
        m_cursor_pos.y = (cursorY - cursorY % m_gridSize) + (offsetY % m_gridSize);

        m_cursor_pos_tile.x = m_cursor_pos.x - offsetX;
        m_cursor_pos_tile.y = m_cursor_pos.y - offsetY;

        // Add the chosen tile to the level if mouse 1 is pressed
        if (InputListener.getMouseButtons()[1])
        {
            if (m_cursor_pos.x < borderX - 16)
                addTile((int) m_cursor_pos_tile.x, (int) m_cursor_pos_tile.y, m_currentTileLayer);
        }
        else if (InputListener.getMouseButtons()[3])
        {
            if (m_cursor_pos.x < borderX - 16)
                m_level.removeTile((int) m_cursor_pos_tile.x, (int) m_cursor_pos_tile.y);
        }

        if (m_cursor_pos.x > borderX - 32)
            m_cursor_pos.x = borderX - 32;

        if (InputListener.getKeyboardKeys()[InputListener.KEY_UP])
        {
            m_gsm.getDisplay().setOffset(new Vec2f(0, 0.25f * dt));
        }
        else if (InputListener.getKeyboardKeys()[InputListener.KEY_DOWN])
        {
            m_gsm.getDisplay().setOffset(new Vec2f(0, -0.25f * dt));
        }
        if (InputListener.getKeyboardKeys()[InputListener.KEY_LEFT])
        {
            m_gsm.getDisplay().setOffset(new Vec2f(0.25f * dt, 0));
        }
        else if (InputListener.getKeyboardKeys()[InputListener.KEY_RIGHT])
        {
            m_gsm.getDisplay().setOffset(new Vec2f(-0.25f * dt, 0));
        }

        if (InputListener.getKeyboardKeys()[InputListener.KEY_1])
            m_currentTileLayer = 1;
        else if (InputListener.getKeyboardKeys()[InputListener.KEY_2])
            m_currentTileLayer = 2;
        else if (InputListener.getKeyboardKeys()[InputListener.KEY_0])
            m_currentTileLayer = 0;

    }

    @Override
    public void render(Display display)
    {
        int borderX = (int) (display.getWidth() / 1.6) + 16;
        int sheetY = (int) (display.getHeight() / 1.6) - m_sh_selected.getHeight() / 4 - 1;

        // Render the level
        m_level.render(-16, -16, borderX, display.getHeight(), display);

        // Render the selected spritesheet
        if (!m_sp_buttons.isEmpty())
        {
            for (MenuComponent mc : m_sp_buttons)
            {
                mc.render(display);
            }
        }

        // Render the editor stuff
        display.drawSprite((int) m_cursor_pos.x, (int) m_cursor_pos.y, false, m_cursor_sp);
        ResourceUtils.g_font_main_320x54.render(borderX + 16, 8, "GRID SIZE: " + m_gridSize, display);
        ResourceUtils.g_font_main_320x54.render(borderX + 16, 24 + 4, "TILES: " + m_level.getTiles().size(), display);
        ResourceUtils.g_font_main_320x54.render(borderX + 16, 40 + 8, "LAYER: " + m_currentTileLayer, display);
        ResourceUtils.g_font_main_320x54.render(borderX + 16, 56 + 12, "TILE X: " + (int) m_cursor_pos_tile.x, display);
        ResourceUtils.g_font_main_320x54.render(borderX + 16, 72 + 16, "TILE Y: " + (int) m_cursor_pos_tile.y, display);
    }

    private void updateSpriteSheet(SpriteSheet sh, Sprite[] sa)
    {
        if (!m_sp_buttons.isEmpty())
            m_sp_buttons.clear();

        int borderX = (int) (m_gsm.getDisplay().getWidth() / 1.6) + 32;
        int sheetY = (int) (m_gsm.getDisplay().getHeight() / 1.6) - sh.getHeight() / 4 - 1;

        for (int j = 0; j < sh.getHeight() / 16; j++)
        {
            for (int i = 0; i < sh.getWidth() / 16; i++)
            {
                m_sp_buttons.add(new MenuButton("spb" + (i + j * sh.getWidth()), borderX + i * 16, sheetY + j * 16, 16, 16, "", null, sa[i + j * 12], m_cursor_sp_selected));
            }
        }
    }

    private void addTile(int x, int y, int layer)
    {
        if (m_tile_selected_type == ConfigUtils.g_tile_types.NORMAL)
            m_level.addTile(new TileNormal(x, y, x + y * m_level.getWidth(), m_tile_selected_sprite, layer));
    }

}
