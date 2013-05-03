package com.pushpop.client.view.desktop;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.pushpop.client.view.resources.Resources;

public class MasterLayoutPanel extends Composite {

    private static final DockLayoutPanel dockPanel = new DockLayoutPanel(Unit.PX);

    private final EventBus eventBus;

    private SimpleLayoutPanel headerPanel = new SimpleLayoutPanel();

    private SimpleLayoutPanel contentPanel = new SimpleLayoutPanel();

    private SimpleLayoutPanel footerPanel = new SimpleLayoutPanel();

    public MasterLayoutPanel(EventBus eventBus, Resources resources) {
        this.eventBus = eventBus;
        initWidget(dockPanel);

        setStyleName(resources.style().masterLayoutPanel());
        
        headerPanel.setStyleName(resources.style().headerPanel());
        contentPanel.setStyleName(resources.style().contentPanel());
<<<<<<< HEAD
        contentPanel.addStyleName("container");
=======
>>>>>>> 9629e6d9c7b32e65882d3bcb6996414b8ba5ea22
       // footerPanel.setStyleName(resources.style().footerPanel());

        dockPanel.addNorth(headerPanel, 120);
       // dockPanel.addSouth(footerPanel, 140);
        dockPanel.add(contentPanel);
    }

    public SimpleLayoutPanel getHeaderPanel() {
        return headerPanel;
    }

    public SimpleLayoutPanel getContentPanel() {
        return contentPanel;
    }

    public SimpleLayoutPanel getFooterPanel() {
        return footerPanel;
    }
}