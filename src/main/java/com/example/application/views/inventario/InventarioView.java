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
        HorizontalLayout layoutRow = new HorizontalLayout();
        TextField textField = new TextField();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        TextField textField2 = new TextField();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();
        Button buttonPrimary3 = new Button();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        HorizontalLayout layoutRow5 = new HorizontalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        Grid basicGrid = new Grid(Pedidos.class);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        textField.setLabel("Nombre del producto");
        textField.setWidth("min-content");
        layoutRow2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        textField2.setLabel("Codigo del producto");
        textField2.setWidth("min-content");
        layoutRow3.setHeightFull();
        layoutRow2.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        layoutColumn2.setHeightFull();
        layoutRow3.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("buscar");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary2.setText("Stock critico");
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary3.setText("Agregar producto");
        buttonPrimary3.setWidth("min-content");
        buttonPrimary3.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutRow4.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow4);
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.getStyle().set("flex-grow", "1");
        layoutRow5.setHeightFull();
        layoutRow4.setFlexGrow(1.0, layoutRow5);
        layoutRow5.addClassName(Gap.MEDIUM);
        layoutRow5.setWidth("100%");
        layoutRow5.getStyle().set("flex-grow", "1");
        layoutColumn4.setHeightFull();
        layoutRow5.setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth("100%");
        layoutColumn4.getStyle().set("flex-grow", "1");
        basicGrid.setWidth("100%");
        basicGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(basicGrid);
        getContent().add(layoutRow);
        layoutRow.add(textField);
        layoutRow.add(layoutRow2);
        layoutRow2.add(textField2);
        layoutRow2.add(layoutRow3);
        layoutRow3.add(layoutColumn2);
        layoutColumn2.add(layoutColumn3);
        layoutColumn3.add(buttonPrimary);
        layoutRow2.add(buttonPrimary2);
        layoutRow.add(buttonPrimary3);
        getContent().add(layoutRow4);
        layoutRow4.add(layoutRow5);
        layoutRow5.add(layoutColumn4);
        layoutColumn4.add(basicGrid);
    }

    private void setGridSampleData(Grid grid) {

    }

    @Autowired()
    private PedidosService pedidosService;
}
