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
		for (Node n : path)
			System.out.printf("(%d; %d)\n", n.x, n.y);

		boolean canMove = path.size() > 1;

		if (canMove)
		{
			Node next = path.remove(1);
			entity.enqueue(new MobMoveAction(entity, next, target));
		}
		else
			entity.ap--;

		// if found a path: stop follow, and move
		// else, keep following
		// altho, this case is less that likely
		return canMove;
	}
}
