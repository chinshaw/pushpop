package com.pushpop.client.view.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class WikiEditor extends Composite implements HasText, LeafValueEditor<String>, TakesValue<String> {

	/**
	 * This {@link ClientBundle} is used for all the button icons. Using a
	 * bundle allows all of these images to be packed into a single image, which
	 * saves a lot of HTTP requests, drastically improving startup time.
	 */
	public static interface Images extends ClientBundle {

		ImageResource bold();

		ImageResource createLink();

		ImageResource hr();

		ImageResource indent();

		ImageResource insertImage();

		ImageResource italic();

		ImageResource justifyCenter();

		ImageResource justifyLeft();

		ImageResource justifyRight();

		ImageResource ol();

		ImageResource outdent();

		ImageResource removeFormat();

		ImageResource removeLink();

		ImageResource strikeThrough();

		ImageResource subscript();

		ImageResource superscript();

		ImageResource ul();

		ImageResource underline();
	}

	@UiTemplate("WikiEditor.ui.xml")
	interface Binder extends UiBinder<Widget, WikiEditor> {
	}

	@UiField
	TextArea wikiText;
	
	@UiField WikiTextViewer
	wikiTextViewer;


	public WikiEditor() {
		initWidget(GWT.<Binder> create(Binder.class).createAndBindUi(this));

		wikiText.addKeyDownHandler(DOMUtils.DEFAULT_TEXTAREA_TAB_HANDLER);
		
		wikiText.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				updateHTMLPreview();
			}
		});
	}

	/**
	 * Retrieves the wiki text.
	 */
	@Override
	public String getText() {
		return wikiText.getValue();
	}

	@Override
	public void setText(String text) {
		wikiText.setValue(text);
		updateHTMLPreview();
	}
	
	public void setReadOnly(boolean readOnly) {
		if (readOnly) {
			wikiText.setReadOnly(true);
		}
	}

	@Override
	public String getValue() {
		return getText();
	}

	@Override
	public void setValue(String value) {
		setText(value);
	}
	
	private void updateHTMLPreview() {
		wikiTextViewer.setText(wikiText.getValue());
	}
}