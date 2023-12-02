package model.builder;

import model.Book;
import model.RaportEmployee;

public class RaportEmployeeBuilder {

    private RaportEmployee raportEmployee;

    public RaportEmployeeBuilder(){
        raportEmployee = new RaportEmployee();
    }

    public RaportEmployeeBuilder setId(Long id){
        raportEmployee.setId(id);
        return this;
    }

    public RaportEmployeeBuilder setText(String text){
        raportEmployee.setText(text);
        return this;
    }
    public RaportEmployee build(){
        return raportEmployee;
    }
}
