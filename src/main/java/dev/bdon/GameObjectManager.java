package dev.bdon;

import java.util.ArrayList;
import java.util.List;

public class GameObjectManager {
  private final static GameObjectManager instance = new GameObjectManager();
  public static GameObjectManager instance() {
    return instance;
  }

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
    for (var obj : spawnObjects) {
      obj.onSpawn();
    }
    liveObjects.addAll(spawnObjects);
    spawnObjects.clear();
  }

  public void runDestroys() {
    for (var obj : destroyObjects) {
      obj.onDestroy();
    }
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
}
