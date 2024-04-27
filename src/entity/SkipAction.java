package entity;

import map.World;

public class SkipAction implements Action
{
	public void update(Entity entity, World world)
	{
		entity.ap = 0;
	}
}
