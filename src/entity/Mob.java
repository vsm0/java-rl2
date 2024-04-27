package entity;

import map.Coord;
import map.World;

public class Mob extends Entity
{
	private Entity target;

	public Mob(Coord c, Entity target)
	{
		super(c, 1);

		this.target = target;
	}

	public boolean update(World world)
	{
		if (isEmpty())
		{
			enqueue(new SearchAction(target));
			enqueue(new FollowAction(target));
		}

		return super.update(world);
	}
}
