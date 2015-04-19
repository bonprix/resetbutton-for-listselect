package org.vaadin.addons.resetbuttonforlistselect.client;

import com.vaadin.shared.communication.ServerRpc;

public interface ResetButtonForListSelectServerRpc extends ServerRpc {

	void onClear();

}
