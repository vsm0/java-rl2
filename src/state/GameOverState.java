package state;

import static main.Main.stack;

public class GameOverState extends State
{
	String msg;

	public GameOverState(String msg)
	{
		this.msg = msg;
	}

	public void update(String input)
	{
		if (input.equals("1"))
			stack.pop();
	}

	public void render()
	{
		System.out.printf("Game Over - You %s\n", msg);
		System.out.println("1 - Back to main menu");
	}
}
