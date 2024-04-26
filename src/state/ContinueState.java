package state;

import static main.Main.stack;

public class ContinueState extends State
{
	public void update(String input)
	{
		int times = switch (input)
		{
			case "1" -> 1;
			case "2" -> 2;
			default -> 0;
		};

		while (times-- > 0)
			stack.pop();
	}

	public void render()
	{
		System.out.println("Level Cleared");
		System.out.println("1 - Continue");
		System.out.println("2 - Exit");
	}
}
