package dev.bdon;

public class TimerEvent {
  private int id;
  private long executeAt;         // when to execute the action
  private final Long interval;    // how much time in-between actions
  private final Runnable action;  // the action

  public TimerEvent(int id, long executeAt, Long interval, Runnable action) {
    this.executeAt = executeAt;
    this.interval = interval;
    this.action = action;
  }

  public boolean isReady(long currentTime) {
    return currentTime >= executeAt;
  }

  public void execute() {
    action.run();
  }

  public boolean repeats() {
    return interval != null;
  }

  public void reset() {
    executeAt += interval;
  }

  public int getId() {
    return id;
  }
}
