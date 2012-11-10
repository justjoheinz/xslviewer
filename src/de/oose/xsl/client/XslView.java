package de.oose.xsl.client;

import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class XslView extends ViewWithUiHandlers<XslViewUiHandler> implements
		XslPresenter.MyView {

	private static final int OUTPUT_TAB = 2;
	private static final int RAW_TAB = 3;
	private final Widget widget;
	@UiField
	TabLayoutPanel tab;
	@UiField
	TextArea input; 
	@UiField
	SpanElement output;
	@UiField
	TextArea xsl;
	@UiField 
	SpanElement raw;

	public interface Binder extends UiBinder<Widget, XslView> {
	}

	@Inject
	public XslView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		bind();
	}

	private void bind() {
		tab.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				if (getUiHandlers() != null) {
					int selectedItem = event.getSelectedItem();
					switch (selectedItem) {
					case OUTPUT_TAB:
					case RAW_TAB:
						getUiHandlers().onTransform();
						break;
					default:
						break;
					}

				}
			}
		});

	}
	
	@UiHandler("saveButton")
	public void onSave(ClickEvent e) {
		getUiHandlers().onSave();
	}
	
	@UiHandler("resetButton")
	public void onReset(ClickEvent e) {
		getUiHandlers().onResetPressed();
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
