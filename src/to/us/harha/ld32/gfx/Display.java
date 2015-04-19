package to.us.harha.ld32.gfx;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.JFrame;

import to.us.harha.ld32.core.util.math.Vec2f;

public class Display extends Canvas
{

    private static final long serialVersionUID = -7692060454281152543L;

    private String            m_title;
    private int               m_width;
    private int               m_height;
    private int               m_scale;
    private int[]             m_pixels;
    private Vec2f             m_offset;
    private BufferedImage     m_image;
    private Dimension         m_dimension;
    private JFrame            m_jframe;
    private BufferStrategy    m_bufferstrategy;

    public Display(int width, int height, int scale, String title)
    {
        m_title = title;
        m_width = width;
        m_height = height;
        m_scale = scale;
    }

    public void create()
    {
        // Create the bitmap
        if (m_image == null)
        {
            m_image = new BufferedImage(m_width, m_height, BufferedImage.TYPE_INT_RGB);
            m_pixels = ((DataBufferInt) m_image.getRaster().getDataBuffer()).getData();
            m_offset = new Vec2f();
            clear();
        }

        // Create the jframe
        if (m_jframe == null)
        {
            m_dimension = new Dimension(m_width * m_scale, m_height * m_scale);
            setPreferredSize(m_dimension);
            m_jframe = new JFrame();
            m_jframe.setResizable(false);
            m_jframe.setTitle(m_title);
            m_jframe.add(this);
            m_jframe.pack();
            m_jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            m_jframe.setLocationRelativeTo(null);
            m_jframe.setVisible(true);
        }

        // Create a buffer strategy using triplebuffering
        if (m_bufferstrategy == null)
        {
            createBufferStrategy(3);
            m_bufferstrategy = getBufferStrategy();
        }
    }

    public void render()
    {
        Graphics g = m_bufferstrategy.getDrawGraphics();
        g.drawImage(m_image, 0, 0, m_width * m_scale, m_height * m_scale, null);
        g.dispose();
        m_bufferstrategy.show();
    }

    public void clear()
    {
        Arrays.fill(m_pixels, 0x00000000);
    }

    public void drawPixelInt(int x, int y, int color)
    {
        if (x < 0 || x >= m_width || y < 0 || y >= m_height)
            return;

        m_pixels[x + y * m_width] = color;
    }

    public void drawSprite(int x, int y, boolean translate, Sprite sprite)
    {
        int width = sprite.getWidth();
        int height = sprite.getHeight();

        for (int j = 0; j < height; j++)
        {
            int ya = j + (y + (int) ((translate) ? m_offset.y : 0));
            for (int i = 0; i < width; i++)
            {
                int xa = i + (x + (int) ((translate) ? m_offset.x : 0));

                int color = sprite.getPixel(i + j * sprite.getWidth());

                // Transparent color
                if (color == 0xFFFF00FF)
                    continue;

                drawPixelInt(xa, ya, color);
            }
        }
    }

    public void drawSpriteColored(int x, int y, int c, boolean translate, Sprite sprite)
    {
        int width = sprite.getWidth();
        int height = sprite.getHeight();

        for (int j = 0; j < height; j++)
        {
            int ya = j + (y + (int) ((translate) ? m_offset.y : 0));
            for (int i = 0; i < width; i++)
            {
                int xa = i + (x + (int) ((translate) ? m_offset.x : 0));

                int color = sprite.getPixel(i + j * sprite.getWidth());

                // Only accept white
                if (color == 0xFFFF00FF)
                    continue;

                drawPixelInt(xa, ya, c);
            }
        }
    }

    public void drawSpriteStretched(int x, int y, int width, int height, boolean translate, Sprite sprite)
    {
        int sw = sprite.getWidth();
        int sh = sprite.getHeight();

        for (int j = 0; j < height; j++)
        {
            int ya = j + (y + (int) ((translate) ? m_offset.y : 0));
            int ja = (j % sh);

            if (j > sh / 4 && j < height - sh / 2)
            {
                ja = sh / 2;
            }

            for (int i = 0; i < width; i++)
            {
                int xa = i + (x + (int) ((translate) ? m_offset.x : 0));
                int ia = (i % sw);

                if (i > sw / 4 && i < width - sw / 2)
                {
                    ia = sw / 2;
                }

                int color = sprite.getPixel(ia + ja * sprite.getWidth());

                // Transparent color
                if (color == 0xFFFF00FF)
                    continue;

                drawPixelInt(xa, ya, color);
            }
        }
    }

    public void drawBackgroundImage(Image image, int xend, int yend)
    {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int j = 0; j < m_height; j++)
        {
            if (j > yend)
                continue;

            int ya = (int) (j - m_offset.y * image.getSpeed());
            for (int i = 0; i < m_width; i++)
            {
                if (i > xend)
                    continue;

                int xa = (int) (i - m_offset.x * image.getSpeed());
                int xp = Math.abs(xa) % width;
                int yp = Math.abs(ya) % height;

                // Don't render transparent color (MAGENTA)
                int color = image.getPixel(xp + yp * width);
                if (color == 0xFFFF00FF)
                    continue;

                drawPixelInt(i, j, color);
            }
        }
    }

    public String getTitle()
    {
        return m_title;
    }

    public int getWidth()
    {
        return m_width;
    }

    public int getHeight()
    {
        return m_height;
    }

    public int[] getPixels()
    {
        return m_pixels;
    }

    public Vec2f getOffset()
    {
        return m_offset;
    }

    public BufferedImage getImage()
    {
        return m_image;
    }

    public Dimension getDimension()
    {
        return m_dimension;
    }

    public JFrame getJFrame()
    {
        return m_jframe;
    }

    public float getAR()
    {
        return (float) m_width / (float) m_height;
    }

    public int getScale()
    {
        return m_scale;
    }

    public void setTitle(String title)
    {
        m_jframe.setTitle(m_title + " | " + title);
    }

    public void setWidth(int m_width)
    {
        this.m_width = m_width;
    }

    public void setHeight(int m_height)
    {
        this.m_height = m_height;
    }

    public void setPixels(int[] m_pixels)
    {
        this.m_pixels = m_pixels;
    }

    public void setOffset(Vec2f offset)
    {
        m_offset.x += offset.x;
        m_offset.y += offset.y;
    }

    public void setImage(BufferedImage m_image)
    {
        this.m_image = m_image;
    }

    public void setDimension(Dimension m_dimension)
    {
        this.m_dimension = m_dimension;
    }

    public void setJFrame(JFrame m_jframe)
    {
        this.m_jframe = m_jframe;
    }

    public void setScale(int m_scale)
    {
        this.m_scale = m_scale;
    }

}
