package dev.bdon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Timer {
  private static Timer instance = new Timer();
  public static Timer instance() {
    return instance;
  }

  private static int nextId = 1;
  private final List<TimerEvent> events = new ArrayList<>();

  public int setTimeout(long milli, Runnable action) {
    var executeAt = System.currentTimeMillis();
    var id = nextId++;
    events.add(new TimerEvent(id, executeAt + milli, null, action));
    return id;
  }

  public int setInterval(long milli, Runnable action) {
    var executeAt = System.currentTimeMillis();
    var id = nextId++;
    events.add(new TimerEvent(id, executeAt + milli, milli, action));
    return id;
  }

  public void cancel(int id) {
    if (id == 0) {
      return;
    }
    events.removeIf(e -> e.getId() == id);
  }

  public void update() {
    var currentTime = System.currentTimeMillis();
    var eventIterator = events.listIterator();
    while (eventIterator.hasNext()) {
      var event = eventIterator.next();
      if (!event.isReady(currentTime)) {
        continue;
      }

      event.execute();
      if (event.repeats()) {
        event.reset();
      }
      else {
        eventIterator.remove();
      }
    }
  }

}
