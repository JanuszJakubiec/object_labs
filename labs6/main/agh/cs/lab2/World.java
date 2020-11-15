package agh.cs.lab2;
import java.util.*;

public class World {
  public static void main(String args[])
  {
    try {
      List<MoveDirection> directions = new OptionsParser().parse(args);
      IWorldMap map = new GrassField(10);
      map.place(new Animal(map));
      map.place(new Animal(map, new Vector2d(4, 2)));
      map.run(directions);
      System.out.print(map.toString());
    }catch(IllegalArgumentException ex) {
      System.out.print(ex);
    }

  }
}
