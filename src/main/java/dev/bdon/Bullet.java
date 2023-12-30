package dev.bdon;

public class Bullet extends GameObject {
  private int handle;

  public static void doSomething() {

  }

  public void moveUp() {
    position.y -= 1;
    if (false) { // todo - how can we check if we are off screen?
      // if we are off screen, destroy ourselves
      GameObjectManager.instance().destroy(this);
    }
  }

  public Bullet(Position position) {
    super(position);
  }

  @Override
  public void onSpawn() {
    // Implement Runnable
    handle = Timer.instance().setInterval(1000, new BlowUp(this));        // named class
    handle = Timer.instance().setInterval(1000, new Runnable() {                // anonymous class
      int x = 55;
      @Override
      public void run() {
        handle = 0;
        System.out.println("anonymous class");
      }
    });

    // Method Reference
    Bullet b2 = new Bullet(position);
    handle = Timer.instance().setInterval(1000, Bullet::doSomething); // static
    handle = Timer.instance().setInterval(1000, this::moveUp);        // this
    handle = Timer.instance().setInterval(1000, b2::moveUp);          // random variable

    // Lambda
    handle = Timer.instance().setInterval(1000, () -> {  // () -> {}
      handle = 0;
      System.out.println("lambda");
    });
  }

  @Override
  public void onDestroy() {
    // Called by the GameObjectManager when this object is being removed from the game
    Timer.instance().cancel(handle);
  }

  @Override
  public void update() {
    // Do nothing, all updates are handled by timers
  }

  @Override
  public void draw(Graphics gfx) {
    gfx.setCharacter(position.x, position.y, '.');
  }

}
