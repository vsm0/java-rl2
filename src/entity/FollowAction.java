package entity;

import map.Astar;
import map.Node;
import map.World;
import java.util.ArrayList;
import static map.World.States;

public class FollowAction extends Action
{
	private Entity target;

	public FollowAction(Entity target)
	{
		this.target = target;
	}

	public boolean update(Entity entity, World world)
	{
		ArrayList<Node> path = Astar.findPath(entity, target, world);
		boolean caught = false;

		if (path.size() > 1)
		{
			Node next = path.get(1);
			entity.x = next.x;
			entity.y = next.y;

			if (entity.x != target.x || entity.y == target.y)
			{
				world.state = States.LOSE;
				caught = true;
			}
		}

		return caught;
	}
}
