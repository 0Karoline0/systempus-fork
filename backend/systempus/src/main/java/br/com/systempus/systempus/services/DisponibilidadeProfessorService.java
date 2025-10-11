package br.com.systempus.systempus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.domain.DisponibilidadeProfessor;
import br.com.systempus.systempus.domain.DisponibilidadeProfessor;
import br.com.systempus.systempus.domain.HorarioAula;
import br.com.systempus.systempus.domain.dto.DisponibilidadeProfessorDTO;
import br.com.systempus.systempus.domain.role_object.Professor;
import br.com.systempus.systempus.repository.DisponibilidadeProfessorRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

@Service
public class DisponibilidadeProfessorService {

    @Autowired
    private DisponibilidadeProfessorRepository repository;

    @Autowired
    private HorarioAulaService horarioAulaService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private WhatsappService whatsappService;

    public DisponibilidadeProfessor getById(Integer id){
        return repository.findById(id).get();
    }

    public List<DisponibilidadeProfessor> getAll(){
        return repository.findAll();
    }

    @Transactional
    public List<DisponibilidadeProfessorDTO> save(List<DisponibilidadeProfessorDTO> disponibilidades, Professor professor){

        List<DisponibilidadeProfessorDTO> disponibilidadesRetorno = new ArrayList<>();

        for (DisponibilidadeProfessorDTO disponibilidade : disponibilidades){
            HorarioAula horario = horarioAulaService.getById(disponibilidade.getHorarioAulaId());
            DisponibilidadeProfessor d = new DisponibilidadeProfessor(professor, horario, disponibilidade.getDiaSemana());
            disponibilidadesRetorno.add(disponibilidade);
            repository.save(d);
        }

        return disponibilidadesRetorno;
    }

    @Transactional
    public List<DisponibilidadeProfessorDTO> updateDisponibilidades(List<DisponibilidadeProfessorDTO> disponibilidades, Professor professor){
        deleteDisponibilidadesByProfessor(professor);
        return save(disponibilidades, professor);
    }

    @Transactional
    public void deleteDisponibilidadesByProfessor(Professor professor){
        List<DisponibilidadeProfessor> disponibilidades = repository.getByProfessor(professor);
        for (DisponibilidadeProfessor d : disponibilidades) {
            repository.delete(d);
        }
    }

    public void deleteDisponibilidade(Integer id) {
        repository.deleteById(id);
    }

    public List<DisponibilidadeProfessorDTO> saveDisponibilidadeProfessor(List<DisponibilidadeProfessorDTO> disponibilidadeRequest, Integer professorId) {
        Professor professor = professorService.getOne(professorId);
        return save(disponibilidadeRequest, professor);
    }

    public List<DisponibilidadeProfessorDTO> updateDisponibilidadeProfessor(Integer professorId, List<DisponibilidadeProfessorDTO> disponibilidades){
        Professor professor = professorService.getOne(professorId);
        return updateDisponibilidades(disponibilidades, professor);
    }

    public String sendWhatsappMessage(Integer idProfessor, Integer idDisciplina, String customMessage) throws IOException, MessagingException{
        
        Professor professor = professorService.getOne(idProfessor);
        Disciplina disciplina = disciplinaService.getOne(idDisciplina);

        try {
            Integer statusCode = whatsappService.sendWhatsappMessage(professor.getProfissional().getTelefone(), professor.getProfissional().getNome(), disciplina, customMessage);
            if (statusCode >= 200 && statusCode < 300){
                return "Mensagem enviada por Whatsapp com sucesso!";
            }
            return "Não foi possível enviar mensagem";
        } catch (Exception e) {
            emailService.enviarEmailAlteracaoHorario(professor, disciplina, customMessage);
            return "Não foi possível enviar mensagem por Whatsapp, a mensagem foi enviada por email.";
        }
    }

}
