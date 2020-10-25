package agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

  @Test
  void next() {
    MapDirection tester = MapDirection.NORTH;
    assertEquals(MapDirection.EAST, tester.next(), "next after NORTH is EAST");
    tester = tester.next();
    assertEquals(MapDirection.SOUTH, tester.next(), "next after EAST is SOUTH");
    tester = tester.next();
    assertEquals(MapDirection.WEST, tester.next(), "next after SOUTH is WEST");
    tester = tester.next();
    assertEquals(MapDirection.NORTH, tester.next(), "next after WEST is NORTH ");
  }

  @Test
  void previous() {
    MapDirection tester = MapDirection.NORTH;
    assertEquals(MapDirection.WEST, tester.previous(), "previous to NORTH is WEST");
    tester = tester.previous();
    assertEquals(MapDirection.SOUTH, tester.previous(), "previous to WEST is SOUTH");
    tester = tester.previous();
    assertEquals(MapDirection.EAST, tester.previous(), "previous to SOUTH is EAST");
    tester = tester.previous();
    assertEquals(MapDirection.NORTH, tester.previous(), "previous to EAST is NORTH");
  }
}