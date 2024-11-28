package br.com.systempus.systempus.services;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.domain.Modulo;
import br.com.systempus.systempus.error.IllegalStateException;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.DisciplinaRepository;
import br.com.systempus.systempus.repository.ModuloRepository;
import br.com.systempus.systempus.services.interfaces.IModuloService;

@Service
public class ModuloService implements IModuloService{

    @Autowired
    private ModuloRepository repository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Override
	public List<Modulo> getAll(){
        List<Modulo> resultado = repository.findAll();
        return resultado;
    }

    @Override
	public Modulo getOne(Integer id){
        Modulo resultado = repository.findById(id).orElseThrow(() -> new NotFoundException(Modulo.class.getSimpleName().toString()));
        return resultado;
    }

    @Override
	public void save(Modulo modulo){
        if (modulo.getId() == null){
            repository.save(modulo);
        }else{
            throw new IllegalStateException(Modulo.class.getSimpleName().toString());
        }
    }

    @Override
	public void delete(Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new NotFoundException(Modulo.class.getSimpleName().toString(), id);
        }
    }

    @Override
	public void update(Modulo modulo){
        if(repository.existsById(modulo.getId())){
            Modulo moduloExistente = repository.findById(modulo.getId()).get();

            moduloExistente.setNome(modulo.getNome());
            moduloExistente.setDataInicio(modulo.getDataInicio());
            moduloExistente.setDataFim(modulo.getDataFim());
            moduloExistente.setCurso(modulo.getCurso());
            moduloExistente.setDisciplinas(modulo.getDisciplinas());

            repository.saveAndFlush(moduloExistente);
        }else{
            throw new NotFoundException(Modulo.class.getSimpleName().toString(), modulo.getId());
        }
    }

    @Override
	public Modulo updatePartial(Map<String, Object> mapValores, Integer id){
        if(repository.existsById(id)){
            Modulo moduloExistente = repository.findById(id).get();

            mapValores.forEach(
                (campo, valor)->{
                    Field field = ReflectionUtils.findField(Modulo.class, campo);
                    field.setAccessible(true);
                    if (field.getType().equals(LocalDate.class) && valor instanceof String){
                        LocalDate valorConvertido = LocalDate.parse((String) valor, DateTimeFormatter.ISO_DATE);
                        ReflectionUtils.setField(field, moduloExistente, valorConvertido);
                    }else{
                        ReflectionUtils.setField(field, moduloExistente, valor);
                    }
                }
            );

            repository.saveAndFlush(moduloExistente);
            return moduloExistente;
        }else{
            throw new NotFoundException(Modulo.class.getSimpleName().toString(), id);
        }
    }

    public Modulo adicionarDisicplinas(Integer idModulo, Disciplina disciplina){
        Modulo modulo = repository.findById(idModulo).get();
        disciplina.setModulo(modulo);

        disciplinaRepository.save(disciplina);

        return repository.findById(idModulo).get();
    }

}