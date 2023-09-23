package org.tptacs.domain.exceptions;

public class ResourceNotFoundException extends NotFoundException {

	private static final long serialVersionUID = 1L;
    
    public ResourceNotFoundException(String id, String resource)  {
        super(id, resource);
    }

}
