package agh.cs.lab2;

import java.util.Optional;;
import java.util.*;


class RectangularMap implements IWorldMap {
  private final Vector2d left_bottom_corner;
  private final Vector2d right_top_corner;
  private List<Animal> animalList = new ArrayList<Animal>();

  public RectangularMap(int width, int height)
  {
    right_top_corner = new Vector2d(width, height);
    left_bottom_corner = new Vector2d(0,0);
  }

  public boolean canMoveTo(Vector2d position)
  {
    return position.precedes(right_top_corner) && position.follows(left_bottom_corner) && !isOccupied(position);
  }

  public boolean place(Animal animal)
  {
    if (canMoveTo(animal.getPosition()))
    {
      animalList.add(animal);
      return true;
    }
    return false;
  }

  public void run(List<MoveDirection> directions)
  {
    int i = 0;
    int size = animalList.size();
    for(MoveDirection direction : directions)
    {
      animalList.get(i%size).move(direction);
      i = i+1;
    }
  }

  public boolean isOccupied(Vector2d position)
  {
    for(Animal animal : animalList)
    {
      if(position.equals(animal.getPosition()))
        return true;
    }
    return false;
  }

  @Override
  public String toString() {
    MapVisualiser vis = new MapVisualiser(this);
    return vis.draw(left_bottom_corner, right_top_corner );
  }

  public Optional<Object> objectAt(Vector2d position)
  {
    for(Animal animal : animalList)
    {
      if(animal.getPosition().equals(position))
        return Optional.of(animal);
    }
    return Optional.empty();
  }

}
