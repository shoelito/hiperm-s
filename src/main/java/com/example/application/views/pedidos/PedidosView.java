package com.example.application.views.pedidos;

import com.example.application.views.agregarpedidos.AgregarPedidosView;
import com.example.application.data.Pedidos;
import com.example.application.services.PedidosService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import java.util.Objects;

@PageTitle("Pedidos")
@Route("Pedidos")
@Menu(order = 1, icon = LineAwesomeIconUrl.SHOPPING_BASKET_SOLID)
@Uses(Icon.class)
public class PedidosView extends Composite<VerticalLayout> {

    public PedidosView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();

        Grid<Pedidos> gridPedidos = new Grid<>(Pedidos.class);

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Agregar pedido");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPrimary.addClickListener(event -> {
            UI.getCurrent().navigate(AgregarPedidosView.class);
        });

        buttonSecondary.setText("Deshacer ultimo pedido");
        buttonSecondary.setWidth("min-content");

        gridPedidos.setWidth("100%");
        gridPedidos.getStyle().set("flex-grow", "0");
        setGridSampleData(gridPedidos);

        getContent().add(layoutRow);
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonSecondary);
        getContent().add(gridPedidos);
    }

    private void setGridSampleData(Grid<Pedidos> gridPedidos) {
        gridPedidos.setItems(query -> pedidosService.list(
                Objects.requireNonNull(VaadinSpringDataHelpers.toSpringPageRequest(query))).stream());
    }

    @Autowired()
    private PedidosService pedidosService;
}
