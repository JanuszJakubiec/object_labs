package agh.cs.lab2;

public class Animal {
  private MapDirection orientation;
  private Vector2d position;
  private static final Vector2d upperRightLimit = new Vector2d(4,4);
  private static final Vector2d lowerLeftLimit = new Vector2d(0,0);

  public Animal()
  {
    orientation = MapDirection.NORTH;
    position = new Vector2d(2,2);
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

  public void setPosition(Vector2d position) {
    if(!positionIsIncorrect(position))
      this.position = position;
  }

  public void move(MoveDirection direction)
  {
    switch(direction)
    {
      case BACKWARD:
        position = position.substract(orientation.toUnitVector());
        if(positionIsIncorrect(position))
          position = position.add(orientation.toUnitVector());
        break;
      case FORWARD:
        position = position.add(orientation.toUnitVector());
        if(positionIsIncorrect(position))
          position = position.substract(orientation.toUnitVector());
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
    return "orientation: " + orientation.toString() +
             " position: " + position.toString();
  }

  private boolean positionIsIncorrect(Vector2d position)
  {
    if(position.precedes(upperRightLimit) && position.follows(lowerLeftLimit))
      return false;
    return true;
  }
}
