package com.example.application.views.agregarproducto;

import com.example.application.components.pricefield.PriceField;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Agregar Producto")
@Route("Agregar-producto")
// @Menu(order = 3, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class AgregarProductoView extends Composite<VerticalLayout> {

    public AgregarProductoView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        TextField textField = new TextField();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Button buttonPrimary = new Button();
        Span badge = new Span();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        TextField textField2 = new TextField();
        ComboBox comboBox = new ComboBox();
        PriceField price = new PriceField();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        NumberField numberField = new NumberField();
        NumberField numberField2 = new NumberField();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        Button buttonPrimary2 = new Button();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        textField.setLabel("Codigo del producto");
        textField.setWidth("min-content");
        layoutColumn2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.addClassName(Gap.LARGE);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.END);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        buttonPrimary.setText("Lupa");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.START, buttonPrimary);
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        badge.setText("Si el producto ya existe, el stock nuevo se adiciona al ya existente");
        badge.setWidth("100%");
        badge.getElement().getThemeList().add("badge");
        layoutRow2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        textField2.setLabel("Nombre del producto");
        textField2.setWidth("min-content");
        textField2.setHeight("60px");
        comboBox.setLabel("Categoria");
        comboBox.setWidth("min-content");
        comboBox.setHeight("60px");
        setComboBoxSampleData(comboBox);
        price.setLabel("Precio");
        price.setWidth("min-content");
        layoutRow3.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        numberField.setLabel("Stock nuevo");
        numberField.setWidth("min-content");
        numberField2.setLabel("Stock Critico");
        numberField2.setWidth("min-content");
        layoutColumn3.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        layoutRow4.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow4);
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.getStyle().set("flex-grow", "1");
        buttonPrimary2.setText("Guardar");
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancelar");
        buttonSecondary.setWidth("min-content");
        getContent().add(layoutRow);
        layoutRow.add(textField);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(buttonPrimary);
        getContent().add(badge);
        getContent().add(layoutRow2);
        layoutRow2.add(textField2);
        layoutRow2.add(comboBox);
        layoutRow2.add(price);
        getContent().add(layoutRow3);
        layoutRow3.add(numberField);
        layoutRow3.add(numberField2);
        getContent().add(layoutColumn3);
        layoutColumn3.add(layoutRow4);
        layoutRow4.add(buttonPrimary2);
        layoutRow4.add(buttonSecondary);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
