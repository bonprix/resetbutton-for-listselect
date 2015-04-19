/**
 *
 */
package org.vaadin.addons.resetbuttonforlistselect;

import org.vaadin.addons.resetbuttonforlistselect.client.ResetButtonForListSelectServerRpc;

import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.ListSelect;

/**
 * The {@link ResetButtonForListSelect} extension adds a reset button to the
 * caption div of the list select to make it resettable.
 *
 * @author cthiel
 *
 */
@SuppressWarnings("serial")
public class ResetButtonForListSelect extends AbstractExtension {
	public static void extend(final ListSelect field) {
		new ResetButtonForListSelect(field).extend((AbstractClientConnector) field);
	}

	private final ResetButtonForListSelectServerRpc rpc = new ResetButtonForListSelectServerRpc() {

		@Override
		public void onClear() {
			ResetButtonForListSelect.this.listSelect.setValue(null);

		}
	};
	private final ListSelect listSelect;

	public ResetButtonForListSelect(final ListSelect listSelect) {
		this.listSelect = listSelect;
		listSelect.addStyleName("resetbuttonforlistselect");

		registerRpc(this.rpc);
	}
}
