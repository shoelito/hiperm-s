package com.example.application.views.agregarpedidos;

import com.example.application.data.Inventario;
import com.example.application.data.Pedidos;
import com.example.application.services.InventarioService;
import com.example.application.services.PedidosService;
import com.example.application.views.MainLayout;
import com.example.application.views.pedidos.PedidosView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.util.Map;
import java.util.List;

@PageTitle("Agregar Pedidos")
@Route(value = "Pedidos/Agregar", layout = MainLayout.class)
@Uses(Icon.class)
public class AgregarPedidosView extends Composite<VerticalLayout> {

    private final PedidosService pedidosService;
    private final InventarioService inventarioService;
    private Pedidos pedidoActual;

    /**
     * @return the pedidosService
     */
    public PedidosService getPedidosService() {
        return pedidosService;
    }

    /**
     * @return the pedidoActual
     */
    public Pedidos getPedidoActual() {
        return pedidoActual;
    }

    /**
     * @param pedidoActual the pedidoActual to set
     */
    public void setPedidoActual(Pedidos pedidoActual) {
        this.pedidoActual = pedidoActual;
    }

    public AgregarPedidosView(PedidosService pedidosService, InventarioService inventarioService) {
        this.pedidosService = pedidosService;
        this.inventarioService = inventarioService;
        this.pedidoActual = new Pedidos();

        HorizontalLayout topLayout = new HorizontalLayout();
        TextField txtNombreCliente = new TextField("Nombre del cliente");
        ComboBox<String> cmbPrioridad = new ComboBox<>("Prioridad");
        HorizontalLayout secondLayout = new HorizontalLayout();
        Button btnGuardarPedido = new Button("Guardar pedido");
        HorizontalLayout thirdLayout = new HorizontalLayout();
        ComboBox<String> cmbArticulo = new ComboBox<>("Artículo");
        NumberField txtCantidad = new NumberField("Cantidad");
        Button btnAgregarArticulo = new Button("Agregar artículo");
        Grid<Map.Entry<String, Integer>> basicGrid = new Grid<>();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        // primer layout
        topLayout.setWidthFull();
        getContent().setFlexGrow(1.0, topLayout);
        topLayout.addClassName(Gap.MEDIUM);
        topLayout.setAlignItems(Alignment.END);
        topLayout.setJustifyContentMode(JustifyContentMode.START);
        topLayout.setFlexGrow(1.0, secondLayout);
        // fin del primer layout

        // ComboBox prioridad
        cmbPrioridad.setWidth("min-content");
        cmbPrioridad.setItems("Urgente", "Normal");
        // fin del ComboBox prioridad

        // TextField nombre del cliente
        txtNombreCliente.setWidth("min-content");
        // fin del TextField nombre del cliente

        // segundo layout
        secondLayout.setHeightFull();
        secondLayout.addClassName(Gap.MEDIUM);
        secondLayout.setWidth("100%");
        secondLayout.getStyle().set("flex-grow", "1");
        secondLayout.setAlignItems(Alignment.START);
        secondLayout.setJustifyContentMode(JustifyContentMode.END);
        // fin del segundo layout

        // tercer layout
        thirdLayout.setWidthFull();
        getContent().setFlexGrow(1.0, thirdLayout);
        thirdLayout.addClassName(Gap.MEDIUM);
        thirdLayout.setWidth("100%");
        thirdLayout.setHeight("min-content");
        thirdLayout.setAlignItems(Alignment.END);
        thirdLayout.setJustifyContentMode(JustifyContentMode.START);
        // fin del tercer layout

        // Boton guardar pedido
        btnGuardarPedido.setWidth("min-content");
        btnGuardarPedido.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnGuardarPedido.addClickListener(e -> {
            String cliente = txtNombreCliente.getValue();
            String prioridad = cmbPrioridad.getValue();

            if (cliente == null || cliente.isEmpty() || prioridad == null) {
                Notification.show("Ingresa el cliente y la prioridad.");
                return;
            }
            if (pedidoActual.getArticulos().isEmpty()) {
                Notification.show("El pedido debe tener al menos un artículo.");
                return;
            }

            for (Map.Entry<String, Integer> item : pedidoActual.getArticulos().entrySet()) {
                String nombreProd = item.getKey();
                int cantidadARestar = item.getValue();

                inventarioService.obtenerCatalogoCompleto().stream()
                        .filter(p -> p.getNombre().equals(nombreProd))
                        .findFirst()
                        .ifPresent(p -> {
                            inventarioService.actualizarStock(p.getCodigo(), -cantidadARestar,
                                    "Salida por Pedido de: " + cliente);
                        });
            }
            pedidoActual.setCliente(cliente);
            pedidoActual.setPrioridad(prioridad);
            pedidosService.agregarPedido(pedidoActual);

            Notification.show("¡Pedido guardado!");
            UI.getCurrent().navigate(PedidosView.class);
        });
        // fin del boton guardar pedido

        // ComboBox articulo
        cmbArticulo.setWidth("min-content");
        List<String> nombresProductosReales = inventarioService.obtenerCatalogoCompleto().stream()
                .map(Inventario::getNombre)
                .toList();
        cmbArticulo.setItems(nombresProductosReales);
        // fin del ComboBox articulo

        // NumberField cantidad
        txtCantidad.setWidth("min-content");
        txtCantidad.setMin(1);
        txtCantidad.setValue(1.0);
        // fin del NumberField cantidad

        // Boton agregar articulo
        btnAgregarArticulo.setWidth("min-content");
        btnAgregarArticulo.getStyle().set("cursor", "pointer");
        btnAgregarArticulo.addClickListener(e -> {
            String articuloNombre = cmbArticulo.getValue();
            Double cantidadPedidaDouble = txtCantidad.getValue();

            if (articuloNombre != null && cantidadPedidaDouble != null && cantidadPedidaDouble > 0) {
                int cantidadPedida = cantidadPedidaDouble.intValue();

                Inventario productoReal = inventarioService.obtenerCatalogoCompleto().stream()
                        .filter(p -> p.getNombre().equals(articuloNombre))
                        .findFirst()
                        .orElse(null);

                if (productoReal == null) {
                    Notification.show("Error: El producto no existe en el inventario.");
                    return;
                }

                int cantidadYaEnCarrito = pedidoActual.getArticulos().getOrDefault(articuloNombre, 0);
                int cantidadTotalRequerida = cantidadYaEnCarrito + cantidadPedida;

                if (cantidadTotalRequerida > productoReal.getStock()) {
                    Notification
                            .show("¡Stock insuficiente! Solo hay " + productoReal.getStock() + " de " + articuloNombre);
                } else {
                    pedidoActual.getArticulos().put(articuloNombre, cantidadTotalRequerida);
                    basicGrid.setItems(pedidoActual.getArticulos().entrySet());
                    Notification.show(articuloNombre + " agregado al pedido.");
                    cmbArticulo.clear();
                    txtCantidad.setValue(1.0);
                }
            } else {
                Notification.show("Selecciona un artículo y cantidad válida.");
            }
        });
        // fin del boton agregar articulo

        // Grid basico
        basicGrid.setWidth("100%");
        basicGrid.getStyle().set("flex-grow", "0");
        basicGrid.addColumn(Map.Entry::getKey).setHeader("Artículo");
        basicGrid.addColumn(Map.Entry::getValue).setHeader("Cantidad");
        // fin del Grid basico

        getContent().add(topLayout);
        topLayout.add(txtNombreCliente);
        topLayout.add(cmbPrioridad);
        topLayout.add(secondLayout);
        secondLayout.add(btnGuardarPedido);
        getContent().add(thirdLayout);
        thirdLayout.add(cmbArticulo);
        thirdLayout.add(txtCantidad);
        thirdLayout.add(btnAgregarArticulo);
        getContent().add(basicGrid);

    }
}