package com.pushpop.client.view.widgets;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.TextArea;

public class DOMUtils {

	public static final KeyDownHandler DEFAULT_TEXTAREA_TAB_HANDLER = new KeyDownHandler() {
		@Override
		public final void onKeyDown(KeyDownEvent event) {
			if (event.getNativeKeyCode() == 9) {
				event.preventDefault();
				event.stopPropagation();
				final TextArea ta = (TextArea) event.getSource();
				final int index = ta.getCursorPos();
				final String text = ta.getText();
				ta.setText(text.substring(0, index) + "\t"
						+ text.substring(index));
				ta.setCursorPos(index + 1);
			}
		}
	};

}
