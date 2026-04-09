package com.example.application.views.inicio;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.UI;
import com.example.application.views.inventario.InventarioView;
import com.example.application.views.pedidos.PedidosView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Inicio")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID)
public class InicioView extends Composite<VerticalLayout> {

    public InicioView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutColumn3 = new HorizontalLayout();
        H1 h1 = new H1();
        Button buttonPrimary = new Button();
        Button buttonPedidos = new Button();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");

        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");

        h1.setText("Bienvenido a HIPERmás");
        h1.setWidth("max-content");

        buttonPrimary.setText("Ver inventario");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPrimary.addClickListener(event -> {
            UI.getCurrent().navigate(InventarioView.class);
        });

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");

        h1.setText("Bienvenido a HIPERmás");
        h1.setWidth("max-content");

        buttonPedidos.setText("Ver pedidos");
        buttonPedidos.setWidth("min-content");
        buttonPedidos.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPedidos.addClickListener(event -> {
            UI.getCurrent().navigate(PedidosView.class);
        });

        getContent().add(layoutColumn2);
        layoutColumn2.add(h1);
        layoutColumn2.add(layoutColumn3);
        layoutColumn3.add(buttonPedidos);
        layoutColumn3.add(buttonPrimary);
    }
}
