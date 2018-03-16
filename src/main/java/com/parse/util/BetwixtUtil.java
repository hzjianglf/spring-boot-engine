package com.parse.util;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.apache.commons.betwixt.XMLIntrospector;
import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.io.BeanWriter;
import org.xml.sax.InputSource;

public class BetwixtUtil {
	private XMLIntrospector introspector = null;
	
	private XMLIntrospector getIntrospector(Class<?> cls) throws Exception {
		if (introspector == null) {
			introspector = new XMLIntrospector();
			introspector.getConfiguration().setAttributesForPrimitives(true);
			introspector.register(new InputSource(cls.getResourceAsStream("betwixt-config.xml")));
		}
		
		return introspector;
	}
	
	public String write(Object obj) {
		try {
			StringWriter sWriter = new StringWriter();
			BeanWriter bWriter = new BeanWriter(sWriter);
			bWriter.setXMLIntrospector(getIntrospector(obj.getClass()));
			bWriter.getBindingConfiguration().setMapIDs(false);
			bWriter.write(obj);
			
			return sWriter.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T parse(String xml,Class<T> cls) {
		try {
			xml = xml.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
			Reader reader = new StringReader(xml);
			BeanReader bReader = new BeanReader();
			bReader.setXMLIntrospector(getIntrospector(cls));
			bReader.registerBeanClass(cls);
			
			return (T) bReader.parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}