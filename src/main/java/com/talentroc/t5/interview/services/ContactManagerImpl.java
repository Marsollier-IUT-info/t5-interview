package com.talentroc.t5.interview.services;

import com.talentroc.t5.interview.entities.Contact;
import com.talentroc.t5.interview.utils.BusinessException;
import org.apache.tapestry5.jpa.annotations.CommitAfter;

import javax.persistence.EntityManager;
import java.util.List;

public class ContactManagerImpl implements ContactManager {

    private final EntityManager entityManager;

    public ContactManagerImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void validate(final Contact contact) throws BusinessException{
        // TODO - Ecrire le code de validation du contact.
    	
    	/** Server input check for the first name (maximum length : 50).
    	 *  If the first name is set ("contact.getFirstName()!=null")
    	 *  and exceeds 50 characters ("contact.getFirstName().length()>50"),
    	 *  a business exception is thrown and the contact is not created. */
    	if(contact.getFirstName()!=null && contact.getFirstName().length()>50)
    	{
    		throw new BusinessException("First name must not be longer than 50 characters.");
    	}
    	
    	/** Server input check for the last name (required field, minimum length : 3, maximum length : 50).
    	 *  If the last name is not set ("contact.getLastName()==null"),
    	 *  has less than 3 characters ("contact.getLastName().length()<3")
    	 *  or exceeds 50 characters ("contact.getLastName().length()>50"),
    	 *  a business exception is thrown, with the appropriate message, and the contact is not created. */
    	if(contact.getLastName()==null)
    	{
    		throw new BusinessException("Last name must be set.");
    	}
    	else
    	{
    		if(contact.getLastName().length()<3)
    		{
    			throw new BusinessException("Last name must not be shorter than 3 characters.");
    		}
    		if(contact.getLastName().length()>50)
    		{
    			throw new BusinessException("Last name must not be longer than 50 characters.");
    		}
    	}
    	
    	/** Server input check for the telephone number (required field, only digits, compulsory length : 10).
    	 *  If the telephone number is not set ("contact.getTelephone()==null"),
    	 *  the input expression is not a string of digits ("!contact.getTelephone().matches("\\d+")")
    	 *  or the number of input digits is not equal to 10 (contact.getTelephone().length()!=10),
    	 *  a business exception is thrown, with the appropriate message, and the contact is not created. */
    	if(contact.getTelephone()==null)
    	{
    		throw new BusinessException("Telephone number must be set.");
    	}
    	else
    	{
    		/** Telephone number input is checked with a regular expression ("\\d+" : only digits (without any restriction of range). */
    		if(!contact.getTelephone().matches("\\d+"))
    		{
    			throw new BusinessException("Only digits are allowed.");
    		}
    		else
    		{
    			if(contact.getTelephone().length()!=10)
        		{
        			throw new BusinessException("Telephone number must have 10 digits.");
        		}
    		}
    	}
    }

    @Override
    @CommitAfter
    public void create(final Contact contact) throws BusinessException {
        if (contact.getId() == null) {
            validate(contact);
            entityManager.persist(contact);
        } else {
            throw new BusinessException("This contact already exists in DB. Use update.");
        }
    }

    @Override
    @CommitAfter
    public void update(final Contact contact) throws BusinessException {
        if (contact.getId() == null) {
            throw new BusinessException("This contact does not exist in DB. Use create.");
        } else {
            validate(contact);
            entityManager.merge(contact);
        }
    }

    @Override
    @CommitAfter
    public void delete(final Contact contact) throws BusinessException {
        entityManager.remove(contact);
    }

    @Override
    public List<Contact> retrieveAll() {
        return entityManager.createNamedQuery(Contact.RETRIEVE_ALL).getResultList();
    }

    @Override
    public Contact retrieveById(final Long id) {
        return entityManager.find(Contact.class, id);
    }
}
