package com.example.application.views.inventario;

import com.example.application.data.Pedidos;
import com.example.application.services.PedidosService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Inventario")
@Route("Inventario")
@Menu(order = 2, icon = LineAwesomeIconUrl.STORE_SOLID)
@Uses(Icon.class)
public class InventarioView extends Composite<VerticalLayout> {

    public InventarioView() {
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
        HorizontalLayout fourthLayout = new HorizontalLayout();
        HorizontalLayout fifthLayout = new HorizontalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        Grid basicGrid = new Grid(Pedidos.class);

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
        btnBuscar.setText("buscar");
        btnBuscar.setWidth("min-content");
        btnStockCritico.setText("Stock critico");
        btnStockCritico.setWidth("min-content");
        btnAgregarProducto.setText("Agregar producto");
        btnAgregarProducto.setWidth("min-content");
        btnAgregarProducto.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

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
        secondLayout.add(btnStockCritico);
        firstLayout.add(btnAgregarProducto);
        getContent().add(fourthLayout);
        fourthLayout.add(fifthLayout);
        fifthLayout.add(layoutColumn4);
        layoutColumn4.add(basicGrid);

    }

    private void setGridSampleData(Grid grid) {

    }

    @Autowired()
    private PedidosService pedidosService;
}
