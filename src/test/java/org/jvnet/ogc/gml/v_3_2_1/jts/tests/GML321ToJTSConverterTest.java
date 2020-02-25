package org.jvnet.ogc.gml.v_3_2_1.jts.tests;

import junit.framework.Assert;
import net.opengis.gml.v_3_2_1.ObjectFactory;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.jaxb2_commons.locator.DefaultRootObjectLocator;
import org.jvnet.ogc.gml.v_3_2_1.jts.ConversionFailedException;
import org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts.GML321ToJTSConverterInterface;
import org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts.GML321ToJTSGeometryConverter;
import org.locationtech.jts.geom.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.transform.stream.StreamSource;
import java.net.URL;

public class GML321ToJTSConverterTest {

	private static double DELTA = 0.1;
	private static final JAXBContext CONTEXT;
	static {
		try {
			CONTEXT = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
		} catch (JAXBException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	private GML321ToJTSConverterInterface<Object, Object, Geometry> converter;


	@Before
	public void setUp() {
		converter = new GML321ToJTSGeometryConverter();
	}

	private Geometry unmarshal(String resourceName) throws JAXBException, ConversionFailedException {
		final URL url = getClass().getResource(resourceName);
		final Object object = CONTEXT.createUnmarshaller().unmarshal(new StreamSource(url.toString()));
		final Object value = JAXBIntrospector.getValue(object);
		return converter.createGeometry(new DefaultRootObjectLocator(value), value);
	}

	@Test
	public void testPoint1() throws Exception {

		final Point point = (Point) unmarshal("Point1.xml");
		Assert.assertFalse(point.isEmpty());
		Assert.assertTrue(point.isValid());
		Assert.assertEquals(17, point.getX(), DELTA);
		Assert.assertEquals(4, point.getY(), DELTA);
	}

	@Test
	public void testPoint2() throws Exception {

		final Point point = (Point) unmarshal("Point2.xml");
		Assert.assertFalse(point.isEmpty());
		Assert.assertTrue(point.isValid());
		Assert.assertEquals(12345, point.getX(), DELTA);
		Assert.assertEquals(67890, point.getY(), DELTA);
		Assert.assertEquals(25833, point.getSRID());
	}

	@Test
	public void testLineString1() throws Exception {

		final LineString lineString = (LineString) unmarshal("LineString1.xml");
		Assert.assertFalse(lineString.isEmpty());
		Assert.assertTrue(lineString.isValid());
		Assert.assertEquals(4, lineString.getCoordinates().length);
		assertCoordinates(lineString.getCoordinateN(0), 0.0, 0.0);
		assertCoordinates(lineString.getCoordinateN(1), 1.0, 0.0);
		assertCoordinates(lineString.getCoordinateN(2), 1.0, 1.0);
		assertCoordinates(lineString.getCoordinateN(3), 0.0, 1.0);
	}

	@Test
	public void testLineString2() throws Exception {

		final LineString lineString = (LineString) unmarshal("LineString2.xml");
		Assert.assertFalse(lineString.isEmpty());
		Assert.assertTrue(lineString.isValid());
		Assert.assertEquals(4, lineString.getCoordinates().length);
		assertCoordinates(lineString.getCoordinateN(0), 0.0, 0.0);
		assertCoordinates(lineString.getCoordinateN(1), 1.0, 0.0);
		assertCoordinates(lineString.getCoordinateN(2), 1.0, 1.0);
		assertCoordinates(lineString.getCoordinateN(3), 0.0, 1.0);
	}

	@Test
	public void testLineString3() throws Exception {

		final LineString lineString = (LineString) unmarshal("LineString3.xml");
		Assert.assertFalse(lineString.isEmpty());
		Assert.assertTrue(lineString.isValid());
		Assert.assertEquals(4, lineString.getCoordinates().length);
		assertCoordinates(lineString.getCoordinateN(0), 0.0, 0.0);
		assertCoordinates(lineString.getCoordinateN(1), 1.0, 0.0);
		assertCoordinates(lineString.getCoordinateN(2), 1.0, 1.0);
		assertCoordinates(lineString.getCoordinateN(3), 0.0, 1.0);
	}

	@Test
	public void testLineStrings() throws Exception {

		final LineString lineString1 = (LineString) unmarshal("LineString1.xml");
		final LineString lineString2 = (LineString) unmarshal("LineString2.xml");
		final LineString lineString3 = (LineString) unmarshal("LineString3.xml");
		Assert.assertTrue(lineString1.equals(lineString2));
		Assert.assertTrue(lineString2.equals(lineString3));
		Assert.assertTrue(lineString3.equals(lineString1));
	}

	@Test
	public void testLinearRing1() throws Exception {

		final LinearRing linearRing = (LinearRing) unmarshal("LinearRing1.xml");
		Assert.assertFalse(linearRing.isEmpty());
		Assert.assertTrue(linearRing.isValid());
		Assert.assertEquals(5, linearRing.getCoordinates().length);
		assertCoordinates(linearRing.getCoordinateN(0), 0.0, 0.0);
		assertCoordinates(linearRing.getCoordinateN(1), 1.0, 0.0);
		assertCoordinates(linearRing.getCoordinateN(2), 1.0, 1.0);
		assertCoordinates(linearRing.getCoordinateN(3), 0.0, 1.0);
		assertCoordinates(linearRing.getCoordinateN(4), 0.0, 0.0);
	}

	@Test
	public void testLinearRing2() throws Exception {

		final LinearRing linearRing = (LinearRing) unmarshal("LinearRing2.xml");
		Assert.assertFalse(linearRing.isEmpty());
		Assert.assertTrue(linearRing.isValid());
		Assert.assertEquals(5, linearRing.getCoordinates().length);
		assertCoordinates(linearRing.getCoordinateN(0), 0.0, 0.0);
		assertCoordinates(linearRing.getCoordinateN(1), 1.0, 0.0);
		assertCoordinates(linearRing.getCoordinateN(2), 1.0, 1.0);
		assertCoordinates(linearRing.getCoordinateN(3), 0.0, 1.0);
		assertCoordinates(linearRing.getCoordinateN(4), 0.0, 0.0);
	}

	@Test
	public void testLinearRings() throws Exception {

		final LinearRing linearRing0 = (LinearRing) unmarshal("LinearRing1.xml");
		final LinearRing linearRing1 = (LinearRing) unmarshal("LinearRing2.xml");
		Assert.assertTrue(linearRing0.equals(linearRing1));
	}

	@Test
	public void testPolygon1() throws Exception {
		final Polygon polygon = (Polygon) unmarshal("Polygon1.xml");
		Assert.assertEquals(5, polygon.getCoordinates().length);
		assertCoordinates(polygon.getCoordinates()[0], 0.0, 0.0);
		assertCoordinates(polygon.getCoordinates()[1], 1.0, 0.0);
		assertCoordinates(polygon.getCoordinates()[2], 1.0, 1.0);
		assertCoordinates(polygon.getCoordinates()[3], 0.0, 1.0);
		assertCoordinates(polygon.getCoordinates()[4], 0.0, 0.0);
	}

	@Test
	public void testPolygon2() throws Exception {
		final Polygon polygon = (Polygon) unmarshal("Polygon2.xml");
		Assert.assertEquals(5, polygon.getCoordinates().length);
		assertCoordinates(polygon.getCoordinates()[0], 0.0, 0.0);
		assertCoordinates(polygon.getCoordinates()[1], 1.0, 0.0);
		assertCoordinates(polygon.getCoordinates()[2], 1.0, 1.0);
		assertCoordinates(polygon.getCoordinates()[3], 0.0, 1.0);
		assertCoordinates(polygon.getCoordinates()[4], 0.0, 0.0);
	}

	@Test
	public void testPolygon3() throws Exception {

		final Polygon polygon = (Polygon) unmarshal("Polygon3.xml");
		final double area = polygon.getArea();
		Assert.assertEquals(3600, area, DELTA);
		Assert.assertEquals(10, polygon.getCoordinates().length);

		assertCoordinates(polygon.getExteriorRing().getCoordinateN(0), 0, 0);
		assertCoordinates(polygon.getExteriorRing().getCoordinateN(1), 100, 0);
		assertCoordinates(polygon.getExteriorRing().getCoordinateN(2), 100, 100);
		assertCoordinates(polygon.getExteriorRing().getCoordinateN(3), 0, 100);
		assertCoordinates(polygon.getExteriorRing().getCoordinateN(4), 0, 0);

		assertCoordinates(polygon.getInteriorRingN(0).getCoordinateN(0), 10.0, 10.0);
		assertCoordinates(polygon.getInteriorRingN(0).getCoordinateN(1), 90.0, 10.0);
		assertCoordinates(polygon.getInteriorRingN(0).getCoordinateN(2), 90.0, 90.0);
		assertCoordinates(polygon.getInteriorRingN(0).getCoordinateN(3), 10.0, 90.0);
		assertCoordinates(polygon.getInteriorRingN(0).getCoordinateN(4), 10.0, 10.0);
	}

	@Test
	public void testPolygons() throws Exception {

		final Polygon polygon0 = (Polygon) unmarshal("Polygon1.xml");
		final Polygon polygon1 = (Polygon) unmarshal("Polygon2.xml");
		Assert.assertTrue(polygon0.equals(polygon1));
	}


	private static void assertCoordinates(final Coordinate actual,
										  final double expectedX,
										  final double expectedY) {

		Assert.assertEquals(expectedX, actual.x, DELTA);
		Assert.assertEquals(expectedY, actual.y, DELTA);
	}
}
