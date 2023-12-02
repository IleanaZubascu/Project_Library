package service.raportemployee;

import model.RaportEmployee;
import repository.RaportEmployee.RaportEmployeeRepository;

import java.util.List;

public class RaportEmployeeServiceImpl implements RaportEmployeeService {

    private final RaportEmployeeRepository raportEmployeeRepository;

    public RaportEmployeeServiceImpl(RaportEmployeeRepository raportEmployeeRepository)
    {
        this.raportEmployeeRepository=raportEmployeeRepository;
    }

    @Override
    public List<RaportEmployee> findall() {
        return raportEmployeeRepository.findall();
    }

    @Override
    public void save(String text) {
        raportEmployeeRepository.save(text);
    }
}
