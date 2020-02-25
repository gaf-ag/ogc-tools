package org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts;

import net.opengis.gml.v_3_2_1.AbstractGeometryType;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.ogc.gml.v_3_2_1.jts.ConversionFailedException;
import org.locationtech.jts.geom.Geometry;


public interface GML321ToJTSSRIDConverterInterface {

  void convert(ObjectLocator locator, AbstractGeometryType source, Geometry target)
      throws ConversionFailedException;
}
