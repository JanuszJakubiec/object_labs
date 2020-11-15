package agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

  @Test
  void to_string() {
    assertEquals( "(1,1)", new Vector2d(1,1).toString(),"to_string function is not working properly");
  }

  @Test
  void precedes() {
    Vector2d v1 = new Vector2d(1,1);
    Vector2d v2 = new Vector2d(2,2);
    Vector2d v3 = new Vector2d(2,3);
    assertTrue(v1.precedes(v2),"precedes returns false when true expected");
    assertFalse(v2.precedes(v1), "precedes returns true when false expected");
    assertTrue(v2.precedes(v3), "precedes returns true when false expected");
  }

  @Test
  void follows() {
    Vector2d v1 = new Vector2d(1,1);
    Vector2d v2 = new Vector2d(2,2);
    Vector2d v3 = new Vector2d(2,3);
    assertTrue(v2.follows(v1),"follows returns false when true expected");
    assertFalse(v1.follows(v2), "follows returns true when false expected");
    assertFalse(v2.follows(v3),"follows returns false when true expected");
  }

  @Test
  void upperRight() {
    Vector2d v1 = new Vector2d(0,0);
    Vector2d v2 = new Vector2d(1,1);
    Vector2d v3 = new Vector2d(1,0);
    Vector2d v4 = new Vector2d(0,1);
    assertEquals(v2, v1.upperRight(v2), "upperRight returns wrong point");
    assertEquals(v2, v2.upperRight(v4), "upperRight returns wrong point");
    assertEquals(v2, v3.upperRight(v2), "upperRight returns wrong point");
    assertEquals(v2, v4.upperRight(v3), "upperRight returns wrong point");
  }

  @Test
  void lowerLeft() {
    Vector2d v1 = new Vector2d(0,0);
    Vector2d v2 = new Vector2d(1,1);
    Vector2d v3 = new Vector2d(1,0);
    Vector2d v4 = new Vector2d(0,1);
    assertEquals(v1, v1.lowerLeft(v2), "lowerLeft returns wrong point");
    assertEquals(v1, v2.lowerLeft(v1), "lowerLeft returns wrong point");
    assertEquals(v1, v3.lowerLeft(v4), "lowerLeft returns wrong point");
    assertEquals(v1, v4.lowerLeft(v3), "lowerLeft returns wrong point");
  }

  @Test
  void add() {
    Vector2d v1 = new Vector2d(1,1);
    Vector2d v2 = new Vector2d(3,1);
    Vector2d v3 = new Vector2d(4,2);
    Vector2d v4 = new Vector2d(7,3);
    assertEquals(v3, v1.add(v2), "add returns wrongly added vectors");
    assertEquals(v4, v3.add(v2), "add returns wrongly added vectors");
  }

  @Test
  void substract() {
    Vector2d v1 = new Vector2d(1,1);
    Vector2d v2 = new Vector2d(3,1);
    Vector2d v3 = new Vector2d(4,2);
    Vector2d v4 = new Vector2d(7,3);
    assertEquals(v1, v3.substract(v2), " substract returns wrongly added vectors");
    assertEquals(v2, v4.substract(v3), " substract returns wrongly added vectors");
  }

  @Test
  void opposite() {
    Vector2d v1 = new Vector2d(1,1);
    Vector2d v2 = new Vector2d(-1,-1);
    assertEquals(v1,v2.opposite(),"opposite returns wrong vector");
    assertEquals(v2,v1.opposite(),"opposite returns wrong vector");
  }

  @Test
  void testEquals() {
    Vector2d v1 = new Vector2d(1,1);
    Vector2d v2 = new Vector2d(-1,-1);
    assertTrue(v1.equals(v1), "equals returns false when expected true");
    assertTrue(v1.equals(v1), "equals returns false when expected true");
    assertFalse(v2.equals(v1), "equals returns true when expected false");
    assertFalse(v1.equals(v2), "equals returns true when expected false");
  }
}