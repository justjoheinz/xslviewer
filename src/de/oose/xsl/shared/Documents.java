package de.oose.xsl.shared;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Documents implements Serializable {
	
	private Map<String, XSLDocument> docs = new HashMap<String, XSLDocument>();

	public void clear() {
		docs.clear();
	}

	public boolean containsKey(Object arg0) {
		return docs.containsKey(arg0);
	}

	public boolean containsValue(Object arg0) {
		return docs.containsValue(arg0);
	}

	public Set<Entry<String, XSLDocument>> entrySet() {
		return docs.entrySet();
	}

	public XSLDocument get(Object arg0) {
		return docs.get(arg0);
	}

	public boolean isEmpty() {
		return docs.isEmpty();
	}

	public Set<String> keySet() {
		return docs.keySet();
	}

	public XSLDocument put(String arg0, XSLDocument arg1) {
		return docs.put(arg0, arg1);
	}

	public void putAll(Map<? extends String, ? extends XSLDocument> arg0) {
		docs.putAll(arg0);
	}

	public XSLDocument remove(Object arg0) {
		return docs.remove(arg0);
	}

	public int size() {
		return docs.size();
	}

	public Collection<XSLDocument> values() {
		return docs.values();
	}
	
	

}
