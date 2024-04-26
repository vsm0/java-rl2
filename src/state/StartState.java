package state;

import static main.Main.stack;

public class StartState extends State
{
	public void update(String input)
	{
		State next = switch (input)
		{
			case "1" -> new PlayState();
			case "2" -> new HelpState();
			case "3" -> null;
			default -> this;
		};

		if (next == null)
			stack.pop();
		else
			stack.push(next);
	}

	public void render()
	{
		System.out.println("=============================================");
		System.out.println("The Dungeon");
		System.out.println("---------------------------------------------");
		System.out.println("a dungeon... a prison... a place to escape...");
		System.out.println("=============================================");
		System.out.println("1 - Start");
		System.out.println("2 - Help");
		System.out.println("3 - Exit");
	}
}
