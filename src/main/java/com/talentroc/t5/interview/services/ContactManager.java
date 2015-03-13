package com.talentroc.t5.interview.services;

import com.talentroc.t5.interview.entities.Contact;
import com.talentroc.t5.interview.utils.BusinessException;

import java.util.List;

public interface ContactManager {

    /**
     * Validates a contact entry.
     *
     * @param contact
     *         contact to validate
     * @throws com.talentroc.t5.interview.utils.BusinessException
     *         on validation error.
     */
    void validate(Contact contact) throws BusinessException;

    /**
     * Creates a contact entry
     *
     * @param contact
     *         contact to create
     */
    void create(Contact contact) throws BusinessException;


    /**
     * Update a contact entry
     *
     * @param contact
     *         contact to update
     */
    void update(Contact contact) throws BusinessException;


    /**
     * Deletes a contact entry
     *
     * @param contact
     *         contact to delete
     */
    void delete(Contact contact) throws BusinessException;

    ;

    /**
     * Retrieves all contacts
     *
     * @return all contacts
     */
    List<Contact> retrieveAll();

    /**
     * Retrieves a contact by its id
     *
     * @param id
     *         id of the contact to retrieve
     * @return the contact corresponding to the id
     */
    Contact retrieveById(Long id);
}
