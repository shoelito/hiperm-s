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
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Pedidos")
@Route("Pedidos")
@Menu(order = 1, icon = LineAwesomeIconUrl.SHOPPING_BASKET_SOLID)
@Uses(Icon.class)
public class PedidosView extends Composite<VerticalLayout> {

    private final PedidosService pedidosService;

    public PedidosView(PedidosService pedidosService) {
        this.pedidosService = pedidosService;

        HorizontalLayout layoutRow = new HorizontalLayout();
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        getContent().add(layoutRow);

        Button buttonPrimary = new Button();
        buttonPrimary.setText("Agregar pedido");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPrimary.addClickListener(event -> {
            UI.getCurrent().navigate(AgregarPedidosView.class);
        });

        Button buttonSecondary = new Button();
        buttonSecondary.setText("Deshacer ultimo pedido");
        buttonSecondary.setWidth("min-content");

        // Crear grid
        Grid<Pedidos> gridPedidos = new Grid<>();

        gridPedidos.addColumn(Pedidos::getId).setHeader("ID").setSortable(true);
        gridPedidos.addColumn(Pedidos::getCliente).setHeader("Cliente");

        gridPedidos.addComponentColumn(pedido -> {
            HorizontalLayout layout = new HorizontalLayout();
            layout.getStyle().set("flex-wrap", "wrap");
            layout.getStyle().set("gap", "4px");

            if (pedido.getArticulos() == null || pedido.getArticulos().isEmpty()) {
                layout.add(new Span("Sin artículos"));
                return layout;
            }

            pedido.getArticulos().forEach((articulo, cantidad) -> {
                Span badge = new Span(articulo + ": " + cantidad);
                badge.getElement().getThemeList().add("badge contrast");
                layout.add(badge);
            });

            return layout;
        }).setHeader("Artículos").setAutoWidth(true).setFlexGrow(1);

        gridPedidos.addColumn(Pedidos::getPrioridad).setHeader("Prioridad").setSortable(true);
        gridPedidos.addColumn(Pedidos::getEstado).setHeader("Estado").setSortable(true);
        gridPedidos.setWidth("100%");
        gridPedidos.getStyle().set("flex-grow", "0");
        setGridData(gridPedidos);

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonSecondary);
        getContent().add(gridPedidos);
    }

    private void setGridData(Grid<Pedidos> gridPedidos) {
        gridPedidos.setItems(pedidosService.findAll());
    }
}
