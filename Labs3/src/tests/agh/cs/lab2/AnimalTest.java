package agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

  Animal test = new Animal();
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
    setAnimalToDefault();
    Vector2d position = new Vector2d(0, 4);
    test.move(MoveDirection.FORWARD);
    test.setPosition(position);
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
    setAnimalToDefault();
    Vector2d position = new Vector2d(0, 0);
    test.move(MoveDirection.BACKWARD);
    test.setPosition(position);
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
    setAnimalToDefault();
    test.setOrientation(MapDirection.EAST);
    Vector2d position = new Vector2d(4,1);
    test.setPosition(position);
    test.move(MoveDirection.FORWARD);
    assertEquals(position, test.getPosition());
  }

  @Test
  void checkRightBorder(){
    setAnimalToDefault();
    test.setOrientation(MapDirection.WEST);
    Vector2d position = new Vector2d(0,1);
    test.setPosition(position);
    test.move(MoveDirection.FORWARD);
    assertEquals(position, test.getPosition());
  }

  private void setAnimalToDefault(){
    test.setPosition(defaultPosition);
    test.setOrientation(defaultOrientation);
  }
}