/**
 *
 */
package org.vaadin.addons.resetbuttonforlistselect.client;

import org.vaadin.addons.resetbuttonforlistselect.ResetButtonForListSelect;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VListSelect;
import com.vaadin.shared.ui.Connect;

/**
 * @author cthiel
 *
 */
@Connect(ResetButtonForListSelect.class)
public class ResetButtonForListSelectConnector extends AbstractExtensionConnector implements AttachEvent.Handler {
	public static final String CLASSNAME = "resetbuttonforlistselect";

	private VListSelect listSelect;
	private Element resetButtonElement;

	private final ResetButtonForListSelectServerRpc rpc = RpcProxy.create(ResetButtonForListSelectServerRpc.class, this);

	@Override
	protected void extend(final ServerConnector target) {
		this.listSelect = (VListSelect) ((ComponentConnector) target).getWidget();
		this.listSelect.addStyleName(ResetButtonForListSelectConnector.CLASSNAME + "-listselect");

		this.resetButtonElement = DOM.createDiv();
		this.resetButtonElement.addClassName(ResetButtonForListSelectConnector.CLASSNAME + "-resetbutton");
		DOM.sinkEvents(this.resetButtonElement, Event.ONCLICK);
		DOM.setEventListener(this.resetButtonElement, new EventListener() {

			@Override
			public void onBrowserEvent(final Event event) {
				if (DOM.eventGetType(event) == Event.ONCLICK) {
					ResetButtonForListSelectConnector.this.rpc.onClear();
				}
			}
		});

		this.listSelect.addAttachHandler(this);
	}

	@Override
	public void onAttachOrDetach(final AttachEvent event) {
		if (event.isAttached()) {
			Element e = this.listSelect.getElement().getParentElement().getFirstChildElement();
			while (e != null) {
				if (e.hasClassName("v-caption")) {
					e.addClassName(ResetButtonForListSelectConnector.CLASSNAME + "-caption");
					e.appendChild(this.resetButtonElement);
					return;
				}
				e = e.getNextSiblingElement();
			}

		} else {
			this.resetButtonElement.removeFromParent();
		}
	}
}
