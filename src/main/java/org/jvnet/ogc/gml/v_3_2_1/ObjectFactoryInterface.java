package org.jvnet.ogc.gml.v_3_2_1;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_2_1.AbstractGeometryType;
import net.opengis.gml.v_3_2_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_2_1.CoordinatesType;
import net.opengis.gml.v_3_2_1.DirectPositionListType;
import net.opengis.gml.v_3_2_1.DirectPositionType;
import net.opengis.gml.v_3_2_1.GeometryPropertyType;
import net.opengis.gml.v_3_2_1.CurvePropertyType;
import net.opengis.gml.v_3_2_1.LineStringType;
import net.opengis.gml.v_3_2_1.LinearRingPropertyType;
import net.opengis.gml.v_3_2_1.LinearRingType;
import net.opengis.gml.v_3_2_1.MultiGeometryPropertyType;
import net.opengis.gml.v_3_2_1.MultiGeometryType;
import net.opengis.gml.v_3_2_1.MultiCurvePropertyType;
import net.opengis.gml.v_3_2_1.MultiCurveType;
import net.opengis.gml.v_3_2_1.MultiPointPropertyType;
import net.opengis.gml.v_3_2_1.MultiPointType;
import net.opengis.gml.v_3_2_1.MultiSurfacePropertyType;
import net.opengis.gml.v_3_2_1.MultiSurfaceType;
import net.opengis.gml.v_3_2_1.PointPropertyType;
import net.opengis.gml.v_3_2_1.PointType;
import net.opengis.gml.v_3_2_1.SurfacePropertyType;
import net.opengis.gml.v_3_2_1.PolygonType;

public interface ObjectFactoryInterface {

	CoordinatesType createCoordinatesType();

	DirectPositionType createDirectPositionType();

	DirectPositionListType createDirectPositionListType();

	JAXBElement<DirectPositionType> createPos(DirectPositionType value);

	PointType createPointType();

	JAXBElement<PointType> createPoint(PointType value);

	PointPropertyType createPointPropertyType();

	JAXBElement<PointPropertyType> createPointProperty(
			PointPropertyType value);

	LineStringType createLineStringType();

	JAXBElement<LineStringType> createLineString(LineStringType value);

	CurvePropertyType createCurvePropertyType();

	JAXBElement<CurvePropertyType> createLineStringProperty(
			CurvePropertyType value);

	LinearRingType createLinearRingType();

	JAXBElement<LinearRingType> createLinearRing(LinearRingType value);

	LinearRingPropertyType createLinearRingPropertyType();
	
	JAXBElement<LinearRingPropertyType> createLinearRingProperty(
			LinearRingPropertyType value);
	
	PolygonType createPolygonType();

	SurfacePropertyType createSurfacePropertyType();

	JAXBElement<PolygonType> createPolygon(PolygonType value);

	JAXBElement<SurfacePropertyType> createPolygonProperty(
			SurfacePropertyType value);

	MultiPointType createMultiPointType();

	JAXBElement<MultiPointType> createMultiPoint(MultiPointType value);

	MultiPointPropertyType createMultiPointPropertyType();

	JAXBElement<MultiPointPropertyType> createMultiPointProperty(
			MultiPointPropertyType value);

	MultiCurveType createMultiCurveType();

	JAXBElement<MultiCurveType> createMultiLineString(
			MultiCurveType value);

	MultiCurvePropertyType createMultiCurvePropertyType();

	JAXBElement<MultiCurvePropertyType> createMultiLineStringProperty(
			MultiCurvePropertyType value);

	AbstractRingPropertyType createAbstractRingPropertyType();

	JAXBElement<AbstractRingPropertyType> createExterior(
			AbstractRingPropertyType value);

	JAXBElement<AbstractRingPropertyType> createInterior(
			AbstractRingPropertyType value);

	MultiSurfaceType createMultiSurfaceType();

	MultiSurfacePropertyType createMultiSurfacePropertyType();

	JAXBElement<MultiSurfaceType> createMultiPolygon(
			MultiSurfaceType value);

	JAXBElement<MultiSurfacePropertyType> createMultiPolygonProperty(
			MultiSurfacePropertyType value);

	MultiGeometryType createMultiGeometryType();

	JAXBElement<MultiGeometryType> createMultiGeometry(
			MultiGeometryType value);

	MultiGeometryPropertyType createMultiGeometryPropertyType();

	JAXBElement<MultiGeometryPropertyType> createMultiGeometryProperty(
			MultiGeometryPropertyType value);

	GeometryPropertyType createGeometryPropertyType();

	JAXBElement<AbstractGeometryType> createGeometry(
			AbstractGeometryType value);
}
