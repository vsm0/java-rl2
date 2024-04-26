/*
 * Finalized legend:
 * 0 -> . -> floor
 * 1 -> # -> wall
 * 2 -> @ -> user
 * 3 -> & -> mob
 * 4 -> ! -> potion
 * 5 -> > -> stair
 * 6 -> ? -> lock
 * 7 -> $ -> key
 * 8 ->   -> none (8 or default)
 * They are ordered in logical explanation order (realized during creation of help screen)
 */

package map;

public enum Tile
{
	FLOOR('.'),
	WALL('#'),
	USER('@'),
	MOB('&'),
	POTION('!'),
	STAIR('>'),
	LOCK('?'),
	KEY('$'),
	NONE(' ');

	public char glyph;
	public int index;

	private Tile(char glyph)
	{
		this.glyph = glyph;
		index = Parser.index++;
		Parser.tiles[index] = this;
	}

	public static Tile parse(int index)
	{
		return Parser.tiles[Math.min(index, NONE.index)];
	}
}

class Parser
{
	public static int index = 0;
	public static Tile[] tiles = new Tile[9];
}
