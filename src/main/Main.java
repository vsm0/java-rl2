package main;

import state.StartState;
import state.StateStack;
import java.util.Scanner;

public class Main
{
	public static StateStack stack = new StateStack();;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String... args)
	{
		stack.push(new StartState());

		while (!stack.isEmpty())
		{
//			cls();
			stack.render();
			System.out.print("> ");
			stack.update(scanner.nextLine());
		}
	}

	public static void cls()
	{
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
