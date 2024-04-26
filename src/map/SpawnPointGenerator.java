package map;

import java.util.ArrayList;

public class SpawnPointGenerator
{
	public static ArrayList<Coord> generate(int[][] map, int index)
	{
		ArrayList<Coord> entities = new ArrayList<>();

		final int NONE = Tile.NONE.index;
		final int FLOOR = Tile.FLOOR.index;
		final int WIDTH = map[0].length;
		final int HEIGHT = map.length;

		for (int y = 0; y < HEIGHT; y++)
			for (int x = 0; x < WIDTH; x++)
			{
				int i = map[y][x];

				if (i == NONE) // fast skip empty regions
				{
					x += Rooms.SIZE - 1;
					continue;
				}

				if (i == index)
				{
					entities.add(new Coord(x, y));
					map[y][x] = FLOOR;
				}
			}

		return entities;
	}
}
