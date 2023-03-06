package com.kardex.HulkStore.HulkStore.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Producto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NotNull(message = "Campo Obligatorio")
        @Size(min = 1, max = 50, message = "Longitud del codigo entre 1 a 50 caracteres")
        private String nombre;

        @NotNull(message = "Debes colocar un codigo")
        @Size(min = 1, max = 50, message = "Longitud del codigo entre 1 a 50 caracteres")
        private String codigo;

        @NotNull(message = "Precio obligatorio")
        @Min(value = 0, message = "valor minimo de precio 0$")
        private Float precio;

        @NotNull(message = "Campo obligatorio")
        @Min(value = 0, message = "Valor minimo de existencia es 0")
        private Float existencia;


        public Producto(String nombre, String codigo, Float precio, Float existencia, Integer id) {
            this.nombre = nombre;
            this.codigo = codigo;
            this.precio = precio;
            this.existencia = existencia;
            this.id = id;
        }

        public Producto(String nombre, String codigo, Float precio, Float existencia) {
            this.nombre = nombre;
            this.codigo = codigo;
            this.precio = precio;
            this.existencia = existencia;
        }

        public Producto(@NotNull(message = "Debes colocar un codigo") @Size(min = 1, max = 50, message = "Longitud del codigo entre 1 a 50 caracteres") String codigo) {
            this.codigo = codigo;
        }

        public Producto() {
        }

        public boolean sinExistencia() {
            return this.existencia <= 0;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public Float getPrecio() {
            return precio;
        }

        public void setPrecio(Float precio) {
            this.precio = precio;
        }

        public Float getExistencia() {
            return existencia;
        }

        public void setExistencia(Float existencia) {
            this.existencia = existencia;
        }

        public void restarExistencia(Float existencia) {
            this.existencia -= existencia;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }


