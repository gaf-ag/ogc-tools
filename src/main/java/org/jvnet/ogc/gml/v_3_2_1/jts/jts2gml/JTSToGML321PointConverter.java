package org.jvnet.ogc.gml.v_3_2_1.jts.jts2gml;

import javax.xml.bind.JAXBElement;

import org.jvnet.ogc.gml.v_3_2_1.ObjectFactoryInterface;

import net.opengis.gml.v_3_2_1.DirectPositionType;
import net.opengis.gml.v_3_2_1.PointPropertyType;
import net.opengis.gml.v_3_2_1.PointType;
import org.locationtech.jts.geom.Point;


public class JTSToGML321PointConverter extends
		AbstractJTSToGML321Converter<PointType, PointPropertyType, Point> {

	private final JTSToGML321CoordinateConverter coordinateConverter;

	public JTSToGML321PointConverter(
			final ObjectFactoryInterface objectFactory,
			final JTSToGML321SRSReferenceGroupConverterInterface srsReferenceGroupConverter,
			final JTSToGML321CoordinateConverter coordinateConverter) {
		super(objectFactory, srsReferenceGroupConverter);
		this.coordinateConverter = coordinateConverter;
	}

	@Override
	protected PointType doCreateGeometryType(final Point point) {

		final PointType resultPoint = getObjectFactory().createPointType();

		if (!point.isEmpty()) {
			final DirectPositionType directPosition = coordinateConverter
					.convertCoordinate(point.getCoordinate());
			resultPoint.setPos(directPosition);
		}
		return resultPoint;
	}

	@Override
	public PointPropertyType createPropertyType(final Point point) {
		final PointPropertyType pointPropertyType = getObjectFactory().createPointPropertyType();

		pointPropertyType.setPoint(createGeometryType(point));
		return pointPropertyType;
	}

	@Override
	public JAXBElement<PointType> createElement(final Point point) {
		return getObjectFactory().createPoint(createGeometryType(point));
	}

}
