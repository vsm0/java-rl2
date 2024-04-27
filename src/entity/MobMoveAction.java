package entity;

import map.Node;
import map.World;

public class MobMoveAction extends MoveAction
{
	private boolean incomplete;

	public MobMoveAction(Entity mob, Node next, Entity target)
	{
		super(next.x - mob.x, next.y - mob.y);

		incomplete = next.x != target.x || next.y != target.y;

		if (incomplete)
			mob.enqueue(new FollowAction(target));
	}

	public boolean update(Entity entity, World world)
	{
		super.update(entity, world);

		// if follow enqueued, remove this action
		// follow enqueued = target not found
		return incomplete;
	}
}
