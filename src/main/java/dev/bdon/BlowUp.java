package dev.bdon;

public class BlowUp implements Runnable {
  private Bullet bullet;

  public BlowUp(Bullet bullet) {
    this.bullet = bullet;
  }

  @Override
  public void run() {
    System.out.println("Boom!");
    bullet.onDestroy();
  }

}
