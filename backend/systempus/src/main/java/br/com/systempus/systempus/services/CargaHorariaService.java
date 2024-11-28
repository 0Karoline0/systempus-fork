package br.com.systempus.systempus.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import br.com.systempus.systempus.domain.CargaHoraria;
import br.com.systempus.systempus.error.IllegalStateException;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.CargaHorariaRepository;

@Service
public class CargaHorariaService {
    @Autowired
    private CargaHorariaRepository repository;

    public CargaHoraria getOne(Integer id) {
        CargaHoraria resultado = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(CargaHoraria.class.getSimpleName().toString(), id));
        return resultado;
    }

    public List<CargaHoraria> getAll() {
        List<CargaHoraria> resultado = repository.findAll();
        return resultado;
    }

    public void save(CargaHoraria cargaHoraria) {
        if ((cargaHoraria.getId() == null)) {
            repository.save(cargaHoraria);
        } else {
            throw new IllegalStateException(CargaHoraria.class.getSimpleName().toString());
        }
    }

    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException(CargaHoraria.class.getSimpleName().toString(), id);
        }
    }


    public void update(CargaHoraria cargaHoraria) {

        if (!repository.existsById(cargaHoraria.getId())) {
			throw new NotFoundException(CargaHoraria.class.getSimpleName().toString(), cargaHoraria.getId());
		}

        CargaHoraria cargaHorariaExistente = repository.findById(cargaHoraria.getId()).get();

        repository.saveAndFlush(cargaHorariaExistente);

    }


    public CargaHoraria updatePartial(Map<String, Object> mapValores, Integer id) {

        if (!repository.existsById(id)) {
			throw new NotFoundException(CargaHoraria.class.getSimpleName().toString(), id);
		}

        CargaHoraria cargaHorariaExistente = repository.findById(id).get();

        mapValores.forEach(
                (campo, valor) -> {
                    Field field = ReflectionUtils.findField(CargaHoraria.class, campo);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, cargaHorariaExistente, valor);
                });
        repository.saveAndFlush(cargaHorariaExistente);
        return cargaHorariaExistente;

    }
}