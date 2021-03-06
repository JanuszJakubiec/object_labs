package agh.cs.lab2;
import java.util.*;

public class World {
  public static void main(String args[])
  {
    List<MoveDirection> directions = new OptionsParser().parse(args);
    IWorldMap map = new GrassField(10);
    map.place(new Animal(map));
    map.place(new Animal(map,new Vector2d(3,4)));
    map.run(directions);
    System.out.print(map.toString());
  }
}
