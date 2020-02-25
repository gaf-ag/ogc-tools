package org.jvnet.ogc.gml.v_3_2_1.jts.jts2gml;

import net.opengis.gml.v_3_2_1.GeometryPropertyType;
import net.opengis.gml.v_3_2_1.MultiGeometryPropertyType;
import net.opengis.gml.v_3_2_1.MultiGeometryType;
import org.jvnet.ogc.gml.v_3_2_1.ObjectFactoryInterface;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;

import javax.xml.bind.JAXBElement;


public class JTSToGML321MultiGeometryConverter
		extends AbstractJTSToGML321Converter<MultiGeometryType, MultiGeometryPropertyType, GeometryCollection> {

	private final JTSToGML321ConverterInterface<Object, GeometryPropertyType, Geometry> geometryConverter;

	public JTSToGML321MultiGeometryConverter(
			ObjectFactoryInterface objectFactory,
			JTSToGML321SRSReferenceGroupConverterInterface srsReferenceGroupConverter,
			JTSToGML321ConverterInterface<Object, GeometryPropertyType, Geometry> geometryConverter) {
		super(objectFactory, srsReferenceGroupConverter);
		this.geometryConverter = geometryConverter;
	}

	@Override
	protected MultiGeometryType doCreateGeometryType(final GeometryCollection multiGeometry) {

		final MultiGeometryType multiGeometryType = getObjectFactory()
				.createMultiGeometryType();

		for (int index = 0; index < multiGeometry.getNumGeometries(); index++) {
			final Geometry geometry = multiGeometry.getGeometryN(index);

			multiGeometryType.getGeometryMember().add(
					geometryConverter.createPropertyType(geometry));
		}
		return multiGeometryType;
	}

	public MultiGeometryPropertyType createPropertyType(final GeometryCollection multiGeometry) {

		final MultiGeometryPropertyType multiGeometryPropertyType = getObjectFactory()
				.createMultiGeometryPropertyType();
		multiGeometryPropertyType.setAbstractGeometricAggregate(createElement(multiGeometry));
		return multiGeometryPropertyType;
	}

	public JAXBElement<MultiGeometryType> createElement(final  GeometryCollection geometryCollection) {

		return getObjectFactory().createMultiGeometry(
				createGeometryType(geometryCollection));
	}
}
