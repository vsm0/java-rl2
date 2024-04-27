package entity;

import map.Astar;
import map.Node;
import map.World;
import java.util.ArrayList;
import static map.World.States;

public class FollowAction implements Action
{
	private Entity target;

	public FollowAction(Entity target)
	{
		this.target = target;
	}

	public void update(Entity entity, World world)
	{
		ArrayList<Node> path = Astar.findPath(entity, target, world);

		if (path.size() > 1)
		{
			Node next = path.remove(1);
			entity.enqueue(new MoveAction(next.x, next.y));
			if (next.x != target.x || next.y != target.y)
				entity.enqueue(this);
		}
		else
		{
			// soon implement counter to switch to search mode
			entity.enqueue(this);
			entity.ap--;
		}
	}
}
