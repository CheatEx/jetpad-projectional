/*
 * Copyright 2012-2014 JetBrains s.r.o
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.jetpad.cell.action;

import jetbrains.jetpad.cell.Cell;
import jetbrains.jetpad.cell.TextCell;
import jetbrains.jetpad.cell.position.PositionHandler;
import jetbrains.jetpad.model.composite.Composites;

public class CellActions {

  public static Runnable toCell(final Cell cell) {
    if (cell == null) throw new NullPointerException();

    return new Runnable() {
      @Override
      public void run() {
        if (!cell.focusable().get()) {
          throw new IllegalStateException();
        }
        cell.focus();
      }
    };
  }

  public static Runnable toHome(final Cell cell) {
    if (cell == null) throw new NullPointerException();

    return new Runnable() {
      @Override
      public void run() {
        if (!cell.focusable().get()) {
          throw new IllegalStateException();
        }
        cell.get(PositionHandler.PROPERTY).home();
        cell.focus();
      }
    };
  }

  public static Runnable toEnd(final Cell cell) {
    if (cell == null) throw new NullPointerException();

    return new Runnable() {
      @Override
      public void run() {
        if (!cell.focusable().get()) {
          throw new IllegalStateException();
        }
        cell.get(PositionHandler.PROPERTY).end();
        cell.focus();
      }
    };
  }

  public static Runnable toFirstFocusable(final Cell cell) {
    if (cell == null) throw new NullPointerException();

    return new Runnable() {
      @Override
      public void run() {
        toHome(Composites.firstFocusable(cell)).run();
      }
    };
  }

  public static Runnable toLastFocusable(final Cell cell) {
    if (cell == null) throw new NullPointerException();

    return new Runnable() {
      @Override
      public void run() {
        Cell lf = Composites.lastFocusable(cell);
        toEnd(lf).run();
      }
    };
  }

  public static Runnable toPosition(final Cell cell, final int pos) {
    if (cell instanceof TextCell) {
      return toPosition((TextCell) cell, pos);
    } else {
      return toCell(cell);
    }
  }

  public static Runnable toPosition(final TextCell cell, final int pos) {
    if (cell == null) throw new NullPointerException();

    return new Runnable() {
      @Override
      public void run() {
        cell.focus();
        cell.caretPosition().set(pos);
      }
    };
  }
}