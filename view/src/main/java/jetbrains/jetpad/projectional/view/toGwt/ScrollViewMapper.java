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
package jetbrains.jetpad.projectional.view.toGwt;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import jetbrains.jetpad.geometry.Vector;
import jetbrains.jetpad.mapper.Synchronizers;
import jetbrains.jetpad.model.property.WritableProperty;
import jetbrains.jetpad.projectional.view.ScrollView;

class ScrollViewMapper extends CompositeViewMapper<ScrollView, Element> {
  ScrollViewMapper(ViewToDomContext ctx, ScrollView source) {
    super(ctx, source, DOM.createDiv());
  }

  @Override
  protected boolean isDomLayout() {
    return true;
  }

  @Override
  protected void registerSynchronizers(SynchronizersConfiguration conf) {
    super.registerSynchronizers(conf);

    final Style style = getTarget().getStyle();
    conf.add(Synchronizers.forPropsOneWay(getSource().scroll(), new WritableProperty<Boolean>() {
      @Override
      public void set(Boolean value) {
        style.setOverflow(value ? Style.Overflow.HIDDEN : Style.Overflow.VISIBLE);
      }
    }));
    conf.add(Synchronizers.forPropsOneWay(getSource().maxDimension(), new WritableProperty<Vector>() {
      @Override
      public void set(Vector value) {
        style.setProperty("maxWidth", value.x + "px");
        style.setProperty("maxHeight", value.y + "px");
      }
    }));
  }
}