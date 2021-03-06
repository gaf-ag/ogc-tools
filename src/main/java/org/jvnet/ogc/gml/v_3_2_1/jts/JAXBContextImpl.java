package org.jvnet.ogc.gml.v_3_2_1.jts;

import net.opengis.gml.v_3_2_1.GeometryPropertyType;
import org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts.GML321ToJTSConverterInterface;
import org.jvnet.ogc.gml.v_3_2_1.jts.jts2gml.JTSToGML321ConverterInterface;
import org.locationtech.jts.geom.Geometry;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Validator;


public class JAXBContextImpl extends javax.xml.bind.JAXBContext {

	private final javax.xml.bind.JAXBContext context;
	private final JTSToGML321ConverterInterface<Object, GeometryPropertyType, Geometry> marshallerConverter;
	private final GML321ToJTSConverterInterface<Object, Object, Geometry> unmarshallerConverter;

	public JAXBContextImpl(
			javax.xml.bind.JAXBContext context,
			JTSToGML321ConverterInterface<Object, GeometryPropertyType, Geometry> marshallerConverter,
			GML321ToJTSConverterInterface<Object, Object, Geometry> unmarshallerConverter) {
		super();
		this.context = context;
		this.marshallerConverter = marshallerConverter;
		this.unmarshallerConverter = unmarshallerConverter;
	}

	@Override
	public Unmarshaller createUnmarshaller() throws JAXBException {
		return new UnmarshallerImpl(context.createUnmarshaller(),
				unmarshallerConverter);
	}

	@Override
	public Marshaller createMarshaller() throws JAXBException {
		return new MarshallerImpl(context.createMarshaller(),
				marshallerConverter);
	}

	@Override
	public Validator createValidator() throws JAXBException {
		throw new UnsupportedOperationException();
	}

}
