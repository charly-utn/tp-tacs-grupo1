package org.tptacs.domain.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

    private final String message = "El recurso %s con id %s no existe";
    private final String id;
    private final String resource;
    
    public ResourceNotFoundException(String id, String resource)  {
        super();
        this.id = id;
        this.resource = resource;
    }

    @Override
    public String getMessage() {
        return String.format(this.message, this.resource, this.id);
    }

}
