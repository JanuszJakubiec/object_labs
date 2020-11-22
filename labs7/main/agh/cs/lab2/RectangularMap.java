package agh.cs.lab2;

import java.util.Optional;;
import java.util.*;


class RectangularMap extends AbstractWorldMap {
  private Vector2d rightTopCorner;
  private Vector2d leftBottomCorner;

  public RectangularMap(int width, int height)
  {
    rightTopCorner = new Vector2d(width, height);
    leftBottomCorner = new Vector2d(0,0);
  }

  public boolean canMoveTo(Vector2d position)
  {
    return position.precedes(rightTopCorner) && position.follows(leftBottomCorner) && !isOccupied(position);
  }

  @Override
  public String toString() {
    MapVisualiser vis = new MapVisualiser(this);
    return vis.draw(leftBottomCorner, rightTopCorner);
  }

}
