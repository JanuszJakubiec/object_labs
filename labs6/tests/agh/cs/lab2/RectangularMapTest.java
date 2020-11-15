package agh.cs.lab2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

  @Test
  void place() {
    RectangularMap map = new RectangularMap(4,4);
    Vector2d position = new Vector2d(2,2);
    Animal test = new Animal(map, position);
    map.place(test);
    assertEquals(test, map.objectAt(position).get());
    System.out.print(map.toString());
  }

  @Test
  void testIfCanMoveToLegalPosition()
  {
    IWorldMap map = new RectangularMap(4, 5);
    assertTrue(map.canMoveTo(new Vector2d(2,2)));
  }

  @Test
  void testIfCantMoveToIllegalPosition()
  {
    IWorldMap map = new RectangularMap(5, 5);
    assertFalse(map.canMoveTo(new Vector2d(-1,-1)));
  }

  @Test
  void testIfCantMoveIntoAnotherAnimal()
  {
    IWorldMap map = new RectangularMap(6,6);
    map.place(new Animal(map));
    assertFalse(map.canMoveTo(new Vector2d(2,2)));
  }

  @Test
  void testRunFunction()
  {
    IWorldMap map = new RectangularMap(6,7);
    Animal cat = new Animal(map, new Vector2d(2,3));
    map.place(cat);
    Animal dog = new Animal(map);
    map.place(dog);
    List<MoveDirection> directions = new LinkedList<MoveDirection>();
    directions.add(MoveDirection.FORWARD);
    directions.add(MoveDirection.FORWARD);
    directions.add(MoveDirection.FORWARD);
    directions.add(MoveDirection.FORWARD);
    directions.add(MoveDirection.FORWARD);
    directions.add(MoveDirection.FORWARD);
    map.run(directions);
    assertEquals(new Vector2d(2,6), cat.getPosition());
  }

  @Test
  void checkIfOccupiedPlaceIsOccupied()
  {
    IWorldMap map = new RectangularMap(2,2);
    map.place(new Animal(map));
    assertTrue(map.isOccupied(new Vector2d(2,2)));
  }

  @Test
  void checkIfUnoccupiecPlaceIsOccupied()
  {
    IWorldMap map = new RectangularMap(2,3);
    assertFalse(map.isOccupied(new Vector2d(2,3)));
  }

  @Test
  void checkIfObjectAtIsReturned()
  {
    IWorldMap map = new RectangularMap(2,2);
    Animal cat = new Animal(map);
    map.place(cat);
    assertSame(cat, map.objectAt(new Vector2d(2,2)).get());
  }

  @Test
  void checkIfNULLIsReturnedWhenThereIsEmptyField()
  {
    IWorldMap map = new RectangularMap(2,3);
    assertTrue(map.objectAt(new Vector2d(2,2)).isEmpty());
  }
}