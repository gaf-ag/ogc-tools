package org.jvnet.ogc.gml.v_3_2_1.jts.tests;

import net.opengis.gml.v_3_2_1.PointType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.jaxb2_commons.locator.DefaultRootObjectLocator;
import org.jvnet.ogc.gml.v_3_2_1.jts.ConversionFailedException;
import org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts.GML321ToJTSConstants;
import org.jvnet.ogc.gml.v_3_2_1.jts.gml2jts.GML321ToJTSSRIDConverterInterface;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import static org.junit.Assert.assertEquals;


public class GML321ToJTSSRIDConverterTest {

	private GeometryFactory geometryFactory;
	private GML321ToJTSSRIDConverterInterface converter;

	@Before
	public void setUp() throws Exception {
		geometryFactory = GML321ToJTSConstants.DEFAULT_GEOMETRY_FACTORY;
		converter = GML321ToJTSConstants.DEFAULT_SRID_CONVERTER;
	}

	private void check(String srsName, int srid, Object userData)
			throws ConversionFailedException {
		final PointType source = new PointType();
		source.setSrsName(srsName);
		Geometry target = geometryFactory.createPoint(new Coordinate(0, 0));
		converter.convert(new DefaultRootObjectLocator(source), source, target);
		assertEquals(target.getSRID(), srid);
		assertEquals(target.getUserData(), userData);
	}

	@Test
	public void testConvert() throws ConversionFailedException {
		check("http://www.opengis.net/gml/srs/epsg.xml#4326", 4326,
				"http://www.opengis.net/gml/srs/epsg.xml#4326");
		check("EPSG:4326", 4326, "EPSG:4326");
		check("urn:ogc:def:crs:EPSG::4326", 4326, "urn:ogc:def:crs:EPSG::4326");
		check("urn:ogc:def:crs:EPSG:6.3:4326", 4326,
				"urn:ogc:def:crs:EPSG:6.3:4326");
		check("urn:x-ogc:def:crs:EPSG::4326", 4326,
				"urn:x-ogc:def:crs:EPSG::4326");
		check("urn:x-ogc:def:crs:EPSG:6.3:4326", 4326,
				"urn:x-ogc:def:crs:EPSG:6.3:4326");
	}

	@Test
	public void testConvertWithCorrectPoint() throws ConversionFailedException {

		Geometry point = geometryFactory.createPoint(new Coordinate(0, 0));

		final PointType source = new PointType();
		int srid = 63266405;

		source.setSrsName("http://www.opengis.net/gml/srs/epsg.xml#63266405");

		converter.convert(new DefaultRootObjectLocator(source), source, point);
		assertEquals(srid, point.getSRID());

	}

	@Test
	public void testConvertWithWrongFormatAndTargetUserDataNull()
			throws ConversionFailedException {
		Geometry point = geometryFactory.createPoint(new Coordinate(0, 0));

		final PointType source = new PointType();

		source.setSrsName("foo");

		converter.convert(new DefaultRootObjectLocator(source), source, point);
		assertEquals("foo", point.getUserData());

	}

	@Test
	public void testConvertWithWrongFormatAndTargetUserDataNotNull()
			throws ConversionFailedException {
		Geometry point = geometryFactory.createPoint(new Coordinate(0, 0));
		point.setUserData("");
		final PointType source = new PointType();

		source.setSrsName("foo");

		try {
			converter.convert(new DefaultRootObjectLocator(source), source,
					point);
			Assert.fail();
		} catch (ConversionFailedException e) {
			Assert.assertTrue(true);
		}

	}

}
