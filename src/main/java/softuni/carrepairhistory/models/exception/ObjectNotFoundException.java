package softuni.carrepairhistory.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "not found")
public class ObjectNotFoundException extends RuntimeException {
    private final Long objectId;

    private final String objectType;

    public ObjectNotFoundException(Long objectId, String objectType) {

//        super("Object with ID " + objectId + " and type" + objectType + " not found");

        this.objectId = objectId;
        this.objectType = objectType;
    }


    public Long getObjectId() {
        return objectId;
    }

    public String getObjectType() {
        return objectType;
    }
}
