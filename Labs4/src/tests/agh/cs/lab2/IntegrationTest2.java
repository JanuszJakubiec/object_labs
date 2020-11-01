package agh.cs.lab2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest2 {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  public void setUpStreams(){
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void restoreStreams(){
    System.setOut(originalOut);
  }

  @Test
  void test_scenario1()
  {
    String[] steps = {"f", "l", "f", "f", "r", "r", "f", "b", "b", "b", "b", "b", "b", "b", "b", "b"};
    RectangularMap testMap = new RectangularMap(10,5);
    Animal test1 = new Animal(testMap, new Vector2d(0,4));
    test1.setOrientation( MapDirection.EAST);
    Animal test2 = new Animal(testMap, new Vector2d(3,0));
    testMap.place(test1);
    testMap.place(test2);
    World.main(steps);
    assertEquals(testMap.toString(), outContent.toString());
  }
}