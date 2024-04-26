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
		push(new MoveAction(dx, dy));
	}

	public void waitTurn()
	{
		push(new WaitAction());
	}

	public void skipTurn()
	{
		push(new SkipAction());
	}
}
