package agh.cs.lab2;
import java.util.*;

public class OptionsParser {
  public static LinkedList<MoveDirection> parse(String[] chain){
    LinkedList<MoveDirection> result = new LinkedList<MoveDirection>();
    for(String word : chain)
    {
      switch (word)
      {
        case "forward", "f" -> result.add(MoveDirection.FORWARD);
        case "backward", "b" -> result.add(MoveDirection.BACKWARD);
        case "right", "r" -> result.add(MoveDirection.RIGHT);
        case "left", "l" -> result.add(MoveDirection.LEFT);
        default -> throw new IllegalArgumentException(word + "argument is invalid");
      }
    }
    return result;
  }
}
