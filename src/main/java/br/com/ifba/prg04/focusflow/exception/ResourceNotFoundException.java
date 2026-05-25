package br.com.ifba.prg04.focusflow.exception;


// exceção lançada quando um recurso não é encontrado
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }
}
