package dev.bdon;

import com.googlecode.lanterna.input.KeyType;

public class Ship extends GameObject {

  @Override
  public void update() {
    var input = InputManager.instance().pollInput();
    var terminalSize = Graphics.instance().getSize();
    if (input != null) {
      var keyType = input.getKeyType();
      if (keyType == KeyType.ArrowUp && position.y > 0) {
        position.y -= 1;
      }
      else if (keyType == KeyType.ArrowDown && position.y < terminalSize.getRows() - 1) {
        position.y += 1;
      }
      else if (keyType == KeyType.ArrowLeft && position.x > 0) {
        position.x -= 1;
      }
      else if (keyType == KeyType.ArrowRight && position.x < terminalSize.getColumns() - 1) {
        position.x += 1;
      }
      else if (keyType == KeyType.Enter) {
        GameObjectManager.instance().spawn(new Bullet(new Position(position.x, position.y - 1)));
      }
    }
  }

  @Override
  public void draw(Graphics gfx) {
    gfx.setCharacter(position.x, position.y, 'X');
  }

  @Override
  public void onDestroy() {
    // do something when the ship is destroyed
  }
}
