package agh.cs.lab2;

public interface IPositionChangePublisher {
  void addObserver(IPositionChangeObserver observer);

  void removeObserver(IPositionChangeObserver observer);
}
