package com.talentroc.t5.interview.pages.contact;

import com.talentroc.t5.interview.entities.Contact;
import com.talentroc.t5.interview.services.ContactManager;
import com.talentroc.t5.interview.utils.BusinessException;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

public class ContactIndex {
    @Inject
    private ContactManager contactManager;

    @Property
    private Contact contact;

    public List<Contact> getContacts() {
        return contactManager.retrieveAll();
    }
    
    /** Remove the contact from the contacts manager when clicking on the "edit" link, linked with the contact id. */
    public void onActionFromRemove(Contact contact)
    {
    	try
    	{
    		contactManager.delete(contact);
    	}
    	catch(BusinessException businessException)
    	{
    		businessException.printStackTrace();
    	}
    }
}
