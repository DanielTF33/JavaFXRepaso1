package org.example.estudio3;

import javafx.beans.property.*;

public class Producto {

    private final StringProperty descripcion = new SimpleStringProperty(this, "descripcion", "");
    private final IntegerProperty cantidad = new SimpleIntegerProperty(this, "cantidad", 0);
    private final DoubleProperty precioUnitario = new SimpleDoubleProperty(this, "precioUnitario", 0.0);

    public Producto(String descripcion, Integer cantidad, Double precioUnitario) {
        setDescripcion(descripcion);
        setCantidad(cantidad);
        setPrecioUnitario(precioUnitario);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public double getPrecioUnitario() {
        return precioUnitario.get();
    }

    public DoubleProperty precioUnitarioProperty() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario.set(precioUnitario);
    }
}
