/*
 * Copyright 2012-2016 JetBrains s.r.o
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
package jetbrains.jetpad.completion;

import com.google.common.base.Function;
import jetbrains.jetpad.model.collections.CollectionItemEvent;
import jetbrains.jetpad.model.collections.list.ObservableArrayList;
import jetbrains.jetpad.model.collections.list.ObservableList;
import jetbrains.jetpad.model.event.EventHandler;
import jetbrains.jetpad.model.property.*;
import jetbrains.jetpad.model.transform.Transformers;

import java.util.Comparator;

public class CompletionMenuModel {
  private static final int CHUNK_SIZE = 60;
  private static final int DISTANCE_FROM_END_TO_LOAD = 10;

  public final Property<String> text = new ValueProperty<>();
  public final ObservableList<CompletionItem> items = new ObservableArrayList<>();
  public final Property<CompletionItem> selectedItem = new ValueProperty<>();
  public final Property<Boolean> loading = new ValueProperty<>(false);

  private final Property<Integer> visibleCount;

  private boolean myDisableReset;
  private int myChunkSize;
  private int myDistanceFromEndToLoad;


  public final ObservableList<CompletionItem> visibleItems;


  public CompletionMenuModel() {
    this(CHUNK_SIZE, DISTANCE_FROM_END_TO_LOAD);
  }

  public CompletionMenuModel(int chunkSize, int distanceFromEnd) {
    myChunkSize = chunkSize;
    myDistanceFromEndToLoad = distanceFromEnd;
    visibleCount = new ValueProperty<>(myChunkSize);

    visibleItems = Transformers.filter(new Function<CompletionItem, ReadableProperty<Boolean>>() {
      @Override
      public ReadableProperty<Boolean> apply(final CompletionItem input) {
        return new DerivedProperty<Boolean>(text) {
          @Override
          public Boolean doGet() {
            return input.isMatchPrefix(text.get() == null ? "" : text.get());
          }

          @Override
          public String getPropExpr() {
            return "isMatchPrefix(" + text.getPropExpr() + ", " + input + ")";
          }
        };
      }
    }).andThen(
      Transformers.sortBy(new Function<CompletionItem, ReadableProperty<CompletionItem>>() {
        @Override
        public ReadableProperty<CompletionItem> apply(final CompletionItem input) {
          return Properties.constant(input);
        }
      }, new Comparator<CompletionItem>() {
        @Override
        public int compare(CompletionItem c1, CompletionItem c2) {
          String text = "";

          int delta = c2.getSortPriority() - c1.getSortPriority();
          if (delta != 0) {
            return delta;
          }

          String t1 = c1.visibleText(text);
          String t2 = c2.visibleText(text);
          if (c1.isMatch(text) && !c2.isMatch(text)) {
            return -1;
          }
          if (!c1.isMatch(text) && c2.isMatch(text)) {
            return 1;
          }
          return t1.compareTo(t2);
        }
      })
    ).andThen(
      Transformers.<CompletionItem>firstN(visibleCount)
    ).transform(items).getTarget();

    visibleItems.addHandler(new EventHandler<CollectionItemEvent<? extends CompletionItem>>() {
      @Override
      public void onEvent(CollectionItemEvent<? extends CompletionItem> event) {
        if (myDisableReset) return;

        if (visibleItems.isEmpty()) {
          selectedItem.set(null);
        } else {
          selectedItem.set(visibleItems.get(0));
        }
      }
    });

    selectedItem.addHandler(new EventHandler<PropertyChangeEvent<CompletionItem>>() {
      @Override
      public void onEvent(PropertyChangeEvent<CompletionItem> event) {
        int index = visibleItems.lastIndexOf(event.getNewValue());
        if (index == -1) return;
        if ((visibleCount.get() - 1) - index <= myDistanceFromEndToLoad) {
          myDisableReset = true;
          try {
            visibleCount.set(visibleCount.get() + myChunkSize);
          } finally {
            myDisableReset = false;
          }
        }
      }
    });
  }

  public void up() {
    CompletionItem selected = selectedItem.get();
    if (selected == null) {
      throw new IllegalStateException();
    }

    int index = visibleItems.indexOf(selected);
    if (index > 0) {
      selectedItem.set(visibleItems.get(index - 1));
    }
  }

  public void down() {
    CompletionItem selected = selectedItem.get();
    if (selected == null) {
      throw new IllegalStateException();
    }

    int index = visibleItems.indexOf(selected);
    if (index < visibleItems.size() - 1) {
      selectedItem.set(visibleItems.get(index + 1));
    }
  }
}