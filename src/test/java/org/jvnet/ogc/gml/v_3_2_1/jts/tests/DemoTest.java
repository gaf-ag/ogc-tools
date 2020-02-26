package org.jvnet.ogc.gml.v_3_2_1.jts.tests;

import org.junit.Test;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.WKTWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class DemoTest {

	@Test
	public void demonstrateContext() throws JAXBException {

		final JAXBContext context = JAXBContext.newInstance("org.jvnet.ogc.gml.v_3_2_1.jts");
		final WKTWriter wktWriter = new WKTWriter();
		
		// Unmarshal
		Point point = (Point) context.createUnmarshaller().unmarshal(
				getClass().getResource("Point1.xml"));
		
		Polygon polygon = (Polygon) context.createUnmarshaller().unmarshal(
				getClass().getResource("Polygon1.xml"));
		
		// Marshal
		StringWriter stringWriter = new StringWriter();
		context.createMarshaller().marshal(point, stringWriter);
		String pointGml = stringWriter.toString();
		System.out.println("Point GML: " + pointGml);

		String pointWkt = wktWriter.write(point);
		assertEquals("POINT (17 4)", pointWkt);
		System.out.println("Point WKT: " + pointWkt);
	}
}
