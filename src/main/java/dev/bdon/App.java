package dev.bdon;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.Random;

public class App {
  public static void main(String[] args) throws Exception {
    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    Terminal terminal = defaultTerminalFactory.createTerminal();
    TerminalScreen screen = new TerminalScreen(terminal);

    Graphics gfx = Graphics.instance();
    gfx.initialize(terminal, screen);

    InputManager input = InputManager.instance();
    input.initialize(screen);

    GameObjectManager goManager = GameObjectManager.instance();
    goManager.initialize();

    goManager.spawn(new Ship());

    while (goManager.isRunning()) {
      // Clear whatever the last frame had
      gfx.clear();

      // Polls the screen for the next input and saves it for the rest of the frame
      input.update();

      // Update game objects
      goManager.runSpawns();
      goManager.updateAll();
      goManager.runDestroys();


      goManager.drawAll();
      gfx.refresh();
      Thread.sleep(10);
    }

    goManager.terminate();
    input.terminate();
    gfx.terminate();
  }
}
