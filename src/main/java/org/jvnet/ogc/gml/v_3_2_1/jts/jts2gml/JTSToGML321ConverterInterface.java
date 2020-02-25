package org.jvnet.ogc.gml.v_3_2_1.jts.jts2gml;

import org.locationtech.jts.geom.Geometry;

import javax.xml.bind.JAXBElement;


public interface JTSToGML321ConverterInterface<G, P, J extends Geometry> {

  G createGeometryType(J geometry);

  P createPropertyType(J geometry);

  JAXBElement<? extends G> createElement(J geometry);

}
