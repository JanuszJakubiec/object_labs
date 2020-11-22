package agh.cs.lab2;

import java.util.LinkedList;

public class MapBoundary implements IPositionChangeObserver {
  private Vector2d rightTopCorner;
  private Vector2d leftBottomCorner;
  private final Vector2d rightTopCornerInitial;
  private final Vector2d leftBottomCornerInitial = new Vector2d(0,0);
  private final LinkedList<Vector2d> sortedX = new LinkedList<Vector2d>();
  private final LinkedList<Vector2d> sortedY = new LinkedList<Vector2d>();

  public MapBoundary(int n)
  {
    int grassLength = (int)Math.sqrt(n*10);
    rightTopCornerInitial = new Vector2d(grassLength, grassLength);
    rightTopCorner = rightTopCornerInitial;
    leftBottomCorner = leftBottomCornerInitial;
  }

  public void addAnimal(Animal animal)
  {
    animal.addObserver(this);
    insertProperlyX(animal.getPosition());
    insertProperlyY(animal.getPosition());
  }

  public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
  {
    Vector2d left = sortedX.getFirst();
    Vector2d right = sortedX.getLast();
    Vector2d down = sortedX.getFirst();
    Vector2d up = sortedX.getLast();
    sortedX.remove(oldPosition);
    sortedY.remove(oldPosition);
    insertProperlyX(newPosition);
    insertProperlyY(newPosition);
    if(!sortedX.getFirst().equals(left) ||
       !sortedX.getLast().equals(right) ||
       !sortedY.getFirst().equals(down) ||
       !sortedY.getLast().equals(up))
    {
      rightTopCorner = new Vector2d(max(sortedX.getLast().x, rightTopCornerInitial.x), max(sortedY.getLast().y, rightTopCornerInitial.y));
      leftBottomCorner = new Vector2d(min(sortedX.getFirst().x, leftBottomCornerInitial.x), min(sortedY.getFirst().y, leftBottomCornerInitial.y));
    }
  }

  private int max(int x, int y) {
    if (x > y)
      return x;
    return y;
  }

  private int min(int x, int y) {
    if (x > y)
      return y;
    return x;
  }

  public Vector2d getRightTopCorner() {
    return rightTopCorner;
  }

  public Vector2d getLeftBottomCorner() {
    return leftBottomCorner;
  }

  private void insertProperlyX(Vector2d position)
  {
    int i = 0;
    for(Vector2d pos:sortedX) {
      if(pos.x > position.x)
        break;
      if(pos.x == position.x)
      {
        if(pos.y > position.y)
          break;
      }
      i++;
    }
    sortedX.add(i,position);
  }

  private void insertProperlyY(Vector2d position)
  {
    int i = 0;
    for(Vector2d pos:sortedY) {
      if(pos.y > position.y)
        break;
      if(pos.y == position.y)
      {
        if(pos.x > position.x)
          break;
      }
      i++;
    }
    sortedY.add(i,position);
  }
}
