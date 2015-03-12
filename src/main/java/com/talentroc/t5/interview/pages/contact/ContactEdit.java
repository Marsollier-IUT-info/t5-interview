package com.talentroc.t5.interview.pages.contact;

import com.talentroc.t5.interview.entities.Contact;
import com.talentroc.t5.interview.pages.contact.ContactIndex;
import com.talentroc.t5.interview.services.ContactManager;
import com.talentroc.t5.interview.utils.BusinessException;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.ioc.annotations.Inject;

public class ContactEdit {

    @Property
    private Contact contact;
    
    /** The contacts index page will be returned on submit. */
    @InjectPage
    private ContactIndex contactIndex;
    
    /** Inject the current contact manager, in which new contacts will be added. */
    @Inject
    private ContactManager contactManager;
    
    void onActivate() {
        contact = new Contact();
    }

    Boolean onActivate(Contact contact) {
        this.contact = contact;
        return Boolean.TRUE;
    }
    
    /** On submit. */
    Object onSuccess()
    {
		/** The new contact is created in the contact manager. */
		try{
			contactManager.create(contact);
		}
		catch(BusinessException e)
		{
			e.printStackTrace();
		}
		
		/** The contacts index page is finally returned. */
		return contactIndex;
	}
}
