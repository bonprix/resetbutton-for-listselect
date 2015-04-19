package org.vaadin.addons.resetbuttonforlistselect.demo;

import java.util.Arrays;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.resetbuttonforlistselect.ResetButtonForListSelect;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("Resetbutton for ListSelect Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.addons.resetbuttonforlistselect.demo.DemoWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(final VaadinRequest request) {

		final ListSelect listSelect = new ListSelect("Some ListSelect", Arrays.asList("Apple", "Orange", "Pineapple", "Coconut", "Strawberry", "Mango", "Kiwi",
		        "Banana", "Water Melon", "Lemon fruit", "Date"));
		listSelect.setWidth(200, Unit.PIXELS);
		listSelect.setHeight(200, Unit.PIXELS);
		listSelect.setMultiSelect(true);
		listSelect.setStyleName("test1");

		ResetButtonForListSelect.extend(listSelect);

		final Label valueLabel = new Label();
		valueLabel.setCaption("ListSelect server-side value:");
		valueLabel.setWidth(200, Unit.PIXELS);

		listSelect.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(final ValueChangeEvent event) {
				final String valueString = listSelect.getValue() == null ? "no selection" : listSelect.getValue().toString();

				valueLabel.setValue(valueString);
			}
		});

		// Show it in the middle of the screen
		final VerticalLayout layout = new VerticalLayout();
		layout.setStyleName("demoContentLayout");
		layout.setSizeFull();
		layout.addComponent(listSelect);
		layout.addComponent(valueLabel);
		layout.setComponentAlignment(listSelect, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(valueLabel, Alignment.MIDDLE_CENTER);
		setContent(layout);

	}
}
