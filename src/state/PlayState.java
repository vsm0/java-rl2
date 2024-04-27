package state;

import entity.Action;
import entity.Entity;
import entity.Mob;
import entity.Scheduler;
import entity.User;
import map.Coord;
import map.RoomGenerator;
import map.SpawnPointGenerator;
import map.Tile;
import map.World;
import java.util.ArrayList;
import java.util.Random;
import static main.Main.stack;
import static map.World.States;

public class PlayState extends State
{
	private static Random rng = new Random();
	private int level, width, height;
	private final int LEVEL_MAX;
	private Scheduler scheduler;
	private World world;
	private User user;

	public PlayState()
	{
		width = 30;
		height = 20;
		level = 1;
		LEVEL_MAX = 8;
		loadLevel();
	}

	private void loadLevel()
	{
		int numRooms = (level + 1) + (level + 1) * (level + 1);
		
		int[][] room = RoomGenerator.generate(numRooms);

		ArrayList<Coord> userSpawns = SpawnPointGenerator.generate(room, Tile.USER.index);
		ArrayList<Coord> itemSpawns = SpawnPointGenerator.generate(room, Tile.POTION.index);
		ArrayList<Coord> mobSpawns = SpawnPointGenerator.generate(room, Tile.MOB.index);

		// room is mutated during spawn generation,
		// so create world here
		world = new World(room);

		Coord start = userSpawns.remove(rng.nextInt(userSpawns.size()));
		world.set(start.x, start.y, Tile.USER);
		user = new User(start);
		scheduler = new Scheduler(user);

		int keysPending = world.keysMax;
		while (keysPending-- > 0)
		{
			Coord c = itemSpawns.remove(rng.nextInt(itemSpawns.size()));
			world.set(c.x, c.y, Tile.KEY);
		}

		int potions = itemSpawns.size();
		int potionsPending = potions > 0 ? 1 + rng.nextInt(potions) : 0;
		while (potionsPending-- > 0)
		{
			Coord c = itemSpawns.remove(rng.nextInt(itemSpawns.size()));
			world.set(c.x, c.y, Tile.POTION);
		}

		int mobs = mobSpawns.size();
		int mobsPending = Math.min(mobs, level + rng.nextInt(mobs));
		while (mobsPending-- > 0)
		{
			Coord c = mobSpawns.remove(rng.nextInt(mobSpawns.size()));
			world.set(c.x, c.y, Tile.MOB);
			Mob mob = new Mob(c, user);
			scheduler.add(mob);
		}
	}

	public void update(String input)
	{
		switch (input.toLowerCase())
		{
			case "w" -> user.move(0, -1);
			case "s" -> user.move(0, 1);
			case "a" -> user.move(-1, 0);
			case "d" -> user.move(1, 0);
			case "wait" -> user.waitTurn();
			case "skip", " " -> user.skipTurn();
			case "exit", "quit" -> stack.pop();
			case "help", "h", "?" -> stack.push(new HelpState());

			// chet cods
			case "_win" -> world.state = States.WIN;
			case "_lose" -> world.state = States.LOSE;
			case "_ap" -> user.ap++;
			case "_key" -> {
				world.keys++;
				world.keysFound++;
			}
		}

		if (world.state == States.RUNNING)
		{
			scheduler.update(world);
		}

		if (world.state == States.WIN)
		{
			if (level < LEVEL_MAX)
			{
				level++;	
				loadLevel();
				stack.push(new ContinueState());
			}
			else
			{
				stack.pop();
				stack.push(new GameOverState("win"));
			}
		}
		else if (world.state == States.LOSE)
		{
			stack.pop();
			stack.push(new GameOverState("lose"));
		}
	}

	public void render()
	{
		String buf = "=";
		while (buf.length() < width)
			buf += '=';

		System.out.printf(
			"%s\nLVL %d/%d | AP %d/%d | KEYS %d/%d\n%s\n",
			buf,
			level,
			LEVEL_MAX,
			user.ap,
			user.apMax,
			world.keysFound,
			world.keysMax,
			buf
		);

		int ox = Math.max(0, Math.min(user.x - width / 2, world.width - width));
		int oy = Math.max(0, Math.min(user.y - height / 2, world.height - height));

		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
				System.out.print(world.get(x + ox, y + oy).glyph);
			System.out.print('\n');
		}
	}
}
