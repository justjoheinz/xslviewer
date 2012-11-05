package de.oose.xsl.client;

import com.gwtplatform.mvp.client.UiHandlers;

public interface XslViewUiHandler extends UiHandlers {
	
	void onTransform();
	void onSave();
	void onResetPressed();

}
