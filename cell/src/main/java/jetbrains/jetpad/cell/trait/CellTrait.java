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
package jetbrains.jetpad.cell.trait;

import jetbrains.jetpad.event.*;
import jetbrains.jetpad.model.property.PropertyChangeEvent;
import jetbrains.jetpad.cell.Cell;
import jetbrains.jetpad.cell.CellPropertySpec;
import jetbrains.jetpad.cell.event.CompletionEvent;
import jetbrains.jetpad.cell.event.FocusEvent;

import java.util.HashSet;
import java.util.Set;

public abstract class CellTrait {
  public static final Object NULL = new Object();
  public static final CellTrait[] EMPTY_ARRAY = new CellTrait[0];

  protected CellTrait[] getBaseTraits(Cell cell) {
    return CellTrait.EMPTY_ARRAY;
  }

  public void onPropertyChanged(Cell cell, CellPropertySpec<?> prop, PropertyChangeEvent<?> event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onPropertyChanged(cell, prop, event);
    }
  }

  public void onFocusGained(Cell cell, FocusEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onFocusGained(cell, event);
    }
  }

  public void onFocusLost(Cell cell, FocusEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onFocusLost(cell, event);
    }
  }

  public void onMousePressed(Cell cell, MouseEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onMousePressed(cell, event);
    }
  }

  public void onMouseReleased(Cell cell, MouseEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onMouseReleased(cell, event);
    }
  }

  public void onMouseMoved(Cell cell, MouseEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onMouseMoved(cell, event);
    }
  }

  public void onMouseEntered(Cell cell, MouseEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onMouseEntered(cell, event);
    }
  }

  public void onMouseLeft(Cell cell, MouseEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onMouseLeft(cell, event);
    }
  }

  public void onKeyPressed(Cell cell, KeyEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onKeyPressed(cell, event);
    }
  }

  public void onKeyPressedLowPriority(Cell cell, KeyEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onKeyReleasedLowPriority(cell, event);
    }
  }

  public void onKeyReleased(Cell cell, KeyEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onKeyReleased(cell, event);
    }
  }

  public void onKeyReleasedLowPriority(Cell cell, KeyEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onKeyReleasedLowPriority(cell, event);
    }
  }

  public void onKeyTyped(Cell cell, KeyEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onKeyTyped(cell, event);
    }
  }

  public void onKeyTypedLowPriority(Cell cell, KeyEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onKeyTypedLowPriority(cell, event);
    }
  }

  public void onCopy(Cell cell, CopyCutEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onCopy(cell, event);
    }
  }

  public void onCut(Cell cell, CopyCutEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onCut(cell, event);
    }
  }

  public void onPaste(Cell cell, PasteEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onPaste(cell, event);
    }
  }

  public void onComplete(Cell cell, CompletionEvent event) {
    for (CellTrait t : getBaseTraits(cell)) {
      t.onComplete(cell, event);
    }
  }

  public void onViewTraitEvent(Cell cell, CellTraitEventSpec<?> spec, Event event) {
  }

  public Set<CellPropertySpec<?>> getChangedProperties(Cell cell) {
    Set<CellPropertySpec<?>> result = new HashSet<>();
    for (CellTrait t : getBaseTraits(cell)) {
      result.addAll(t.getChangedProperties(cell));
    }
    return result;
  }

  public Object get(Cell cell, CellPropertySpec<?> spec) {
    for (CellTrait t : getBaseTraits(cell)) {
      Object result = t.get(cell, spec);
      if (result != null) return result;
    }
    return null;
  }

  public Object get(Cell cell, CellTraitPropertySpec<?> spec) {
    for (CellTrait t : getBaseTraits(cell)) {
      Object result = t.get(cell, spec);
      if (result != null) return result;
    }
    return null;
  }
}