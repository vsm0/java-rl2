package entity;

import map.World;

public class WaitAction extends Action
{
	public boolean update(Entity entity, World world)
	{
		entity.ap--;
		return true;
	}
}
