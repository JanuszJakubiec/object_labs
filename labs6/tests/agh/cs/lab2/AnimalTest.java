package agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
  Animal test;
  Vector2d defaultPosition = new Vector2d(2,2);
  MapDirection defaultOrientation = MapDirection.NORTH;

  @Test
  void checkLegalMoveForward() {
    setAnimalToDefault();
    test.move(MoveDirection.FORWARD);
    assertEquals(new Vector2d(2,3), test.getPosition());
  }

  @Test
  void checkIllegalMoveForward() {
      Vector2d position = new Vector2d(0, 4);
      setAnimalWithPosition(position);
      test.move(MoveDirection.FORWARD);
      assertEquals(position, test.getPosition());
  }

  @Test
  void checkLegalMoveBackward(){
    setAnimalToDefault();
    test.move(MoveDirection.BACKWARD);
    assertEquals(new Vector2d(2,1), test.getPosition());
  }

  @Test
  void checkIllegalMoveBackward() {
    Vector2d position = new Vector2d(0, 0);
    setAnimalWithPosition(position);
    test.move(MoveDirection.BACKWARD);
    assertEquals(position, test.getPosition());
  }

  @Test
  void checkChangePositionLeft(){
    setAnimalToDefault();
    test.move(MoveDirection.LEFT);
    test.move(MoveDirection.LEFT);
    assertEquals(MapDirection.SOUTH, test.getOrientation());
  }

  @Test
  void checkChangePositionRight(){
    setAnimalToDefault();
    test.move(MoveDirection.RIGHT);
    assertEquals(MapDirection.EAST, test.getOrientation());
  }

  @Test
  void checkLeftBorder(){
    Vector2d position = new Vector2d(4,1);
    setAnimalWithPosition(position);
    test.setOrientation(MapDirection.EAST);
    test.move(MoveDirection.FORWARD);
    assertEquals(position, test.getPosition());
  }

  @Test
  void checkRightBorder(){
    Vector2d position = new Vector2d(0,1);
    setAnimalWithPosition(position);
    test.setOrientation(MapDirection.WEST);
    test.move(MoveDirection.FORWARD);
    assertEquals(position, test.getPosition());
  }

  private void setAnimalToDefault(){
    IWorldMap map = new RectangularMap(4, 4);
    test = new Animal(map);
    test.setOrientation(defaultOrientation);
  }

  private void setAnimalWithPosition(Vector2d position){
    IWorldMap map = new RectangularMap(4, 4);
    test = new Animal(map,position);
    test.setOrientation(defaultOrientation);
    System.out.print(map.toString());
  }
}