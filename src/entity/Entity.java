package entity;

import map.Coord;
import map.World;
import java.util.LinkedList;

public class Entity extends LinkedList<Action>
{
	public int x, y, ap, apMax;
	public Entity before, after; // refs for circular doubly linked list scheduler
	
	public Entity(Coord c, int apMax)
	{
		super();

		x = c.x;
		y = c.y;
		this.apMax = ap = apMax;
	}

	public boolean update(World world)
	{
		while (ap > 0 && !isEmpty())
			remove().update(this, world);

		return ap > 0;
	}

	public void enqueue(Action action)
	{
		addLast(action);
	}
}
