package addressbook

class AddressbookController {
    def model
    def storageService

    void mvcGroupInit(Map args) {
        execFuture {
            List<ContactPresentationModel> list = storageService.load().collect([]) {
                new ContactPresentationModel(contact: it)
            }
            execInsideUIAsync {
                model.contacts.addAll(list)
            }
        }
    }

    void newAction(evt) {
        model.selectedIndex = -1
        model.currentContact.contact = new Contact()
    }
    
    void saveAction(evt) {
        // push changes to domain object
        model.currentContact.updateContact()
        boolean isNew = model.currentContact.contact.id < 1
        // save to db
        storageService.store(model.currentContact.contact)
        // if is a new contact, add it to the list
        if(isNew) {
            model.contacts << new ContactPresentationModel(contact: model.currentContact.contact)
        }
    }
    
    void deleteAction(evt) {
        if(model.currentContact.contact && model.currentContact.contact.id) {
            // delete from db
            storageService.remove(model.currentContact.contact)
            // remove from contact list
            execInsideUIAsync {
                model.removeContact(model.currentContact.contact)
                model.selectedIndex = -1
            }
        }
    }
    
    void dumpAction(evt) {
        storageService.dump()
    }
}