package org.jvnet.ogc.gml.v_3_2_1.jts.tests;

import junit.framework.Assert;
import net.opengis.gml.v_3_2_1.AbstractGeometryType;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.ogc.gml.v_3_2_1.ObjectFactory;
import org.jvnet.ogc.gml.v_3_2_1.jts.jts2gml.JTSToGML321GeometryConverter;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;


public class JTSToGML321ConverterTest {

	private final GeometryFactory geometryFactory = new GeometryFactory();

	private JAXBContext context;
	private JTSToGML321GeometryConverter converter;

	@Before
	public void setUp() throws Exception {
		context = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
		converter = new JTSToGML321GeometryConverter();
	}

	private String marshal(Object object) throws JAXBException {
		final StringWriter stringWriter = new StringWriter();
		final Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(object, new StreamResult(stringWriter));
		return stringWriter.toString();
	}

	@Test
	public void testPoint1() throws Exception {

		final Point point = geometryFactory.createPoint(new Coordinate(10, 20));
		point.setSRID(4326);
		final JAXBElement<? extends AbstractGeometryType> element = converter.createElement(point);
		Assert.assertEquals("urn:ogc:def:crs:EPSG::4326", element.getValue().getSrsName());
		System.out.println("XML: " + marshal(element));
	}

	@Test
	public void testPoint2() throws Exception {

		final Point point = geometryFactory.createPoint(new Coordinate(10, 20));
		point.setUserData("urn:ogc:def:crs:EPSG::4326");
		final JAXBElement<? extends AbstractGeometryType> element = converter.createElement(point);
		Assert.assertEquals("urn:ogc:def:crs:EPSG::4326", element.getValue().getSrsName());
		System.out.println("XML: " + marshal(element));
	}

	@Test
	public void testPoint3() throws Exception {

		final Point point = geometryFactory.createPoint(new Coordinate(10, 20));
		point.setUserData("urn:ogc:def:crs:OGC:1.3:CRS1");
		final JAXBElement<? extends AbstractGeometryType> element = converter.createElement(point);
		Assert.assertEquals("urn:ogc:def:crs:OGC:1.3:CRS1", element.getValue().getSrsName());
		System.out.println("XML: " + marshal(element));
	}

	@Test
	public void testPoint4() throws Exception {

		final Point point = geometryFactory.createPoint(new Coordinate(10, 20));
		final JAXBElement<? extends AbstractGeometryType> element = converter.createElement(point);
		Assert.assertEquals(null, element.getValue().getSrsName());
		System.out.println("XML: " + marshal(element));
	}
}
