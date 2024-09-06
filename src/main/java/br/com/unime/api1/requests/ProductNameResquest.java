package br.com.unime.api1.requests;

public class ProductNameResquest {

    private String name;


    public ProductNameResquest() {

    }

    public ProductNameResquest(String name) {
        this.name = name;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
