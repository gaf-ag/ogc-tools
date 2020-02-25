package org.jvnet.ogc.gml.v_3_2_1.jts.adapter;

import org.locationtech.jts.geom.Point;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class PointTypeDomHandler extends AbstractPointDomHandler {

	public PointTypeDomHandler() throws JAXBException {
		super();
	}

	public PointTypeDomHandler(final Marshaller marshaller, final Unmarshaller unmarshaller)
			throws JAXBException {
		super(marshaller, unmarshaller);
	}

	public PointTypeDomHandler(final Marshaller marshaller) throws JAXBException {
		super(marshaller);
	}

	public PointTypeDomHandler(final Unmarshaller unmarshaller) throws JAXBException {
		super(unmarshaller);
	}
	
	@Override
	public Point getElement(final DOMResultEx result) {
		return unmarshalGeometryType(result);
	}

}