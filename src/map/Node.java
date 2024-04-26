package map;

public class Node
{
	public int x, y, g, h, f;
	public Node parent;

	public Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
