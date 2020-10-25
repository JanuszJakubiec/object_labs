package agh.cs.lab2;
import java.util.*;

public class World {
  public static void main(String args[])
  {
    Animal cow = new Animal();
    System.out.println(cow.toString());
    LinkedList<MoveDirection> list = OptionsParser.parse(args);
    for(MoveDirection move : list)
    {
      cow.move(move);
      System.out.println(cow.toString());
    }
  }
}
