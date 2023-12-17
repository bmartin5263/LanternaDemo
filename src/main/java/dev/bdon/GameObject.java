package dev.bdon;

public abstract class GameObject {
  protected Position position = new Position(0, 0);

  public GameObject() {
  }

  public GameObject(Position position) {
    this.position = position;
  }

  public abstract void update();
  public abstract void draw(Graphics gfx);
  public abstract void onDestroy();

}
