package softuni.carrepairhistory.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import softuni.carrepairhistory.models.exception.ObjectNotFoundException;

@ControllerAdvice
public class ErrorController {


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView notFound(ObjectNotFoundException notFoundException) {

        ModelAndView modelAndView = new ModelAndView("error-not-found");

        modelAndView.addObject("objectId", notFoundException.getObjectId());
        modelAndView.addObject("objectType", notFoundException.getObjectType());

        return modelAndView;
    }
}
