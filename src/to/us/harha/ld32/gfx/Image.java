package to.us.harha.ld32.gfx;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import to.us.harha.ld32.core.util.ConfigUtils;
import to.us.harha.ld32.core.util.ResourceUtils;


public class Image
{

    private String m_fileName;
    private int    m_width;
    private int    m_height;
    private float  m_speed;
    private int[]  m_pixels;

    public Image(String fileName, float speed)
    {
        m_fileName = fileName;
        m_speed = speed;

        try
        {
            String path = ConfigUtils.g_res_root + m_fileName;
            ResourceUtils.g_logger.printMsg("Loading a new spritesheet: " + path);
            BufferedImage image = ImageIO.read(new FileInputStream(path));
            m_width = image.getWidth();
            m_height = image.getHeight();
            m_pixels = new int[m_width * m_height];
            image.getRGB(0, 0, m_width, m_height, m_pixels, 0, m_width);
        } catch (IOException e)
        {
            ResourceUtils.g_logger.printErr(e.toString());
            System.exit(1);
        }
    }

    public String getFileName()
    {
        return m_fileName;
    }

    public int getWidth()
    {
        return m_width;
    }

    public int getHeight()
    {
        return m_height;
    }
    
    public float getSpeed()
    {
        return m_speed;
    }

    public int getPixel(int i)
    {
        return m_pixels[i];
    }

}
