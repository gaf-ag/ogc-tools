package org.jvnet.ogc.gml.v_3_2_1.jts.jts2gml;

import javax.xml.bind.JAXBElement;

import org.jvnet.ogc.gml.v_3_2_1.ObjectFactoryInterface;

import net.opengis.gml.v_3_2_1.DirectPositionType;
import net.opengis.gml.v_3_2_1.CurvePropertyType;
import net.opengis.gml.v_3_2_1.LineStringType;
import org.locationtech.jts.geom.LineString;


public class JTSToGML321LineStringConverter
		extends AbstractJTSToGML321Converter<LineStringType, CurvePropertyType, LineString> {

	private final JTSToGML321CoordinateConverter coordinateConverter;

	public JTSToGML321LineStringConverter(
			ObjectFactoryInterface objectFactory,
			JTSToGML321SRSReferenceGroupConverterInterface srsReferenceGroupConverter,
			JTSToGML321CoordinateConverter coordinateConverter) {
		super(objectFactory, srsReferenceGroupConverter);
		this.coordinateConverter = coordinateConverter;
	}

	@Override
	protected LineStringType doCreateGeometryType(final LineString lineString) {

		final LineStringType resultLineString = getObjectFactory().createLineStringType();

		for (DirectPositionType directPosition : coordinateConverter
				.convertCoordinates(lineString.getCoordinates())) {
			final JAXBElement<DirectPositionType> pos = getObjectFactory()
					.createPos(directPosition);
			resultLineString.getPosOrPointPropertyOrPointRep().add(pos);
		}
		return resultLineString;
	}

	public CurvePropertyType createPropertyType(final LineString lineString) {
		final CurvePropertyType curvePropertyType = getObjectFactory()
				.createCurvePropertyType();
		curvePropertyType.setAbstractCurve(createElement(lineString));
		return curvePropertyType;
	}

	public JAXBElement<LineStringType> createElement(final LineString linearString) {
		return getObjectFactory().createLineString(createGeometryType(linearString));
	}
}
