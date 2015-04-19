package to.us.harha.ld32.level;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import to.us.harha.ld32.core.InputListener;
import to.us.harha.ld32.core.util.ConfigUtils;
import to.us.harha.ld32.core.util.ResourceUtils;
import to.us.harha.ld32.gfx.Display;
import to.us.harha.ld32.gfx.Image;
import to.us.harha.ld32.level.tile.Tile;

public class Level
{

    private int        m_width;
    private int        m_height;

    private List<Tile> m_tiles;
    private Image[]    m_backgroundImages;

    public Level(int width, int height)
    {
        m_width = width;
        m_height = height;

        m_tiles = new ArrayList<Tile>();
        m_backgroundImages = new Image[3];
        m_backgroundImages[0] = new Image("images/bglayer2.png", 1.25f);
        m_backgroundImages[1] = new Image("images/bglayer0.png", 0.25f);
        m_backgroundImages[2] = new Image("images/bglayer1.png", 0.5f);
    }

    public void update(float dt)
    {
        if (InputListener.getKeyboardKeys()[InputListener.KEY_S])
        {
            saveAsSerializedObject("testlevel.lvl");
        }
        else if (InputListener.getKeyboardKeys()[InputListener.KEY_A])
        {
            loadAsSerializedObject("testlevel.lvl");
        }
    }

    public void render(int xstart, int ystart, int xend, int yend, Display display)
    {
        if (m_backgroundImages[1] != null)
        {
            for (int i = 1; i < m_backgroundImages.length; i++)
            {
                display.drawBackgroundImage(m_backgroundImages[i], xend, yend);
            }
        }

        if (!m_tiles.isEmpty())
        {
            for (Tile t : m_tiles)
            {
                float tx = t.getX() + display.getOffset().x;
                float ty = t.getY() + display.getOffset().y;

                if (tx > xstart && tx < xend && ty > ystart && ty < yend)
                    t.render(display);
            }
        }

        if (m_backgroundImages[0] != null)
        {
            display.drawBackgroundImage(m_backgroundImages[0], xend, yend);
        }
    }

    public void addTile(Tile tile)
    {
        int x = tile.getX();
        int y = tile.getY();

        if (x >= 0 && x < m_width && y >= 0 && y < m_height)
        {
            int index = x + y * m_width;

            if (getTileAtIndexLayer(index, tile.getLayer()) != null)
                return;

            m_tiles.add(tile);
        }
    }

    public void removeTile(int x, int y)
    {
        int index = x + y * m_width;

        if (x >= 0 && x < m_width && y >= 0 && y < m_height)
        {
            if (getTileAtIndex(index) != null)
                m_tiles.remove(getTileAtIndex(index));
        }
    }

    public void saveAsSerializedObject(String filename)
    {
        String path = ConfigUtils.g_res_root + "levels/" + filename;
        try
        {
            OutputStream file = new FileOutputStream(path);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            try
            {
                output.writeObject(m_tiles);
            } finally
            {
                output.close();
                ResourceUtils.g_logger.printMsg("Saved the current level as a raw object to: " + path);
            }
        } catch (IOException e)
        {
            ResourceUtils.g_logger.printErr("Error saving a level as a raw object! " + path);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadAsSerializedObject(String filename)
    {
        String path = ConfigUtils.g_res_root + "levels/" + filename;
        try
        {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            try
            {
                m_tiles = (ArrayList<Tile>) input.readObject();
            } catch (ClassNotFoundException e)
            {
                ResourceUtils.g_logger.printErr(e.getStackTrace().toString());
            } finally
            {
                input.close();
                ResourceUtils.g_logger.printMsg("Loaded the current level as a raw object from: " + path);
            }
        } catch (IOException e)
        {
            ResourceUtils.g_logger.printErr("Error loading a level as a raw object! " + path);
        }
    }

    public int getWidth()
    {
        return m_width;
    }

    public int getHeight()
    {
        return m_height;
    }

    public ArrayList<Tile> getTiles()
    {
        return (ArrayList<Tile>) m_tiles;
    }

    public Tile getTileAtIndex(int index)
    {
        for (Tile t : m_tiles)
        {
            if (t.getIndex() == index)
                return t;
        }
        return null;
    }

    public Tile getTileAtIndexLayer(int index, int layer)
    {
        for (Tile t : m_tiles)
        {
            if (t.getIndex() == index && t.getLayer() == layer)
                return t;
        }
        return null;
    }

}
