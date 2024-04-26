/*
 * This is a custom implementation of a room generation algorithm as described by miziziz for his 1drl
 * 
 * Steps:
 * - add a room to a free list
 * - while number of rooms < max number of rooms, remove a random room from the free list
 * - add it to a bidirectional (infinite) grid <Vector2>
 * - add its neighbors (excluding diagonal) to the free list
 * - repeat from step 2 until done
 * - create an empty room
 * - for each room in grid, fill the room with a random sample
 * - fill it with walls afterwards if has no neighbors
 */

package map;

import java.util.ArrayList;
import java.util.Random;

public class RoomGenerator
{
	private static Random rng = new Random();

	public static int[][] generate(int maxRooms)
	{
		ArrayList<Coord> freeList = new ArrayList<>();
		InfiniteGrid waitList = new InfiniteGrid();
		InfiniteGrid grid = new InfiniteGrid();

		Coord start = new Coord(0, 0);
		waitList.add(start);
		freeList.add(start);

		int numRooms = Math.max(2, maxRooms);

		while (grid.size() < numRooms)
		{
			Coord focus = freeList.remove(rng.nextInt(freeList.size()));
			waitList.remove(focus);
			grid.add(focus);

			int x = focus.x;
			int y = focus.y;

			Coord n = new Coord(x + 0, y - 1);
			Coord s = new Coord(x + 0, y + 1);
			Coord w = new Coord(x - 1, y + 0);
			Coord e = new Coord(x + 1, y + 0);

			if (waitList.add(n))
				freeList.add(n);

			if (waitList.add(s))
				freeList.add(s);

			if (waitList.add(w))
				freeList.add(w);

			if (waitList.add(e))
				freeList.add(e);
		}

		int width = grid.width * Rooms.SIZE;
		int height = grid.height * Rooms.SIZE;
		int[][] room = new int[height][width];
		final int NONE = Tile.NONE.index;

		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				room[y][x] = NONE;

		ArrayList<Coord> coords = new ArrayList<>(grid.values());

		int index = rng.nextInt(coords.size());
		Coord c = coords.remove(index);
		overwrite(c, grid.offset, Rooms.start, room);
		fillWalls(c, grid, room);

		index = rng.nextInt(coords.size());
		c = coords.remove(index);
		overwrite(c, grid.offset, Rooms.end, room);
		fillWalls(c, grid, room);

		while (coords.size() > 0)
		{
			index = rng.nextInt(coords.size());
			c = coords.remove(index);
			overwrite(c, grid.offset, Rooms.any[rng.nextInt(Rooms.any.length)], room);
			fillWalls(c, grid, room);
		}

		return room;
	}

	private static void overwrite(Coord focus, Coord offset, int[][] src, int[][] target)
	{
		int ox = (focus.x + offset.x) * Rooms.SIZE;
		int oy = (focus.y + offset.y) * Rooms.SIZE;
		
		for (int y = 0; y < Rooms.SIZE; y++)
			for (int x = 0; x < Rooms.SIZE; x++)
				target[y + oy][x + ox] = src[y][x];
	}

	private static void fillWalls(Coord focus, InfiniteGrid grid, int[][] target)
	{
		int ox = (focus.x + grid.offset.x) * Rooms.SIZE;
		int oy = (focus.y + grid.offset.y) * Rooms.SIZE;
		final int WALL = Tile.WALL.index;

		if (grid.get(focus.x + 0, focus.y - 1) == null)
			for (int i = 0; i < Rooms.SIZE; i++)
				target[0 + oy][i + ox] = WALL;

		if (grid.get(focus.x + 0, focus.y + 1) == null)
			for (int i = 0; i < Rooms.SIZE; i++)
				target[Rooms.SIZE - 1 + oy][i + ox] = WALL;

		if (grid.get(focus.x - 1, focus.y + 0) == null)
			for (int i = 0; i < Rooms.SIZE; i++)
				target[i + oy][0 + ox] = WALL;

		if (grid.get(focus.x + 1, focus.y + 0) == null)
			for (int i = 0; i < Rooms.SIZE; i++)
				target[i + oy][Rooms.SIZE - 1 + ox] = WALL;
	}
}
