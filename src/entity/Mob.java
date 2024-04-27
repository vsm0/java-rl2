package entity;

import map.Coord;
import map.World;

public class Mob extends Entity
{
	public Mob(Coord c, Entity target)
	{
		super(c, 1);

		enqueue(new SearchAction(target));
	}
}
