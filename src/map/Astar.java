package map;

import entity.Entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Astar
{
	private static final Coord[] DIRS = {
		new Coord(0, -1),
		new Coord(0, 1),
		new Coord(-1, 0),
		new Coord(1, 0)
	};

	public static ArrayList<Node> findPath(Entity start, Entity end, World world)
	{
		Node first = new Node(start.x, start.y);
		first.g = 0;
		first.h = heuristic(first, end);
		first.f = first.g + first.h;

		if (first.h == 1) // had to force it cuz my bren cant bren rn
		{
			ArrayList<Node> path = new ArrayList<>();
			path.add(first);
			path.add(new Node(end.x, end.y));
			return path;
		}

		PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));
		open.add(first);

		HashSet<Node> closed = new HashSet<>();
		int timeout = first.h * first.h;

		while (timeout-- > 0 && !open.isEmpty())
		{
			Node focus = open.poll();

			if (focus.x == end.x && focus.y == end.y)
				return buildPath(focus);

			closed.add(focus);

			for (Coord c : DIRS)
			{
				int x = focus.x + c.x;
				int y = focus.y + c.y;

				Tile t = world.get(x, y);
				// x, y assumed within bounds
				if (t != Tile.FLOOR && t != Tile.USER)
					continue;

				Node n = new Node(x, y);

				if (closed.contains(n))
					continue;

				int g = focus.g + 1;
				if (g < n.g || !open.contains(n))
				{
					n.g = g;
					n.h = heuristic(n, end);
					n.f = n.g + n.h;
					n.parent = focus;
					open.add(n);
				}
			}
		}

		return new ArrayList<>();
	}

	private static int heuristic(Node a, Entity b)
	{
		return Math.abs(b.x - a.x) + Math.abs(b.y - a.y);
	}

	private static ArrayList<Node> buildPath(Node focus)
	{
		ArrayList<Node> path = new ArrayList<>();

		while (focus != null)
		{
			path.add(focus);
			focus = focus.parent;
		}

		Collections.reverse(path);

		return path;
	}
}
