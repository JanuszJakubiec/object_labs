package agh.cs.lab2;

import java.util.*;
import java.util.Optional;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
  protected Map<Vector2d,Animal> animals = new LinkedHashMap<>();

  public abstract boolean canMoveTo(Vector2d position);

  public boolean place(Animal animal) {
    if (canMoveTo(animal.getPosition())) {
      animals.put(animal.getPosition(), animal);
      animal.addObserver(this);
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
      values[i%size].move(direction);
      i = i + 1;
    }
  }

  public Optional<Object> objectAt(Vector2d position) {
    return Optional.ofNullable(animals.get(position));
  }

  public boolean isOccupied(Vector2d position) {
    return animals.containsKey(position);
  }


  public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
  {
    if(!oldPosition.equals(newPosition)) {
      Animal animal = animals.get(oldPosition);
      animals.remove(oldPosition);
      animals.put(newPosition, animal);
    }
  }
}
