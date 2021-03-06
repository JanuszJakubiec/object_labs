package agh.cs.lab2;

import java.util.Objects;
import java.util.Vector;

public class Vector2d {
  public final int x;
  public final int y;

  public Vector2d(int x, int y)
  {
    this.x = x;
    this.y = y;
  }

  public String toString()
  {
    return "(" + this.x + "," + this.y + ")";
  }

  public boolean precedes( Vector2d other )
  {
    return other.x >= this.x && other.y >= this.y;
  }

  public boolean follows( Vector2d other )
  {
    return other.x <= this.x && other.y <= this.y;
  }

  public Vector2d upperRight( Vector2d other )
  {
    return new Vector2d(this.x > other.x ? this.x : other.x, this.y > other.y ? this.y : other.y);
  }

  public Vector2d lowerLeft( Vector2d other )
  {
    return new Vector2d(this.x < other.x ? this.x : other.x, this.y < other.y ? this.y : other.y);
  }

  public Vector2d add( Vector2d other )
  {
    return new Vector2d( this.x+other.x, this.y + other.y );
  }

  public Vector2d substract ( Vector2d other )
  {
    return new Vector2d(this.x - other.x, this.y - other.y);
  }

  public Vector2d opposite()
  {
    return new Vector2d(-1 * this.x, -1 * this.y);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vector2d vector2d = (Vector2d) o;
    return x == vector2d.x &&
            y == vector2d.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
