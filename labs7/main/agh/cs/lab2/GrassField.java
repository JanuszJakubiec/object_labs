package agh.cs.lab2;

import java.util.*;
import java.lang.Math;

public class GrassField extends AbstractWorldMap {
  private Map<Vector2d,Grass> grassList = new LinkedHashMap<>();
  private MapBoundary borders;


  public GrassField(int n)
  {
    generateGrass(n);
    borders = new MapBoundary(n);
  }

  public boolean canMoveTo(Vector2d position)
  {
    Optional<Object> object = objectAt(position);
    return object.isEmpty() || !(object.get() instanceof Animal);
  }

  public boolean isOccupied(Vector2d position)
  {
    return super.isOccupied(position) || grassList.containsKey(position);
  }

  @Override
  public boolean place(Animal animal) {
    boolean condition = super.place(animal);
    if(condition)
      borders.addAnimal(animal);
    return condition;
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
      int x = randPosition(size);
      int y = randPosition(size);
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

  private int randPosition(int max)
  {
    int min = 0;
    int range = max - min + 1;
    return (int)(Math.random() * range) + min;
  }

  private void placeGrassOnFirstAvailablePosition(int n)
  {
    for(int i=0; i<n;i++)
    {
      for(int j=0;j<n;j++)
        if(!(this.objectAt(new Vector2d(i,j)).get() instanceof Grass))
          grassList.put(new Vector2d(i, j),new Grass(new Vector2d(i, j)));
    }
  }

  @Override
  public String toString() {
    MapVisualiser vis = new MapVisualiser(this);
    return vis.draw(borders.getLeftBottomCorner(), borders.getRightTopCorner());
  }
}
