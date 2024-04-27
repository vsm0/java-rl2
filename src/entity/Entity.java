package entity;

import map.Coord;
import map.World;
import java.util.LinkedList;

public class Entity extends LinkedList<Action>
{
	public int x, y, ap, apMax;
	
	public Entity(Coord c, int apMax)
	{
		super();

		x = c.x;
		y = c.y;
		this.apMax = ap = apMax;
	}

	public boolean update(World world)
	{
		if (ap <= 0)
			return false;

		while (!isEmpty())
			if (peek().update(this, world))
				remove(0);
			else
				break;

		return ap > 0;
	}

	public void enqueue(Action action)
	{
		if (ap > 0)
			addLast(action);
	}
}
