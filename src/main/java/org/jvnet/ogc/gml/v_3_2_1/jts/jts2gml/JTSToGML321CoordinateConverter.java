package org.jvnet.ogc.gml.v_3_2_1.jts.jts2gml;

import org.jvnet.ogc.gml.v_3_2_1.ObjectFactoryInterface;

import net.opengis.gml.v_3_2_1.DirectPositionType;
import org.locationtech.jts.geom.Coordinate;


public class JTSToGML321CoordinateConverter {

	private final ObjectFactoryInterface objectFactory;
	private final JTSToGML321SRSReferenceGroupConverterInterface srsReferenceGroupConverter;

	public JTSToGML321CoordinateConverter(final ObjectFactoryInterface objectFactory,
			final JTSToGML321SRSReferenceGroupConverterInterface srsReferenceGroupConverter) {
		super();
		this.objectFactory = objectFactory;
		this.srsReferenceGroupConverter = srsReferenceGroupConverter;
	}

	public DirectPositionType convertCoordinate(final Coordinate coordinate) {
		final DirectPositionType directPosition = objectFactory.createDirectPositionType();

		directPosition.getValue().add(coordinate.x);
		directPosition.getValue().add(coordinate.y);
		if (!Double.isNaN(coordinate.z)) {
			directPosition.getValue().add(coordinate.z);
		}
		return directPosition;

	}

	public DirectPositionType[] convertCoordinates(final Coordinate[] coordinates) {
		if (coordinates == null) {
			return null;
		} else {
			final DirectPositionType[] directPositions = new DirectPositionType[coordinates.length];
			for (int index = 0; index < coordinates.length; index++) {
				directPositions[index] = convertCoordinate(coordinates[index]);
			}
			return directPositions;
		}
	}
}
