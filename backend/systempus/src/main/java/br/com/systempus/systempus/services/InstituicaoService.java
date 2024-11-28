package br.com.systempus.systempus.services;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import br.com.systempus.systempus.domain.Instituicao;
import br.com.systempus.systempus.error.IllegalStateException;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.InstituicaoRepository;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository repository;

    public List<Instituicao> getAll() {
        List<Instituicao> resultado = repository.findAll();
        return resultado;
    }

    public Instituicao getOne(Integer id) {
        Instituicao resultado = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Instituicao.class.getSimpleName().toString(), id));
        return resultado;
    }

    public void save(Instituicao instituicao) {
        if (instituicao.getId() == null) {
            repository.save(instituicao);
        } else {
            throw new IllegalStateException(Instituicao.class.getSimpleName().toString());
        }
    }

    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException(Instituicao.class.getSimpleName().toString(), id);
        }
    }


    public void update(Instituicao instituicao) {
        if (repository.existsById(instituicao.getId())) {
            Instituicao existente = repository.findById(instituicao.getId()).get();

            existente.setNome(instituicao.getNome());

            repository.saveAndFlush(existente);

        } else {
            throw new NotFoundException(Instituicao.class.getSimpleName().toString(), instituicao.getId());
        }
    }

    public Instituicao updatePartial(Map<String, Object> mapValores, Integer id) {
        if (repository.existsById(id)) {
            Instituicao cursoExistente = repository.findById(id).get();

            mapValores.forEach(
                    (campo, valor) -> {
                        Field field = ReflectionUtils.findField(Instituicao.class, campo);
                        field.setAccessible(true);

                        if (!(field.getType().isEnum())) {
                            ReflectionUtils.setField(field, cursoExistente, valor);
                            field.setAccessible(false);
                        } else {

                            try {
                                Class<?> enumClass = field.getType();

                                Method valueOfMethod = enumClass.getMethod("toEnum", Integer.class);
                                Enum<?> enumValor = (Enum<?>) valueOfMethod.invoke(null, (Integer) valor);

                                ReflectionUtils.setField(field, cursoExistente, enumValor);
                                field.setAccessible(false);
                            } catch (NoSuchMethodException ex) {
                                throw new NotFoundException("Método não encontrado no enum toEnum");
                            } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    });

            System.out.println(cursoExistente);

            repository.saveAndFlush(cursoExistente);
            return cursoExistente;
        } else {
            throw new NotFoundException(Instituicao.class.getSimpleName().toString(), id);
        }
    }


}