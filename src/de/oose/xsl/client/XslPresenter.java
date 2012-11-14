package de.oose.xsl.client;

import hu.szaboaz.gwt.xslt.client.XsltProcessingException;
import hu.szaboaz.gwt.xslt.client.XsltProcessor;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;

import de.oose.xsl.client.place.NameTokens;
import de.oose.xsl.shared.Documents;
import de.oose.xsl.shared.XSLDocument;

public class XslPresenter extends
		Presenter<XslPresenter.MyView, XslPresenter.MyProxy> implements
		XslViewUiHandler {
	
	@Inject
	private Documents docMap;

	public interface MyView extends View, HasUiHandlers<XslViewUiHandler> {
		public String getXML();
 
		public void setXML(String xml);

		public String getXSL();

		public void setXSL(String xsl);

		public void setOutput(String output);
		
		public void setRaw(String output);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.xslview)
	public interface MyProxy extends ProxyPlace<XslPresenter> {
	}

	private XsltProcessor processor;

	private Storage documentStorage;

	@Inject
	public XslPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, XsltProcessor processor) {
		super(eventBus, view, proxy);
		getView().setUiHandlers(this);
		this.processor = processor;
	}

	@Override
	protected void revealInParent() {
		RevealRootLayoutContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		retrieveStorage();
	}

	private void retrieveStorage() {
		documentStorage = Storage.getLocalStorageIfSupported();
		if (documentStorage == null) {
			return;
		}
		StorageMap map = new StorageMap(documentStorage);
		if (map.containsKey("xsl")) {
			getView().setXSL(map.get("xsl"));
		}
		if (map.containsKey("xml")) {
			getView().setXML(map.get("xml"));
		}
		doTransform();
	}

	@Override
	public void onTransform() {
		doTransform();
	}

	@Override
	public void onSave() {
		doSave();
	}
	
	@Override
	public void onResetPressed() {
		doReset();
	}

	private void doSave() {
		if (documentStorage != null) {
			String xsl = getView().getXSL();
			String xml = getView().getXML();
			docMap.put("default", new XSLDocument(xsl,xml));
			documentStorage.setItem("xsl", xsl);
			documentStorage.setItem("xml",xml);
		}
	}
	
	private void doReset() {
		if (documentStorage != null) {
			documentStorage.clear();
			Window.Location.reload();
		}
		
	}

	private void doTransform() {
		// Setting the stylesheet to transform with
		try {
			processor.importStyleSheet(getView().getXSL());
			processor.importSource(getView().getXML());
			// Getting the result
			String resultString = processor.transform();
			getView().setOutput(resultString);
			getView().setRaw(resultString);
		} catch (XsltProcessingException e) {
			getView().setOutput(e.getMessage());
		}
	}

}
