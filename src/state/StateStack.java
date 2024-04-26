package state;

import java.util.Stack;

public class StateStack extends Stack<State>
{
	public StateStack()
	{
		super();
	}

	public State push(State state)
	{
		state.create();
		return super.push(state);
	}

	public State pop()
	{
		peek().dispose();
		return super.pop();
	}

	public void swap(State state)
	{
		pop();
		push(state);
	}

	public void update(String input)
	{
		peek().update(input);
	}

	public void render()
	{
		peek().render();
	}
}
