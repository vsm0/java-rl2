package map;

public class World
{
	public enum States
	{
		RUNNING,
		WIN,
		LOSE
	}

	public States state;
	public int keys, keysFound, keysMax, width, height;
	private Tile[][] map;

	public World(int[][] room)
	{
		state = States.RUNNING;
		keys = keysFound = 0;
		keysMax = 3;
		width = room[0].length;
		height = room.length;
		map = new Tile[height][width];

		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				map[y][x] = Tile.parse(room[y][x]);
	}

	public Tile get(int x, int y)
	{
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.NONE;

		return map[y][x];
	}

	public void set(int x, int y, Tile tile)
	{
		if (get(x, y) != null)
			map[y][x] = tile;
	}
}
