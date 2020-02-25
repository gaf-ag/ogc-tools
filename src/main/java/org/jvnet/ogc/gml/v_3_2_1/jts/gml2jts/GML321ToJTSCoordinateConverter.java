package org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts;

import java.util.List;

import net.opengis.gml.v_3_2_1.CoordinatesType;
import net.opengis.gml.v_3_2_1.DirectPositionListType;
import net.opengis.gml.v_3_2_1.DirectPositionType;

import org.apache.commons.lang.StringUtils;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.ogc.gml.v_3_2_1.jts.ConversionFailedException;
import org.locationtech.jts.geom.Coordinate;


public class GML321ToJTSCoordinateConverter {

	public Coordinate createCoordinate(final ObjectLocator locator,
									   final DirectPositionType directPositionType) throws ConversionFailedException {

		final List<Double> value = directPositionType.getValue();
		final int count = value.size();
		if (count == 2) {
			final double x = value.get(0);
			final double y = value.get(1);
			return new Coordinate(x, y);
		} else if (count == 3) {
			final double x = value.get(0);
			final double y = value.get(1);
			final double z = value.get(2);
			return new Coordinate(x, y, z);
		} else {
			throw new ConversionFailedException(
					locator.property("value", value), //$NON-NLS-1$
					"Direct position type is expected to have 2 or 3 items."); //$NON-NLS-1$
		}
	}

	public Coordinate[] createCoordinates(final ObjectLocator locator,
										  final DirectPositionListType directPositionListType)
			throws ConversionFailedException {

		final int dimensions = directPositionListType.isSetSrsDimension() ? directPositionListType
				.getSrsDimension().intValue() : 2;

		if (dimensions < 2 || dimensions > 3) {
			throw new ConversionFailedException(locator.property(
					"srsDimension", dimensions), //$NON-NLS-1$
					"Only two- or three-dimensional coordinates are supported."); //$NON-NLS-1$
		}

		final List<Double> values = directPositionListType.getValue();
		if (values.size() % dimensions != 0) {
			throw new ConversionFailedException(locator.property(
					"value", values), //$NON-NLS-1$
					"Wrong number of entries in the list."); //$NON-NLS-1$
		}

		final Coordinate[] coordinates = new Coordinate[values.size() / dimensions];
		for (int index = 0; index < values.size() / dimensions; index++) {
			if (dimensions == 2) {
				coordinates[index] = new Coordinate(values.get(index
						* dimensions), values.get(index * dimensions + 1));
			} else if (dimensions == 3) {
				coordinates[index] = new Coordinate(values.get(index
						* dimensions), values.get(index * dimensions + 1),
						values.get(index * dimensions + 2));
			}
		}
		return coordinates;

	}

	public Coordinate[] createCoordinates(final ObjectLocator locator,
			final CoordinatesType coordinates) throws ConversionFailedException {

		return createCoordinates(locator,
				coordinates.getValue(),
				coordinates.getDecimal(),
				coordinates.getCs(),
				coordinates.getTs());
	}

	public Coordinate[] createCoordinates(final ObjectLocator locator,
		  	final String value,
			final String ds,
			final String cs,
			final String ts) throws ConversionFailedException {

		final String tupleSeparator = ts == null ? " " : ts;
		final String[] tuples = StringUtils.split(value, tupleSeparator);

		final Coordinate[] coordinatesArray = new Coordinate[tuples.length];
		for (int index = 0; index < tuples.length; index++) {
			coordinatesArray[index] = createCoordinate(
					locator.item(index, tuples[index]), tuples[index], ds, cs);
		}
		return coordinatesArray;
	}

	public Coordinate createCoordinate(final ObjectLocator locator,
			final String value,
			final String ds,
			final String cs) throws ConversionFailedException {

		final String coordinateSeparator = cs == null ? "," : cs;

		final String[] coordinates = StringUtils.split(value,
				coordinateSeparator);

		final double[] coordinateComponents = new double[coordinates.length];
		for (int index = 0; index < coordinates.length; index++) {
			coordinateComponents[index] = createCoordinateComponent(
					locator.item(index, coordinates[index]),
					coordinates[index], ds);
		}
		if (coordinateComponents.length == 2) {
			return new Coordinate(coordinateComponents[0],
					coordinateComponents[1]);
		} else if (coordinateComponents.length == 3) {
			return new Coordinate(coordinateComponents[0],
					coordinateComponents[1],
					coordinateComponents[2]);
		} else {
			throw new ConversionFailedException(locator,
					"Expected two or three coordinates.");
		}
	}

	public double createCoordinateComponent(final ObjectLocator locator,
			final String value,
			final String ds) throws ConversionFailedException {

		final String decimalSeparator = ds == null ? "." : ds;
		try {
			return Double.parseDouble(value.replace(decimalSeparator, "."));
		} catch (NumberFormatException nfex) {
			throw new ConversionFailedException(locator, nfex);
		}
	}
}
