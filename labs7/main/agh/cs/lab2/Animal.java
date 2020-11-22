package agh.cs.lab2;

import java.util.LinkedList;
import java.util.Objects;

public class Animal implements IPositionChangePublisher{
  private MapDirection orientation;
  private IWorldMap map;
  private Vector2d position;
  LinkedList<IPositionChangeObserver> subscribers = new LinkedList<IPositionChangeObserver>();

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

  public Vector2d getPosition() {
    return position;
  }

  public MapDirection getOrientation() {
    return orientation;
  }

  public void setOrientation(MapDirection orientation) {
    this.orientation = orientation;
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
    if(map.canMoveTo(tmpPosition)) {
      positionChanged(position, tmpPosition);
      position = tmpPosition;
    }
  }

  public void addObserver(IPositionChangeObserver observer)
  {
    subscribers.add(observer);
  }

  public void removeObserver(IPositionChangeObserver observer)
  {
    subscribers.remove(observer);
  }

  private void positionChanged(Vector2d oldPosition, Vector2d newPosition)
  {
    for(IPositionChangeObserver observer : subscribers)
    {
      observer.positionChanged(oldPosition, newPosition);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Animal animal = (Animal) o;
    return Objects.equals(position, animal.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position);
  }

}
