package com.example.application.views.inventario;

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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.data.Inventario;
import com.example.application.services.InventarioService;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.util.List;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.example.application.views.MainLayout;
import com.example.application.views.agregarproducto.AgregarProductoView;

@PageTitle("Inventario")
@Route(value = "Inventario", layout = MainLayout.class)
@Menu(order = 2, icon = LineAwesomeIconUrl.STORE_SOLID)
@Uses(Icon.class)

public class InventarioView extends Composite<VerticalLayout> {

    private InventarioService inventarioService;

    public InventarioView(InventarioService inventarioService) {
        this.inventarioService = inventarioService;

        HorizontalLayout firstLayout = new HorizontalLayout();
        TextField txtNombreProducto = new TextField();
        HorizontalLayout secondLayout = new HorizontalLayout();
        TextField txtCodigoProducto = new TextField();
        HorizontalLayout thirdLayout = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        Button btnBuscar = new Button();
        Button btnStockCritico = new Button();
        Button btnAgregarProducto = new Button();
        Button btnLimpiarFiltros = new Button("Limpiar");
        HorizontalLayout fourthLayout = new HorizontalLayout();
        HorizontalLayout fifthLayout = new HorizontalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        Grid<Inventario> basicGrid = new Grid<>();

        // Configuración del layout principal
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        // Configuración del primer layout
        firstLayout.setWidthFull();
        getContent().setFlexGrow(1.0, firstLayout);
        firstLayout.addClassName(Gap.MEDIUM);
        firstLayout.setWidth("100%");
        firstLayout.setHeight("min-content");

        // Configuración del TextField nombre del producto
        txtNombreProducto.setLabel("Nombre del producto");
        txtNombreProducto.setWidth("min-content");

        // Configuración del segundo layout
        secondLayout.setHeightFull();
        firstLayout.setFlexGrow(1.0, secondLayout);
        secondLayout.addClassName(Gap.MEDIUM);
        secondLayout.setWidth("100%");
        secondLayout.getStyle().set("flex-grow", "1");

        // Configuración del TextField codigo del producto
        txtCodigoProducto.setLabel("Codigo del producto");
        txtCodigoProducto.setWidth("min-content");

        // Configuración del tercer layout
        thirdLayout.setHeightFull();
        secondLayout.setFlexGrow(1.0, thirdLayout);
        thirdLayout.addClassName(Gap.MEDIUM);
        thirdLayout.setWidth("100%");
        thirdLayout.getStyle().set("flex-grow", "1");

        // Configuración del layout de columnas
        layoutColumn2.setHeightFull();
        thirdLayout.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");

        // Configuración de los botones
        btnBuscar.setText("Buscar");
        btnBuscar.setWidth("min-content");
        btnBuscar.addClickListener(e -> {
            String nombreABuscar = txtNombreProducto.getValue().trim().toLowerCase();
            String codigoABuscar = txtCodigoProducto.getValue().trim().toLowerCase();

            // Filtramos la lista completa basándonos en los TextFields
            List<Inventario> filtrados = inventarioService.obtenerCatalogoCompleto().stream()
                    .filter(p -> (nombreABuscar.isEmpty() || p.getNombre().toLowerCase().contains(nombreABuscar)) &&
                            (codigoABuscar.isEmpty()
                                    || String.valueOf(p.getCodigo()).toLowerCase().contains(codigoABuscar)))
                    .toList();

            basicGrid.setItems(filtrados);
        });

        btnLimpiarFiltros.setText("Limpiar");
        btnLimpiarFiltros.setWidth("min-content");
        btnLimpiarFiltros.addClickListener(e -> {
            txtNombreProducto.clear();
            txtCodigoProducto.clear();
            basicGrid.setItems(inventarioService.obtenerCatalogoCompleto());
        });

        btnStockCritico.setText("Stock critico");
        btnStockCritico.setWidth("min-content");
        btnStockCritico.addClickListener(e -> {
            basicGrid.setItems(inventarioService.obtenerTop10BajoStock());
        });

        btnAgregarProducto.setText("Agregar o actualizar producto");
        btnAgregarProducto.setWidth("min-content");
        btnAgregarProducto.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnAgregarProducto.addClickListener(e -> {
            UI.getCurrent().navigate(AgregarProductoView.class);
        });

        // Configuración del cuarto layout
        fourthLayout.setWidthFull();
        getContent().setFlexGrow(1.0, fourthLayout);
        fourthLayout.addClassName(Gap.MEDIUM);
        fourthLayout.setWidth("100%");
        fourthLayout.getStyle().set("flex-grow", "1");

        // Configuración del quinto layout
        fifthLayout.setHeightFull();
        fourthLayout.setFlexGrow(1.0, fifthLayout);
        fifthLayout.addClassName(Gap.MEDIUM);
        fifthLayout.setWidth("100%");
        fifthLayout.getStyle().set("flex-grow", "1");

        // Configuración del layout de columna 4
        layoutColumn4.setHeightFull();
        fifthLayout.setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth("100%");
        layoutColumn4.getStyle().set("flex-grow", "1");

        // Configuración de la tabla
        basicGrid.addColumn(Inventario::getCodigo).setHeader("Codigo");
        basicGrid.addColumn(Inventario::getNombre).setHeader("Nombre");
        basicGrid.addColumn(Inventario::getCategoria).setHeader("Categoria");
        basicGrid.addComponentColumn(producto -> {
            Span textoStock = new Span(String.valueOf(producto.getStock()));

            if (producto.getStock() <= producto.getStockCritico()) {
                textoStock.getStyle().set("color", "var(--lumo-error-text-color)");
                textoStock.getStyle().set("font-weight", "bold");
            }

            return textoStock;
        }).setHeader("Stock").setComparator(Inventario::getStock);

        basicGrid.addColumn(Inventario::getPrecio).setHeader("Precio");
        basicGrid.addColumn(Inventario::getStockCritico).setHeader("Stock Critico");
        basicGrid.setWidth("100%");
        basicGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(basicGrid);

        // Agregar los componentes al layout
        getContent().add(firstLayout);
        firstLayout.add(txtNombreProducto);
        firstLayout.add(secondLayout);
        secondLayout.add(txtCodigoProducto);
        secondLayout.add(thirdLayout);
        thirdLayout.add(layoutColumn2);
        layoutColumn2.add(layoutColumn3);
        layoutColumn3.add(btnBuscar);
        secondLayout.add(btnLimpiarFiltros);
        secondLayout.add(btnStockCritico);
        firstLayout.add(btnAgregarProducto);
        getContent().add(fourthLayout);
        fourthLayout.add(fifthLayout);
        fifthLayout.add(layoutColumn4);
        layoutColumn4.add(basicGrid);

    }

    private void setGridSampleData(Grid<Inventario> grid) {
        grid.setItems(inventarioService.obtenerCatalogoCompleto());
    }
}
