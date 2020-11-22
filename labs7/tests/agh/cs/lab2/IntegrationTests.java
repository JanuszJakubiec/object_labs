package agh.cs.lab2;
import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTests {

  @Test
  void scenario1(){
    IWorldMap map1 = new RectangularMap(4, 4);
    Animal test = new Animal(map1);
    map1.place(test);
    String[] chain = {"f", "f", "backward", "left", "l", "r", "f", "b", "f", "f", "f", "f"};
    LinkedList<MoveDirection> list = OptionsParser.parse(chain);
    singleTest(2,3,MapDirection.NORTH, list, test);
    singleTest(2,4,MapDirection.NORTH, list, test);
    singleTest(2,3,MapDirection.NORTH, list, test);
    singleTest(2,3,MapDirection.WEST, list, test);
    singleTest(2,3,MapDirection.SOUTH, list, test);
    singleTest(2,3,MapDirection.WEST, list, test);
    singleTest(1,3,MapDirection.WEST, list, test);
    singleTest(2,3,MapDirection.WEST, list, test);
    singleTest(1,3,MapDirection.WEST, list, test);
    singleTest(0,3,MapDirection.WEST, list, test);
    singleTest(0,3,MapDirection.WEST, list, test);
    singleTest(0,3,MapDirection.WEST, list, test);
  }

  @Test
  void scenario2(){
    IWorldMap map2 = new RectangularMap(4, 4);
    Animal test = new Animal(map2);
    map2.place(test);
    String[] chain = {"l", "l", "l", "l", "f", "f", "f", "f", "f", "f", "b", "b", "b", "b", "b", "b"};
    LinkedList<MoveDirection> list = OptionsParser.parse(chain);
    singleTest(2,2,MapDirection.WEST, list, test);
    singleTest(2,2,MapDirection.SOUTH, list, test);
    singleTest(2,2,MapDirection.EAST, list, test);
    singleTest(2,2,MapDirection.NORTH, list, test);
    singleTest(2,3,MapDirection.NORTH, list, test);
    singleTest(2,4,MapDirection.NORTH, list, test);
    singleTest(2,4,MapDirection.NORTH, list, test);
    singleTest(2,4,MapDirection.NORTH, list, test);
    singleTest(2,4,MapDirection.NORTH, list, test);
    singleTest(2,4,MapDirection.NORTH, list, test);
    singleTest(2,3,MapDirection.NORTH, list, test);
    singleTest(2,2,MapDirection.NORTH, list, test);
    singleTest(2,1,MapDirection.NORTH, list, test);
    singleTest(2,0,MapDirection.NORTH, list, test);
    singleTest(2,0,MapDirection.NORTH, list, test);
    singleTest(2,0,MapDirection.NORTH, list, test);
  }

  private void singleTest(int x, int y, MapDirection direction, LinkedList <MoveDirection> list, Animal test){
    test.move(list.getFirst());
    list.removeFirst();
    assertEquals(new Vector2d(x,y), test.getPosition(), "Invalid position");
    assertEquals(direction, test.getOrientation(), "invalid direction");
  }
}