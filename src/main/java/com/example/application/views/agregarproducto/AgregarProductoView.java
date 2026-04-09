package com.example.application.views.agregarproducto;

import com.example.application.data.Inventario;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.services.InventarioService;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Agregar Producto")
@Route("Agregar-producto")

public class AgregarProductoView extends Composite<VerticalLayout> {

    private final InventarioService inventarioService;
    private boolean nuevoProducto;

    public AgregarProductoView(InventarioService inventarioService) {
        this.inventarioService = inventarioService;

        // Definicion de componentes
        Icon lupa = VaadinIcon.SEARCH.create();
        HorizontalLayout firsLayout = new HorizontalLayout();
        TextField txtCodigoProducto = new TextField();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Button btnLupa = new Button();
        Span badge = new Span();
        HorizontalLayout secondLayout = new HorizontalLayout();
        TextField txtNombreProducto = new TextField();
        ComboBox<SampleItem> cmbCategoria = new ComboBox<>();
        TextField txtPrecio = new TextField();
        HorizontalLayout thirdLayout = new HorizontalLayout();
        TextField txtNuevoStock = new TextField();
        TextField txtStockCritico = new TextField();
        TextField txtStockActual = new TextField();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        HorizontalLayout fourthLayout = new HorizontalLayout();
        Button btnGuardar = new Button();
        Button btnCancelar = new Button();

        nuevoProducto = true;

        TextArea txtDescripcion = new TextArea();
        txtDescripcion.setLabel("Descripcion");
        txtDescripcion.setWidth("100%");
        txtDescripcion.setHeight("60px");
        txtDescripcion.setEnabled(false);
        txtDescripcion.setValue("Descripcion del producto");

        // Layout principal
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        // primer layout
        firsLayout.setWidthFull();
        getContent().setFlexGrow(1.0, firsLayout);
        firsLayout.addClassName(Gap.MEDIUM);
        firsLayout.setWidth("100%");
        firsLayout.getStyle().set("flex-grow", "1");
        firsLayout.setFlexGrow(1.0, layoutColumn2);

        // textfield codigo del producto
        txtCodigoProducto.setLabel("Codigo del producto");
        txtCodigoProducto.setWidth("min-content");
        txtCodigoProducto.setPlaceholder("Codigo del producto");

        // layout column 2
        layoutColumn2.setHeightFull();
        layoutColumn2.addClassName(Gap.LARGE);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.END);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.START, btnLupa);

        // boton lupa
        btnLupa.setIcon(lupa);
        btnLupa.setWidth("min-content");
        btnLupa.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnLupa.addClickListener(e -> {
            if (txtCodigoProducto.isEmpty()) {
                Notification.show("Ingrese el codigo del producto");
                return;
            }

            Inventario producto = inventarioService.buscarProducto(Long.parseLong(txtCodigoProducto.getValue()));

            if (producto != null) {
                txtNombreProducto.setValue(producto.getNombre());
                cmbCategoria.setValue(new SampleItem(producto.getCategoria(), producto.getCategoria(), null));
                txtPrecio.setValue(String.valueOf(producto.getPrecio()));
                txtPrecio.setEnabled(true);
                txtStockActual.setVisible(true);
                txtStockActual.setValue(String.valueOf(producto.getStock()));

                txtNuevoStock.setEnabled(true);

                txtStockCritico.setEnabled(true);

                txtStockCritico.setValue(String.valueOf(producto.getStockCritico()));
                txtStockCritico.setEnabled(true);

                btnGuardar.setEnabled(true);
                nuevoProducto = false;
                return;
            }

            txtNuevoStock.clear();
            txtStockCritico.clear();
            txtPrecio.clear();
            txtNombreProducto.clear();
            cmbCategoria.clear();

            txtStockActual.setVisible(false);
            txtNombreProducto.setEnabled(true);
            cmbCategoria.setEnabled(true);
            txtPrecio.setEnabled(true);
            txtNuevoStock.setEnabled(true);
            txtStockCritico.setEnabled(true);
            btnGuardar.setEnabled(true);

        });

        // badge
        badge.setText("Si es producto existente, el stock se suma al stock actual");
        badge.setWidth("100%");
        badge.getElement().getThemeList().add("badge");

        // segundo layout
        secondLayout.setWidthFull();
        getContent().setFlexGrow(1.0, secondLayout);
        secondLayout.addClassName(Gap.MEDIUM);
        secondLayout.setWidth("100%");
        secondLayout.getStyle().set("flex-grow", "1");

        // textfield nombre del producto
        txtNombreProducto.setLabel("Nombre del producto");
        txtNombreProducto.setWidth("min-content");
        txtNombreProducto.setHeight("60px");
        txtNombreProducto.setEnabled(false);
        txtNombreProducto.setPlaceholder("Nombre del producto");

        // combobox categoria
        cmbCategoria.setLabel("Categoria");
        cmbCategoria.setWidth("min-content");
        cmbCategoria.setHeight("60px");
        cmbCategoria.setEnabled(false);
        setComboBoxSampleData(cmbCategoria);
        cmbCategoria.setPlaceholder("Seleccione una categoria");

        // textfield precio
        txtPrecio.setLabel("Precio");
        txtPrecio.setWidth("min-content");
        txtPrecio.setEnabled(false);
        txtPrecio.setPlaceholder("Precio del producto");

        // tercer layout
        thirdLayout.setWidthFull();
        getContent().setFlexGrow(1.0, thirdLayout);
        thirdLayout.addClassName(Gap.MEDIUM);
        thirdLayout.setWidth("100%");
        thirdLayout.getStyle().set("flex-grow", "1");

        // textfield stock nuevo
        txtNuevoStock.setLabel("Stock nuevo");
        txtNuevoStock.setWidth("min-content");
        txtNuevoStock.setEnabled(false);
        txtNuevoStock.setPlaceholder("Ingrese el stock nuevo");

        // textfield stock critico
        txtStockCritico.setLabel("Stock Critico");
        txtStockCritico.setWidth("min-content");
        txtStockCritico.setEnabled(false);
        txtStockCritico.setPlaceholder("Ingrese el stock critico");

        // textfield stock actual
        txtStockActual.setLabel("Stock actual");
        txtStockActual.setWidth("min-content");
        txtStockActual.setEnabled(false);
        txtStockActual.setVisible(false);
        txtStockActual.setPlaceholder("Stock actual");

        // layout column 3
        layoutColumn3.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");

        // fourth layout
        fourthLayout.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, fourthLayout);
        fourthLayout.addClassName(Gap.MEDIUM);
        fourthLayout.setWidth("100%");
        fourthLayout.getStyle().set("flex-grow", "1");

        // boton guardar
        btnGuardar.setText("Guardar");
        btnGuardar.setWidth("min-content");
        btnGuardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnGuardar.setEnabled(false);
        btnGuardar.addClickListener(e -> {
            if (txtNombreProducto.isEmpty() || cmbCategoria.isEmpty() || txtPrecio.isEmpty() || txtNuevoStock.isEmpty()
                    || txtStockCritico.isEmpty()) {
                Notification.show("Complete todos los campos");
                return;
            }

            Inventario producto = new Inventario();

            producto.setCodigo(Long.valueOf(txtCodigoProducto.getValue()));
            producto.setNombre(txtNombreProducto.getValue());
            producto.setCategoria(cmbCategoria.getValue().value());

            try {
                producto.setPrecio(Integer.parseInt(txtPrecio.getValue()));
                producto.setStock(Integer.parseInt(txtNuevoStock.getValue()));
                producto.setStockCritico(Integer.parseInt(txtStockCritico.getValue()));
            } catch (Exception i) {
                Notification.show("Ingrese solo numeros en los campos de precio, stock y stock critico");
                return;
            }

            if (nuevoProducto) {
                inventarioService.registrarNuevoProducto(producto);
                Notification.show("Producto guardado exitosamente");
                UI.getCurrent().navigate("Inventario");
            } else {
                inventarioService.actualizarStock(producto.getCodigo(), producto.getStock(), "Actualizacion");
                Notification.show("Producto actualizado exitosamente");
                UI.getCurrent().navigate("Inventario");
            }
        });

        // boton cancelar
        btnCancelar.setText("Cancelar");
        btnCancelar.setWidth("min-content");
        btnCancelar.addClickListener(e -> {
            UI.getCurrent().navigate("Inventario");
        });

        // agregar componentes al layout
        getContent().add(firsLayout);
        firsLayout.add(txtCodigoProducto);
        firsLayout.add(layoutColumn2);
        layoutColumn2.add(btnLupa);
        getContent().add(badge);
        getContent().add(secondLayout);
        secondLayout.add(txtNombreProducto);
        secondLayout.add(cmbCategoria);
        secondLayout.add(txtPrecio);
        getContent().add(thirdLayout);
        thirdLayout.add(txtNuevoStock);
        thirdLayout.add(txtStockCritico);
        thirdLayout.add(txtStockActual);
        getContent().add(layoutColumn3);
        layoutColumn3.add(fourthLayout);
        fourthLayout.add(btnGuardar);
        fourthLayout.add(btnCancelar);
        // layoutColumn3.add(txtDescripcion);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox<SampleItem> comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("Abarrotes", "Abarrotes", null));
        sampleItems.add(new SampleItem("Carnes", "Carnes", null));
        sampleItems.add(new SampleItem("Verduras", "Verduras", null));
        sampleItems.add(new SampleItem("Frutas", "Frutas", null));
        sampleItems.add(new SampleItem("Bebidas", "Bebidas", null));
        sampleItems.add(new SampleItem("Limpieza", "Limpieza", null));
        sampleItems.add(new SampleItem("Lacteos", "Lacteos", null));
        sampleItems.add(new SampleItem("Panaderia", "Panaderia", null));
        sampleItems.add(new SampleItem("Congelados", "Congelados", null));
        sampleItems.add(new SampleItem("Otros", "Otros", null));

        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
