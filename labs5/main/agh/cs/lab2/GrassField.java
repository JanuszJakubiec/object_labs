package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.Optional;

public class GrassField extends AbstractWorldMap {
  private final List<Grass> grassList = new ArrayList<>();

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
    if (super.isOccupied(position))
      return true;
    for(Grass grass : grassList)
    {
      if(position.equals(grass.getPosition()))
        return true;
    }
    return false;
  }

  public Optional<Object> objectAt(Vector2d position)
  {
    Optional<Object> object = super.objectAt(position);
    if(!object.isEmpty())
      return object;
    for(Grass grass : grassList)
    {
      if(grass.getPosition().equals(position))
        return Optional.of(grass);
    }
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
      if (!object.isEmpty() && (object.get() instanceof Grass))
      {
        i--;
        counter++;
      } else
      {
        grassList.add(new Grass(new Vector2d(x, y)));
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
          grassList.add(new Grass(new Vector2d(i,j)));
    }
  }
}
