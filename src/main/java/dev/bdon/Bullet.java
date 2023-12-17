package dev.bdon;

public class Bullet extends GameObject {

  public Bullet(Position position) {
    super(position);
  }

  @Override
  public void update() {
    position.y -= 1;
  }

  @Override
  public void draw(Graphics gfx) {
    gfx.setCharacter(position.x, position.y, '.');
  }

  @Override
  public void onDestroy() {

  }
}
