package agh.cs.lab2;

import org.junit.jupiter.api.Test;

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
}