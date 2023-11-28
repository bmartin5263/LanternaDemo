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

    screen.startScreen();
    screen.setCursorPosition(null);
    TextGraphics gfx = screen.newTextGraphics();
    TerminalSize terminalSize = screen.getTerminalSize();

    System.out.println(terminalSize);

    boolean running = true;
    int row = 0;
    int col = 0;
    while (running) {
      var input = screen.pollInput();
      if (input != null) {
        var keyType = input.getKeyType();
        if (keyType == KeyType.ArrowUp && row > 0) {
          row -= 1;
        }
        else if (keyType == KeyType.ArrowDown && row < terminalSize.getRows() - 1) {
          row += 1;
        }
        else if (keyType == KeyType.ArrowLeft && col > 0) {
          col -= 1;
        }
        else if (keyType == KeyType.ArrowRight && col < terminalSize.getColumns() - 1) {
          col += 1;
        }
        else if (keyType == KeyType.Escape) {
          running = false;
        }
      }

      screen.clear();
      gfx.setCharacter(col, row, 'Λ');
      screen.refresh();

      System.out.printf("row=%s, col=%s\n", row, col);
      Thread.sleep(10);
    }

    screen.stopScreen();
    screen.close();
    terminal.close();
  }
}
