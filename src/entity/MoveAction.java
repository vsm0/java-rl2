package entity;

import map.Tile;
import map.World;
import static map.World.States;

public class MoveAction implements Action
{
	private int x, y;

	public MoveAction(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void update(Entity entity, World world)
	{
		int apOld = entity.ap;
		entity.ap--;

		switch (world.get(x, y))
		{
			case FLOOR:
				move(entity, world);
				break;
			case STAIR:
				move(entity, world);
				world.state = States.WIN;
				break;
			case LOCK:
				if (world.keys > 0)
				{
					world.keys--;
					world.set(x, y, Tile.FLOOR);
				}
				break;
			case KEY:
				world.keys++;
				world.keysFound++;
				move(entity, world);
				break;
			case POTION:
				move(entity, world);
				entity.ap = apOld + 5;
				break;
			case MOB:
				world.state = States.LOSE;
				break;
			case USER:
				move(entity, world);
				world.state = States.LOSE;
				break;
			default:
				entity.ap = apOld;
		}
	}

	private void move(Entity e, World world)
	{
		world.set(x, y, world.get(e.x, e.y));
		world.set(e.x, e.y, Tile.FLOOR);
		e.x = x;
		e.y = y;
	}
}
