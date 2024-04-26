package entity;

import map.Tile;
import map.World;
import static map.World.States;

public class MoveAction extends Action
{
	private int dx, dy;

	public MoveAction(int dx, int dy)
	{
		this.dx = dx;
		this.dy = dy;
	}

	public boolean update(Entity entity, World world)
	{
		int x = entity.x + dx;
		int y = entity.y + dy;

		Tile t = world.get(x, y);

		switch (t)
		{
			case FLOOR:
				move(entity, x, y, world);
				break;
			case STAIR:
				move(entity, x, y, world);
				world.state = States.WIN;
				break;
			case LOCK:
				if (world.keys > 0)
				{
					world.keys--;
					entity.ap--;
					world.set(x, y, Tile.FLOOR);
				}
				break;
			case KEY:
				world.keys++;
				world.keysFound++;
				move(entity, x, y, world);
				break;
			case POTION:
				int ap = entity.ap;
				move(entity, x, y, world);
				entity.ap = ap + 5;
				break;
			case MOB:
				world.state = States.LOSE;
				break;
		}

		return true;
	}

	private void move(Entity e, int x, int y, World world)
	{
		world.set(x, y, world.get(e.x, e.y));
		world.set(e.x, e.y, Tile.FLOOR);
		e.x = x;
		e.y = y;
		e.ap--;
	}
}
