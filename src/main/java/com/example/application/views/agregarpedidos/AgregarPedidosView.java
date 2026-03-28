package com.example.application.views.agregarpedidos;

import com.example.application.data.Pedidos;
import com.example.application.services.PedidosService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Agregar Pedidos")
@Route("Agregar-pedidos")
// @Menu(order = 4, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@Uses(Icon.class)
public class AgregarPedidosView extends Composite<VerticalLayout> {

    public AgregarPedidosView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        TextField textField = new TextField();
        ComboBox comboBox = new ComboBox();
        ComboBox comboBox2 = new ComboBox();
        NumberField numberField = new NumberField();
        Button buttonSecondary = new Button();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Grid basicGrid = new Grid(Pedidos.class);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.setAlignItems(Alignment.END);
        layoutRow.setJustifyContentMode(JustifyContentMode.START);

        textField.setLabel("Nombre del cliente");
        textField.setWidth("min-content");

        comboBox.setLabel("Prioridad");
        comboBox.setWidth("min-content");
        setPriority(comboBox);

        comboBox2.setLabel("Articulo");
        comboBox2.setWidth("min-content");
        setArticulo(comboBox2);

        numberField.setLabel("Cantidad");
        numberField.setWidth("min-content");

        buttonSecondary.setText("Agregar");
        buttonSecondary.setWidth("min-content");

        layoutRow2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        layoutRow2.setAlignItems(Alignment.START);
        layoutRow2.setJustifyContentMode(JustifyContentMode.END);

        buttonPrimary.setText("Finalizar pedido");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        basicGrid.setWidth("100%");
        basicGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(basicGrid);

        getContent().add(layoutRow);
        layoutRow.add(textField);
        layoutRow.add(comboBox);
        layoutRow.add(comboBox2);
        layoutRow.add(numberField);
        layoutRow.add(buttonSecondary);
        layoutRow.add(layoutRow2);
        layoutRow2.add(buttonPrimary);
        getContent().add(basicGrid);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setPriority(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("Urgente", "Urgente", null));
        sampleItems.add(new SampleItem("Normal", "Normal", null));
        sampleItems.add(new SampleItem("Baja", "Baja", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }

    private void setArticulo(ComboBox comboBox2) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("Leche", "Leche", null));
        sampleItems.add(new SampleItem("Pan", "Pan", null));
        sampleItems.add(new SampleItem("Huevos", "Huevos", null));
        comboBox2.setItems(sampleItems);
        comboBox2.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }

    private void setGridSampleData(Grid grid) {
        // grid.setItems(query ->
        // pedidosService.list(VaadinSpringDataHelpers.toSpringPageRequest(query)).stream());
    }

    @Autowired()
    private PedidosService pedidosService;
}
