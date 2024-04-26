package entity;

import map.Coord;
import map.World;

public class Mob extends Entity
{
	public Mob(Coord c)
	{
		super(c, 1);
	}

	public boolean update(World world)
	{
		// push search
		// push follow
		ap = 0;
		return super.update(world);
	}
}
