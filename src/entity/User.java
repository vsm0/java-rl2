package entity;

import map.Coord;

public class User extends Entity
{
	public User(Coord c)
	{
		super(c, 2);
	}

	public void move(int dx, int dy)
	{
		enqueue(new MoveAction(x + dx, y + dy));
	}

	public void waitTurn()
	{
		enqueue(new WaitAction());
	}

	public void skipTurn()
	{
		enqueue(new SkipAction());
	}
}
