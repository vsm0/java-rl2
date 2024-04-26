package map;

import java.util.Objects;

public class Coord
{
	public int x, y;
	private int hash;

	public Coord(int x, int y)
	{
		this.x = x;
		this.y = y;
		hash = Objects.hash(x, y);
	}

	public int hashCode()
	{
		return hash;
	}

	public boolean equals(Object other)
	{
		if (this == other)
			return true;

		if (other == null || getClass() != other.getClass())
			return false;

		Coord c = (Coord) other;

		return x == c.x && y == c.y;
	}
}
