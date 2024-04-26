package state;

import static main.Main.stack;

public class HelpState extends State
{
	private static String[] help = {
		"=============================================",
		"		    Help",
		"=============================================",
		"[Gameplay]",
		"+ Escape the dungeon while avoiding the guards",
		"+ Each floor has one locked exit",
		"+ There are three keys to unlock the exit",
		"---------------------------------------------",
		"[In-Game Commands]",
		"w, a, s, d    : Movement",
		"exit, quit    : Exit the game",
		"wait          : Use 1 Action Point",
		"skip, [space] : Skip your turn",
		"help, h, ?    : This help screen",
		"---------------------------------------------",
		"[Legend]",
		"# -> Wall     : Blocks your path",
		". -> Floor    : Is walkable",
		"@ -> Player   : Your avatar",
		"& -> Guard    : Follows you with intent to kill",
		"! -> Powder   : Increases your Action Points",
		"> -> Stair    : Your exit to the next floor",
		"? -> Lock     : Blocks your exit",
		"$ -> Key      : Unlocks a lock",
		"=============================================",
	};

	public void update(String input)
	{
		if (input.equals("1"))
			stack.pop();
	}

	public void render()
	{
		for (String s : help)
			System.out.println(s);
		System.out.println("1 - Back");
	}
}
