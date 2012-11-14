package de.oose.xsl.shared;

import java.io.Serializable;

public class XSLDocument implements Serializable {

	private String xsl;
	private String xml;
	
	public XSLDocument() {
	}
	
	public XSLDocument(String xsl, String xml) {
		super();
		this.xsl = xsl;
		this.xml = xml;
	}

	public String getXsl() {
		return xsl;
	}
	public void setXsl(String xsl) {
		this.xsl = xsl;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	
	

}
