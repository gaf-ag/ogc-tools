package org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.ogc.gml.v_3_2_1.jts.ConversionFailedException;
import org.locationtech.jts.geom.Geometry;


public interface GML321ToJTSConverterInterface<G, P, J extends Geometry> {

    J createGeometry(ObjectLocator locator, G geometryType) throws ConversionFailedException;

}
