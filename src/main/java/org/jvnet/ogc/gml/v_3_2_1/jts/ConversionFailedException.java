package org.jvnet.ogc.gml.v_3_2_1.jts;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

public class ConversionFailedException extends Exception {

	private static final long serialVersionUID = 1L;
	private final ObjectLocator locator;

	public ConversionFailedException(final ObjectLocator locator, final String message) {

		super(message);
		this.locator = locator;
	}

	public ConversionFailedException(final ObjectLocator locator, final Throwable throwable) {

		super(throwable);
		this.locator = locator;
	}

	public ConversionFailedException(final ObjectLocator locator,
									 final String message,
									 final Throwable throwable) {

		super(message, throwable);
		this.locator = locator;
	}

	public ConversionFailedException(final ObjectLocator locator) {
		super();
		this.locator = locator;
	}

	public ObjectLocator getLocator() {
		return locator;
	}

}
