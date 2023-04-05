package softuni.carrepairhistory.models.exception;

public class ObjectNotFoundException extends RuntimeException {
    private final Long objectId;

    private final String objectType;

    public ObjectNotFoundException(Long objectId, String objectType) {

        super("Object with ID " + objectId + " and type" + objectType + " not found");

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
