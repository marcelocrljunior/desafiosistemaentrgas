package com.desafio.desafioentregas.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe que representa o handler para tratamentos de erros na API
 * @author marceloc.r.lopesjunior
 */
@ControllerAdvice
public class ErroCustomizadoGlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({MalhaServerErrorException.class,EntregaServerErrorException.class})
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


}
