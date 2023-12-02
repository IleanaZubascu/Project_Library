package repository.RaportEmployee;

import model.RaportEmployee;

import java.util.List;

public interface RaportEmployeeRepository {

    List<RaportEmployee> findall();

    void save(String text);

}
