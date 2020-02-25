package org.jvnet.ogc.gml.v_3_2_1.jts.adapter;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.opengis.gml.v_3_2_1.PointPropertyType;
import net.opengis.gml.v_3_2_1.PointType;

import org.jvnet.ogc.gml.v_3_2_1.ObjectFactoryInterface;
import org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts.GML321ToJTSConverterInterface;
import org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts.GML321ToJTSCoordinateConverter;
import org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts.GML321ToJTSPointConverter;
import org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts.GML321ToJTSSRIDConverterInterface;
import org.jvnet.ogc.gml.v_3_2_1.jts.jts2gml.JTSToGML321ConverterInterface;
import org.jvnet.ogc.gml.v_3_2_1.jts.jts2gml.JTSToGML321CoordinateConverter;
import org.jvnet.ogc.gml.v_3_2_1.jts.jts2gml.JTSToGML321PointConverter;
import org.jvnet.ogc.gml.v_3_2_1.jts.jts2gml.JTSToGML321SRSReferenceGroupConverterInterface;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;


public abstract class AbstractPointDomHandler extends
		AbstractGeometryDomHandler<PointType, PointPropertyType, Point> {

	public AbstractPointDomHandler() throws JAXBException {
		super();
	}

	public AbstractPointDomHandler(Marshaller marshaller,
			Unmarshaller unmarshaller) throws JAXBException {
		super(marshaller, unmarshaller);
	}

	public AbstractPointDomHandler(Marshaller marshaller) throws JAXBException {
		super(marshaller);
	}

	public AbstractPointDomHandler(Unmarshaller unmarshaller)
			throws JAXBException {
		super(unmarshaller);
	}

	@Override
	protected Class<Point> getGeometryClass() {
		return Point.class;
	}

	@Override
	protected Class<PointPropertyType> getPropertyType() {
		return PointPropertyType.class;
	}

	@Override
	protected Class<PointType> getGeometryType() {
		return PointType.class;
	}

	@Override
	protected JTSToGML321ConverterInterface<PointType, PointPropertyType, Point> createMarshallerConverter(
			ObjectFactoryInterface objectFactory,
			JTSToGML321SRSReferenceGroupConverterInterface srsReferenceGroupConverter) {
		return new JTSToGML321PointConverter(objectFactory,
				srsReferenceGroupConverter, new JTSToGML321CoordinateConverter(
						objectFactory, srsReferenceGroupConverter));
	}

	@Override
	protected GML321ToJTSConverterInterface<PointType, PointPropertyType, Point> createUnmarshallerConverter(
			GeometryFactory geometryFactory,
			GML321ToJTSSRIDConverterInterface sridConverter) {
		return new GML321ToJTSPointConverter(geometryFactory, sridConverter,
				new GML321ToJTSCoordinateConverter());
	}
}
