package org.jvnet.ogc.gml.v_3_2_1.jts.jts2gml;

import net.opengis.gml.v_3_2_1.AbstractGeometryType;
import org.locationtech.jts.geom.Geometry;


public interface JTSToGML321SRSReferenceGroupConverterInterface {

  void convert(Geometry source, AbstractGeometryType target);

}
