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
package jetbrains.jetpad.projectional.svg.toDom;

import jetbrains.jetpad.mapper.Synchronizers;
import jetbrains.jetpad.model.property.WritableProperty;
import jetbrains.jetpad.projectional.svg.SvgTextElement;
import org.vectomatic.dom.svg.OMSVGTextElement;

public class SvgTextElementMapper extends SvgStylableElementMapper<SvgTextElement, OMSVGTextElement> {
  public SvgTextElementMapper(SvgTextElement source, OMSVGTextElement target) {
    super(source, target);
  }

  @Override
  protected void registerSynchronizers(SynchronizersConfiguration conf) {
    super.registerSynchronizers(conf);

    conf.add(Synchronizers.forPropsOneWay(getSource().getProp(SvgTextElement.X), new WritableProperty<Double>() {
      @Override
      public void set(Double value) {
        getTarget().setAttribute("x", Double.toString(value));
      }
    }));
    conf.add(Synchronizers.forPropsOneWay(getSource().getProp(SvgTextElement.Y), new WritableProperty<Double>() {
      @Override
      public void set(Double value) {
        getTarget().setAttribute("y", Double.toString(value));
      }
    }));
  }
}
