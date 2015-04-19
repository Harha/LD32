package to.us.harha.ld32.core.util.math;

public class Vec2f
{

	public float x;
	public float y;

	public Vec2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vec2f()
	{
		x = 0;
		y = 0;
	}

	public Vec2f add(Vec2f v)
	{
		return new Vec2f(x + v.x, y + v.y);
	}

	public Vec2f add(float i)
	{
		return new Vec2f(x + i, y + i);
	}

	public Vec2f sub(Vec2f v)
	{
		return new Vec2f(x - v.x, y - v.y);
	}

	public Vec2f sub(float i)
	{
		return new Vec2f(x - i, y - i);
	}

	public Vec2f scale(Vec2f v)
	{
		return new Vec2f(x * v.x, y * v.y);
	}

	public Vec2f scale(float i)
	{
		return new Vec2f(x * i, y * i);
	}

}
