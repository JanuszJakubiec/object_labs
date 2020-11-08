package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract class AbstractWorldMap implements IWorldMap {
  protected Vector2d rightTopCorner;
  protected Vector2d leftBottomCorner;
  protected List<Animal> animalList = new ArrayList<Animal>();

  public abstract boolean canMoveTo(Vector2d position);

  public boolean place(Animal animal) {
    if (canMoveTo(animal.getPosition())) {
      animalList.add(animal);
      return true;
    }
    return false;
  }

  public void run(List<MoveDirection> directions) {
    int i = 0;
    int size = animalList.size();
    for (MoveDirection direction : directions) {
      animalList.get(i % size).move(direction);
      i = i + 1;
    }
  }

  public Optional<Object> objectAt(Vector2d position) {
    for (Animal animal : animalList) {
      if (animal.getPosition().equals(position))
        return Optional.of(animal);
    }
    return Optional.empty();
  }

  public boolean isOccupied(Vector2d position) {
    for (Animal animal : animalList) {
      if (position.equals(animal.getPosition()))
        return true;
    }
    return false;
  }

  @Override
  public String toString() {
    MapVisualiser vis = new MapVisualiser(this);
    return vis.draw(leftBottomCorner, rightTopCorner);
  }
}
