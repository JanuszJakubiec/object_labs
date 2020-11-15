package agh.cs.lab2;

import java.util.ArrayList;
import java.util.*;
import java.util.Optional;

abstract class AbstractWorldMap implements IWorldMap {
  protected Vector2d rightTopCorner;
  protected Vector2d leftBottomCorner;
  protected Map<Vector2d,Animal> animals = new LinkedHashMap<>();

  public abstract boolean canMoveTo(Vector2d position);

  public boolean place(Animal animal) {
    if (canMoveTo(animal.getPosition())) {
      animals.put(animal.getPosition(), animal);
      return true;
    }
    throw new IllegalArgumentException("position " + animal.getPosition().toString() + " is invalid");
  }

  public void run(List<MoveDirection> directions) {
    int i = 0;
    Collection <Animal> coll = animals.values();
    Animal[] values =  coll.toArray(new Animal[0]);
    int size = values.length;
    for (MoveDirection direction : directions) {
      Vector2d position = values[i%size].getPosition();
      values[i%size].move(direction);
      animals.remove(position);
      animals.put(values[i%size].getPosition(),values[i%size]);
      i = i + 1;
    }
  }

  public Optional<Object> objectAt(Vector2d position) {
    if (animals.get(position) != null)
      return Optional.of(animals.get(position));
    return Optional.empty();
  }

  public boolean isOccupied(Vector2d position) {
    return animals.containsKey(position);
  }

  @Override
  public String toString() {
    MapVisualiser vis = new MapVisualiser(this);
    return vis.draw(leftBottomCorner, rightTopCorner);
  }
}
