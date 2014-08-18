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

public class SvgRectElement extends SvgStylableElement {
  public static final SvgAttrSpec<Double> X = new SvgAttrSpec<>("x");
  public static final SvgAttrSpec<Double> Y = new SvgAttrSpec<>("y");
  public static final SvgAttrSpec<Double> HEIGHT = new SvgAttrSpec<>("height");
  public static final SvgAttrSpec<Double> WIDTH = new SvgAttrSpec<>("width");

  protected static Map<String, SvgAttrSpec<?>> myAttrInfo;
  static {
    myAttrInfo = new HashMap<>(SvgElement.myAttrInfo);
    myAttrInfo.put(X.toString(), X);
    myAttrInfo.put(Y.toString(), Y);
    myAttrInfo.put(HEIGHT.toString(), HEIGHT);
    myAttrInfo.put(WIDTH.toString(), WIDTH);
  }

  @Override
  protected Map<String, SvgAttrSpec<?>> getAttrInfo() {
    return myAttrInfo;
  }

  public Property<Double> getX() {
    return getProp(X);
  }

  public Property<Double> getY() {
    return getProp(Y);
  }

  public Property<Double> getHeight() {
    return getProp(HEIGHT);
  }

  public Property<Double> getWidth() {
    return getProp(WIDTH);
  }
}