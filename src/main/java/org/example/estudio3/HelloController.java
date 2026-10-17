package org.example.estudio3;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class HelloController {

    ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Producto, Integer> tcCantidad;
    @FXML
    private TextField tfDescripcion;
    @FXML
    private Button btnEliminar;
    @FXML
    private Label titulo;
    @FXML
    private TableView<Producto> tvProductos;
    @FXML
    private TextField tfCantidad;
    @FXML
    private Button btnAnadir;
    @FXML
    private TableColumn<Producto, String> tcProducto;
    @FXML
    private TableColumn<Producto, Double> tcPrecioUnitario;
    @FXML
    private TextField tfPrecioUnitario;

    @FXML
    public void initialize() {

        tvProductos.setItems(listaProductos);

        tcProducto.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
        tcCantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty().asObject());
        tcPrecioUnitario.setCellValueFactory(cellData -> cellData.getValue().precioUnitarioProperty().asObject());


        /**
         * 3. BOOLEAN BINDING: Controla dinámicamente cuándo el botón 'Añadir' debe estar activo o deshabilitado.
         * Se volverá a evaluar automáticamente cada vez que cambie el texto de cualquiera de los tres TextField.
         */

        BooleanBinding camposAltaBien = Bindings.createBooleanBinding(() -> {

            //si la descripcion esta vacia es true si no false
            Boolean camposMal = (tfDescripcion.getText().trim().isEmpty());

            Boolean numerosBien = true;

                    try {

                        //si es un numero se quedara con el true de arriba
                        Double.parseDouble(tfPrecioUnitario.getText().trim());
                        Integer.parseInt(tfCantidad.getText().trim());

                    } catch (NumberFormatException nfe) {
                        //pero si es una letra retorna false
                        numerosBien = false;
                    }

                    /**
                     * Si el campo esta vacio retorna true por eso pongo el !,
                     * si los dos son verdaderos puedes anadir
                     * con que uno sea falso no podras anadir
                     */
                    return !camposMal && numerosBien;

                }, tfDescripcion.textProperty(), tfCantidad.textProperty(), tfPrecioUnitario.textProperty()

                );

                // uso el booleanBinding aqui para comprobar que los campos estan bien
                btnAnadir.disableProperty().bind(camposAltaBien.not());

                btnEliminar.disableProperty().bind(tvProductos.getSelectionModel().selectedItemProperty().isNull());


                tfCantidad.addEventFilter(KeyEvent.KEY_TYPED, event -> {

                    // Coge cada caracter introducido (KEY_TYPED) en tfCantidad
                    char caracter = event.getCharacter().charAt(0);

                    // Si el caracter NO es un numero
                    if (!Character.isDigit(caracter)) {
                        event.consume(); // Cancela/anula el evento, impidiendo que aparezca en el TextField
                    }

                    tfPrecioUnitario.addEventFilter(KeyEvent.KEY_TYPED, event2 -> {

                        if (event.getCharacter().isEmpty()) {
                            return;
                        }

                        char caracter2 = event.getCharacter().charAt(0);

                        if (Character.isDigit(caracter2) && caracter != '.' && caracter != ',') {
                            event.consume();
                        }

                    });
                });

        tfDescripcion.setOnKeyPressed(this::manejarTeclado);
        tfCantidad.setOnKeyPressed(this::manejarTeclado);
        tfPrecioUnitario.setOnKeyPressed(this::manejarTeclado);


    }

    @FXML
    public void onEliminar(ActionEvent actionEvent) {
        listaProductos.remove(tvProductos.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void onAnadir(ActionEvent actionEvent) {

        int cantidad = Integer.parseInt(tfCantidad.getText());
        double precio = Double.parseDouble(tfPrecioUnitario.getText());

        listaProductos.add(new Producto(tfDescripcion.getText(), cantidad, precio));
    }


    private void manejarTeclado(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("Algo");
        } else if (event.getCode() == KeyCode.ESCAPE){
            System.out.println("Adios");
        }
    }


}
