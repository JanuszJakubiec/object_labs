package agh.cs.lab2;

public class Animal {
  private MapDirection orientation;
  private Vector2d position;
  private IWorldMap map;

  public Animal(IWorldMap map)
  {
    this.map = map;
    orientation = MapDirection.NORTH;
    position = new Vector2d(2,2);
  }

  public Animal( IWorldMap map, Vector2d initialPosition)
  {
    this.map = map;
    orientation = MapDirection.NORTH;
    position = initialPosition;
  }

  public MapDirection getOrientation() {
    return orientation;
  }

  public void setOrientation(MapDirection orientation) {
    this.orientation = orientation;
  }

  public Vector2d getPosition() {
    return position;
  }

  public void move(MoveDirection direction)
  {
    switch(direction)
    {
      case BACKWARD,FORWARD:
        moveForwardBackward(direction);
        break;
      case RIGHT:
        orientation = orientation.next();
        break;
      case LEFT:
        orientation = orientation.previous();
        break;
    }
  }

  public String toString()
  {
    return switch(orientation)
    {
      case NORTH -> "^";
      case SOUTH -> "v";
      case EAST -> ">";
      case WEST -> "<";
    };
  }

  private void moveForwardBackward(MoveDirection direction)
  {
    Vector2d v = orientation.toUnitVector();
    if(direction == MoveDirection.BACKWARD)
      v = v.opposite();
    Vector2d tmpPosition = position.add(v);
    if(map.canMoveTo(tmpPosition))
      position = tmpPosition;
  }
}
