package map;

import java.util.HashMap;
import java.util.Stack;

public class InfiniteGrid extends HashMap<Coord, Coord>
{
	public int width, height;
	public Coord offset;
	private Stack<Coord> minX, minY, maxX, maxY;

	public InfiniteGrid()
	{
		super();

		width = height = 0;
		offset = new Coord(0, 0);

		minX = new Stack<Coord>();
		minY = new Stack<Coord>();
		maxX = new Stack<Coord>();
		maxY = new Stack<Coord>();

		add(new Coord(0, 0));
	}

	public Coord get(int x, int y)
	{
		return get(new Coord(x, y));
	}

	public boolean add(Coord c)
	{
		if (containsKey(c))
			return false;

		if (minX.isEmpty() || minX.peek().x >= c.x)
			minX.push(c);

		if (minY.isEmpty() || minY.peek().y >= c.y)
			minY.push(c);

		if (maxX.isEmpty() || maxX.peek().x <= c.x)
			maxX.push(c);

		if (maxY.isEmpty() || maxY.peek().y <= c.y)
			maxY.push(c);

		rebase();

		put(c, c);

		return true;
	}

	public boolean remove(Coord c)
	{
		if (super.remove(c) == null)
			return false;

		if (c == minX.peek())
			minX.pop();

		if (c == minY.peek())
			minY.pop();

		if (c == maxX.peek())
			maxX.pop();

		if (c == maxY.peek())
			maxY.pop();

		rebase();

		return true;
	}

	private void rebase()
	{
		offset.x = Math.abs(minX.peek().x);
		offset.y = Math.abs(minY.peek().y);
		width = maxX.peek().x - minX.peek().x + 1;
		height = maxY.peek().y - minY.peek().y + 1;
	}
}
