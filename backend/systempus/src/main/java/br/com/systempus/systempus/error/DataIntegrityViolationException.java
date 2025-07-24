package br.com.systempus.systempus.error;

import org.springframework.dao.NonTransientDataAccessException;

public class DataIntegrityViolationException extends NonTransientDataAccessException{

    public DataIntegrityViolationException(String message){
        super(message);
    }

    public static String cpfExists(String cpf){
        StringBuilder mensagem = new StringBuilder();

        mensagem.append("O CPF: ")
                .append(cpf)
                .append(" já foi cadastrado no sistema");

        return mensagem.toString();
    }

    public static String emailExists(String email){
        StringBuilder mensagem = new StringBuilder();

        mensagem.append("O email: ")
                .append(email)
                .append(" já foi cadastrado no sistema");

        return mensagem.toString();
    }

    public static String turnoAlreadyExists(String turno, String curso){
        StringBuilder mensagem = new StringBuilder();
        
        mensagem.append("Já existe um período com o turno ")
                .append(turno)
                .append(" cadastrado para o curso ")
                .append(curso);
        
        return mensagem.toString();
    }

    public static String turnoDoesntMatchHours(){
        return "Os horários informados não são condizentes com o turno escolhido";
    }

}
