package entity;

import map.World;

public class WaitAction implements Action
{
	public void update(Entity entity, World world)
	{
		entity.ap--;
	}
}
