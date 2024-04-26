package entity;

import map.World;
import map.Tile;

public class SearchAction extends Action
{
	public boolean update(Entity entity, World world)
	{
		int x = entity.x;
		int y = entity.y;
		Entity target = (Entity) world.user;
		int tx = target.x;
		int ty = target.y;

		int dx = Math.abs(tx - x);
		int dy = Math.abs(ty - y);
		int sx = x < tx ? 1 : -1;
		int sy = y < ty ? 1 : -1;
		int err = dx - dy;

		boolean found = true;

		while (x != tx || y != ty)
		{
			if (world.get(x, y) == Tile.WALL)
			{
				found = false;
				break;
			}
			
			int e2 = 2 * err;

			if (e2 > -dy)
			{
				err -= dy;
				x += sx;
			}

			if (e2 < dx)
			{
				err += dy;
				y += sy;
			}
		}
		
		return found;
	}
}
