package dev.bdon;

import java.util.ArrayList;
import java.util.List;

public class GameObjectManager {
  private final static GameObjectManager instance = new GameObjectManager();

  private boolean running;
  private List<GameObject> spawnObjects = new ArrayList<>();
  private List<GameObject> destroyObjects = new ArrayList<>();
  private List<GameObject> liveObjects = new ArrayList<>();

  public void initialize() {
    running = true;
  }

  public void spawn(GameObject go) {
    spawnObjects.add(go);
  }

  public void destroy(GameObject go) {
    destroyObjects.add(go);
  }

  public void runSpawns() {
    liveObjects.addAll(spawnObjects);
    spawnObjects.clear();
  }

  public void runDestroys() {
    liveObjects.removeAll(destroyObjects);
    destroyObjects.clear();
  }

  public void updateAll() {
    for (var go : liveObjects) {
      go.update();
    }
  }

  public void drawAll() {
    for (var go : liveObjects) {
      go.draw(Graphics.instance());
    }
  }

  public boolean isRunning() {
    return running;
  }

  public void terminate() {

  }

  public static GameObjectManager instance() {
    return instance;
  }
}
