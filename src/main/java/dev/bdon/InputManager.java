package dev.bdon;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;
import java.io.UncheckedIOException;

public class InputManager {
  private static InputManager instance = new InputManager();
  private TerminalScreen terminalScreen;
  private KeyStroke currentInput;

  public void initialize(TerminalScreen terminalScreen) {
    this.terminalScreen = terminalScreen;
  }

  public static InputManager instance() {
    return instance;
  }

  public KeyStroke pollInput() {
    return currentInput;
  }

  public void update() {
    try {
      currentInput = terminalScreen.pollInput();
    }
    catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public void terminate() {

  }
}
