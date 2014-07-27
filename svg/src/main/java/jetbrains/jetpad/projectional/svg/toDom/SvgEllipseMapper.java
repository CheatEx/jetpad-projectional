package jetbrains.jetpad.projectional.svg.toDom;

import jetbrains.jetpad.mapper.Synchronizers;
import jetbrains.jetpad.model.property.WritableProperty;
import jetbrains.jetpad.projectional.svg.SvgEllipse;
import jetbrains.jetpad.values.Color;
import org.vectomatic.dom.svg.OMSVGEllipseElement;

public class SvgEllipseMapper extends SvgElementMapper<SvgEllipse, OMSVGEllipseElement> {
  public SvgEllipseMapper(SvgEllipse source, OMSVGEllipseElement target) {
    super(source, target);
  }

  @Override
  protected void registerSynchronizers(SynchronizersConfiguration conf) {
    super.registerSynchronizers(conf);

    conf.add(Synchronizers.forPropsOneWay(getSource().cx, new WritableProperty<Double>() {
      @Override
      public void set(Double value) {
        getTarget().setAttribute("cx", Double.toString(value));
      }
    }));
    conf.add(Synchronizers.forPropsOneWay(getSource().cy, new WritableProperty<Double>() {
      @Override
      public void set(Double value) {
        getTarget().setAttribute("cy", Double.toString(value));
      }
    }));
    conf.add(Synchronizers.forPropsOneWay(getSource().rx, new WritableProperty<Double>() {
      @Override
      public void set(Double value) {
        getTarget().setAttribute("rx", Double.toString(value));
      }
    }));
    conf.add(Synchronizers.forPropsOneWay(getSource().ry, new WritableProperty<Double>() {
      @Override
      public void set(Double value) {
        getTarget().setAttribute("ry", Double.toString(value));
      }
    }));
    conf.add(Synchronizers.forPropsOneWay(getSource().fill, new WritableProperty<Color>() {
      @Override
      public void set(Color value) {
        getTarget().setAttribute("fill", value.toCssColor());
      }
    }));
  }
}
