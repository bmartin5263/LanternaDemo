package dev.bdon;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.io.UncheckedIOException;

public class Graphics {
  private static final Graphics instance = new Graphics();
  private Terminal terminal;
  private TerminalScreen screen;
  private TextGraphics textGraphics;

  public void initialize(Terminal terminal, TerminalScreen screen) {
    this.terminal = terminal;
    this.screen = screen;

    try {
      screen.startScreen();
    }
    catch (IOException e) {
      throw new UncheckedIOException(e);
    }

    screen.setCursorPosition(null);
    textGraphics = screen.newTextGraphics();
  }

  public static Graphics instance() {
    return instance;
  }

  public TerminalSize getSize() {
    return screen.getTerminalSize();
  }

  public void clear() {
    screen.clear();
  }

  public void setCharacter(int x, int y, char c) {
    textGraphics.setCharacter(x, y, c);
  }

  public void refresh() {
    try {
      screen.refresh();
    }
    catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public void terminate() {
    try {
      screen.stopScreen();
      screen.close();
      terminal.close();
    }
    catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
