/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.model;

import java.util.*;

/**
 *
 * @author andre
 */
public class Carro {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
//new Carro(getRandomId(), getRandomBrand(), getRandomYear(), getRandomColor(), getRandomPrice(), getRandomSoldState()));
    private String id;
    private String marca;
    private int ano;
    private String cor;
    private double preco;
    private boolean estado;
    private String nome;
    private String code;

    public Carro() {
    }

    public Carro(String id, String marca, int ano, String cor, double preco, boolean estado) {
        this.id = id;
        this.marca = marca;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
        this.estado = estado;
    }
/*
    public List<Carro> getAll() {
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro("Fusca", "Volks"));
        carros.add(new Carro("Viper", "Dodge"));
        carros.add(new Carro("GTOne", "Toyota"));
        carros.add(new Carro("GT40", "Ford"));
        return carros;
    }
*/
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
