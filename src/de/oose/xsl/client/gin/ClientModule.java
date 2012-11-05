package de.oose.xsl.client.gin;

import hu.szaboaz.gwt.xslt.client.XsltProcessor;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

import de.oose.xsl.client.XslPresenter;
import de.oose.xsl.client.XslView;
import de.oose.xsl.client.place.ClientPlaceManager;
import de.oose.xsl.client.place.DefaultPlace;
import de.oose.xsl.client.place.NameTokens;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindPresenter(XslPresenter.class, XslPresenter.MyView.class,
				XslView.class, XslPresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.xslview);
		
		bind(XsltProcessor.class);
	}
}
