package entity;

import map.World;

public class Scheduler
{
	private Entity cursor, focus;

	public Scheduler(Entity cursor)
	{
		this.cursor = cursor.after = cursor.before = focus = cursor;
	}

	public void add(Entity end)
	{
		end.before = cursor.before;
		end.after = cursor;
		cursor.before.after = end;
		cursor.before = end;
	}

	public void update(World world)
	{
		while (!focus.update(world))
		{
			focus.ap = focus.apMax;
			focus = focus.after;
		}
	}
}
