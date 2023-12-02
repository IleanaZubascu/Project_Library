package service.raportemployee;

import model.RaportEmployee;

import java.util.List;

public interface RaportEmployeeService {

    List<RaportEmployee> findall();

    void save(String text);
}
