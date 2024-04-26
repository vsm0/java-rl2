package entity;

import map.World;

public class SkipAction extends Action
{
	public boolean update(Entity entity, World world)
	{
		entity.ap = 0;
		return true;
	}
}
