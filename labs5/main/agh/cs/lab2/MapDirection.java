package agh.cs.lab2;
import java.util.concurrent.ExecutionException;

enum MapDirection
{
  NORTH,
  SOUTH,
  WEST,
  EAST;

  public String toString()
  {
    switch(this)
    {
      case NORTH: return "Północ";
      case SOUTH: return "Południe";
      case WEST: return "Zachód";
      case EAST: return "Wschód";
    }
    throw new IllegalArgumentException("Error, invalid data");
  }

  public MapDirection next()
  {
    switch(this)
    {
      case EAST: return SOUTH;
      case SOUTH: return WEST;
      case WEST: return NORTH;
      case NORTH: return EAST;
    }
    throw new IllegalArgumentException("Error, invalid data");
  }

  public MapDirection previous()
  {
    switch(this)
    {
      case EAST: return NORTH;
      case SOUTH: return EAST;
      case WEST: return SOUTH;
      case NORTH: return WEST;
    }
    throw new IllegalArgumentException("Error, invalid data");
  }

  public Vector2d toUnitVector()
  {
    switch(this)
    {
      case EAST: return new Vector2d(1,0);
      case SOUTH: return new Vector2d(0,-1);
      case WEST: return new Vector2d(-1,0);
      case NORTH: return new Vector2d(0,1);
    }
    throw new IllegalArgumentException("Error, invalid data");
  }
}