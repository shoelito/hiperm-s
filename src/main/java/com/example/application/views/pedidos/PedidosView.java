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
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification;
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

    @SuppressWarnings("unused")
    public PedidosView(PedidosService pedidosService) {
        this.pedidosService = pedidosService;

        Grid<Pedidos> gridPedidos = new Grid<>();
        HorizontalLayout layoutRow = new HorizontalLayout();
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        getContent().add(layoutRow);

        // Boton para agregar un nuevo pedido
        Button btnAgregar = new Button();
        btnAgregar.setText("Agregar pedido");
        btnAgregar.setWidth("min-content");
        btnAgregar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnAgregar.addClickListener(event -> {
            UI.getCurrent().navigate(AgregarPedidosView.class);
        });
        // fin boton agregar

        // Boton para deshacer el ultimo pedido
        Button btnDeshacer = new Button();
        btnDeshacer.setText("Deshacer ultimo pedido");
        btnDeshacer.setWidth("min-content");
        btnDeshacer.addClickListener(event -> {
            boolean exito = pedidosService.deshacerUltimoAtendido();
            if (exito) {
                Notification.show("Se deshizo la entrega del último pedido.");
                gridPedidos.setItems(pedidosService.obtenerPedidosGrid());
            } else {
                Notification.show("No hay historial de pedidos para deshacer.");
            }
        });
        // fin boton deshacer

        // Boton para atender el siguiente pedido
        Button btnAtender = new Button();
        btnAtender.setText("Despachar pedido");
        btnAtender.setWidth("min-content");
        btnAtender.addClickListener(event -> {
            Pedidos atendido = pedidosService.atenderSiguiente();
            if (atendido != null) {
                Notification.show("Pedido #" + atendido.getId() + " completado.");
                gridPedidos.setItems(pedidosService.obtenerPedidosGrid());
            } else {
                Notification.show("No hay pedidos pendientes.");
            }
        });

        btnAtender.addClickListener(event -> {
            pedidosService.atenderSiguiente();
            setGridData(gridPedidos);
        });
        // fin boton atender

        // Grid de pedidos
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

        gridPedidos.addColumn(Pedidos::getPrioridad).setHeader("Prioridad");
        gridPedidos.addColumn(Pedidos::getEstado).setHeader("Estado");
        gridPedidos.setWidth("100%");
        gridPedidos.getStyle().set("flex-grow", "0");
        setGridData(gridPedidos);
        // fin grid de pedidos

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        layoutRow.add(btnAgregar);
        layoutRow.add(btnDeshacer);
        layoutRow.add(btnAtender);
        getContent().add(gridPedidos);
    }

    private void setGridData(Grid<Pedidos> gridPedidos) {
        gridPedidos.setItems(pedidosService.obtenerPedidosGrid());
    }
}
