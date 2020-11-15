package agh.cs.lab2;

import java.util.*;
import java.lang.Math;

public class GrassField extends AbstractWorldMap {
  Map<Vector2d,Grass> grassList = new LinkedHashMap<>();


  public GrassField(int n)
  {
    generateGrass(n);
    int grassLength = (int)Math.sqrt(n*10);
    rightTopCorner = new Vector2d(grassLength, grassLength);
    leftBottomCorner = new Vector2d(0,0);
  }

  public boolean canMoveTo(Vector2d position)
  {
    if (!position.follows(leftBottomCorner))
      leftBottomCorner = position;
    if (!position.precedes(rightTopCorner))
      rightTopCorner = position;
    Optional<Object> object = objectAt(position);
    return object.isEmpty() || !(object.get() instanceof Animal);
  }

  public boolean isOccupied(Vector2d position)
  {
    return super.isOccupied(position) || grassList.containsKey(position);
  }

  public Optional<Object> objectAt(Vector2d position)
  {
    Optional<Object> object = super.objectAt(position);
    if(object.isPresent())
      return object;
    if (grassList.get(position) != null)
      return Optional.of(grassList.get(position));
    return Optional.empty();
  }

  private void generateGrass(int n)
  {
    int size = (int) (Math.sqrt(10 * n));
    int counter = 0; //counter enabling safe exit from function when rand goes wrong
    for(int i =0;i<n;i++) {
      int x = randPosition(n, size);
      int y = randPosition(n, size);
      Optional<Object> object = objectAt(new Vector2d(x,y));
      if (!object.isEmpty())
      {
        i--;
        counter++;
      } else
      {
        grassList.put(new Vector2d(x, y),new Grass(new Vector2d(x, y)));
        counter = 0;
      }
      if(counter==10)
        placeGrassOnFirstAvailablePosition(size);
    }
  }

  private int randPosition(int n, int sqrt)
  {
    int max = sqrt;
    int min = 0;
    int range = max - min + 1;
    return (int)(Math.random() * range) + min;
  }

  private void placeGrassOnFirstAvailablePosition(int n)
  {
    for(int i=0; i<n;i++)
    {
      for(int j=0;i<n;j++)
        if(!(this.objectAt(new Vector2d(i,j)).get() instanceof Grass))
          grassList.put(new Vector2d(i, j),new Grass(new Vector2d(i, j)));
    }
  }
}
