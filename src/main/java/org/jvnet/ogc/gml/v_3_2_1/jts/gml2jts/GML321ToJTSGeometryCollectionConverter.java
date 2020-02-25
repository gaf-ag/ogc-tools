package org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts;

import net.opengis.gml.v_3_2_1.AbstractGeometricAggregateType;
import net.opengis.gml.v_3_2_1.MultiGeometryPropertyType;
import net.opengis.gml.v_3_2_1.MultiGeometryType;
import net.opengis.gml.v_3_2_1.MultiCurvePropertyType;
import net.opengis.gml.v_3_2_1.MultiCurveType;
import net.opengis.gml.v_3_2_1.MultiPointPropertyType;
import net.opengis.gml.v_3_2_1.MultiPointType;
import net.opengis.gml.v_3_2_1.MultiSurfacePropertyType;
import net.opengis.gml.v_3_2_1.MultiSurfaceType;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.ogc.gml.v_3_2_1.jts.ConversionFailedException;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.MultiPolygon;


public class GML321ToJTSGeometryCollectionConverter implements
		GML321ToJTSConverterInterface<AbstractGeometricAggregateType, MultiGeometryPropertyType, GeometryCollection> {

	// + GeometryCollection
	// + MultiPoint
	// + MultiLineString
	// + MultiPolygon

	private final GML321ToJTSConverterInterface<MultiPointType, MultiPointPropertyType, MultiPoint> multiPointConverter;
	private final GML321ToJTSConverterInterface<MultiCurveType, MultiCurvePropertyType, MultiLineString> multiLineStringConverter;
	private final GML321ToJTSConverterInterface<MultiSurfaceType, MultiSurfacePropertyType, MultiPolygon> multiPolygonConverter;
	private final GML321ToJTSConverterInterface<MultiGeometryType, MultiGeometryPropertyType, GeometryCollection> multiGeometryConverter;

	public GML321ToJTSGeometryCollectionConverter(
			GML321ToJTSConverterInterface<MultiPointType, MultiPointPropertyType, MultiPoint> multiPointConverter,
			GML321ToJTSConverterInterface<MultiCurveType, MultiCurvePropertyType, MultiLineString> multiLineStringConverter,
			GML321ToJTSConverterInterface<MultiSurfaceType, MultiSurfacePropertyType, MultiPolygon> multiPolygonConverter,
			GML321ToJTSConverterInterface<MultiGeometryType, MultiGeometryPropertyType, GeometryCollection> multiGeometryConverter) {
		this.multiPointConverter = multiPointConverter;
		this.multiLineStringConverter = multiLineStringConverter;
		this.multiPolygonConverter = multiPolygonConverter;
		this.multiGeometryConverter = multiGeometryConverter;
	}

	public GeometryCollection createGeometry(final ObjectLocator locator,
			final AbstractGeometricAggregateType abstractGeometryType)
			throws ConversionFailedException {

		if (abstractGeometryType instanceof MultiPointType) {
			return multiPointConverter.createGeometry(locator,
					(MultiPointType) abstractGeometryType);
		} else if (abstractGeometryType instanceof MultiCurveType) {
			return multiLineStringConverter.createGeometry(locator,
					(MultiCurveType) abstractGeometryType);
		} else if (abstractGeometryType instanceof MultiSurfaceType) {
			return multiPolygonConverter.createGeometry(locator,
					(MultiSurfaceType) abstractGeometryType);
		} else if (abstractGeometryType instanceof MultiGeometryType) {
			return multiGeometryConverter.createGeometry(locator,
					(MultiGeometryType) abstractGeometryType);
		}else {
			throw new ConversionFailedException(locator, "Unexpected type.");
		}

	}

	public GeometryCollection createGeometry(final ObjectLocator locator,
			final MultiGeometryPropertyType multiGeometryPropertyType)
			throws ConversionFailedException {

		if (multiGeometryPropertyType.isSetAbstractGeometricAggregate()) {
			return createGeometry(
					locator.property("geometricAggregate",
							multiGeometryPropertyType.getAbstractGeometricAggregate()).property(
									"value", multiGeometryPropertyType.getAbstractGeometricAggregate().getValue()),
					multiGeometryPropertyType.getAbstractGeometricAggregate()
							.getValue());
		} else {
			throw new ConversionFailedException(locator,
					"Expected [GeometricAggregate] element.");
		}
	}
}
