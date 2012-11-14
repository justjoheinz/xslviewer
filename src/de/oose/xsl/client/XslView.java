package de.oose.xsl.client;

import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class XslView extends ViewWithUiHandlers<XslViewUiHandler> implements
		XslPresenter.MyView {

	private final Widget widget;
	@UiField
	TextArea input; 
	@UiField
	SpanElement output;
	@UiField
	TextArea xsl;
	@UiField 
	SpanElement raw;
	@UiField
	Modal m;
	@UiField
	NavLink about;
	

	public interface Binder extends UiBinder<Widget, XslView> {
	}

	@Inject
	public XslView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		bind();
	}

	private void bind() {

	}
	
	@UiHandler("saveButton")
	public void onSave(ClickEvent e) {
		getUiHandlers().onSave();
	}
	
	@UiHandler("resetButton")
	public void onReset(ClickEvent e) {
		getUiHandlers().onResetPressed();
	}
	
	@UiHandler("outputTab")
	public void onOutputClick(ClickEvent e) {
		getUiHandlers().onTransform();
	}
	
	@UiHandler("rawTab")
	public void onRawClick(ClickEvent e) {
		getUiHandlers().onTransform();
	}
	
	@UiHandler("about")
	public void onAboutClick(ClickEvent e) {
		m.show();
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public String getXML() {
		return input.getValue();
	}
	
	public void setXML(String xml) {
		input.setValue(xml);
	}

	@Override
	public String getXSL() {
		return xsl.getValue();
	}
	
	public void setXSL(String doc) {
		xsl.setValue(doc);
	}

	@Override
	public void setOutput(String newText) {
		this.output.setInnerHTML(newText);
	}

	@Override
	public void setRaw(String output) {
		this.raw.setInnerText(output);
		
	}

}
