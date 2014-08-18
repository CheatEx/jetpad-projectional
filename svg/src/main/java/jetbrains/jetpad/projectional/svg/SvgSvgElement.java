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
package jetbrains.jetpad.projectional.svg;

import jetbrains.jetpad.model.property.Property;

import java.util.HashMap;
import java.util.Map;

public class SvgSvgElement extends SvgStylableElement {
  protected static Map<String, SvgAttrSpec<?>> myAttrInfo = new HashMap<>(SvgStylableElement.myAttrInfo);

  public static final SvgAttrSpec<Double> HEIGHT = SvgAttrSpec.initAttrSpec("height", myAttrInfo);
  public static final SvgAttrSpec<Double> WIDTH = SvgAttrSpec.initAttrSpec("width", myAttrInfo);

  @Override
  protected Map<String, SvgAttrSpec<?>> getAttrInfo() {
    return myAttrInfo;
  }

  public Property<Double> getHeight() {
    return getProp(HEIGHT);
  }

  public Property<Double> getWidth() {
    return getProp(WIDTH);
  }
}
