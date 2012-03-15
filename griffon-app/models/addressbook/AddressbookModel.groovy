package addressbook

import groovy.beans.Bindable
import ca.odell.glazedlists.*
import griffon.transform.PropertyListener
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener

class AddressbookModel {
    final EventList<ContactPresentationModel> contacts = new ObservableElementList<ContactPresentationModel>(
        GlazedLists.threadSafeList(new BasicEventList<ContactPresentationModel>()),
        GlazedLists.beanConnector(ContactPresentationModel)
    )
    
    final ContactPresentationModel currentContact = new ContactPresentationModel()
    
    @PropertyListener(selectionUpdater)
    @Bindable int selectedIndex = -1
    
    private selectionUpdater = { e ->
        currentContact.contact = contacts[selectedIndex].contact
    }
    
    AddressbookModel() {
        currentContact.addPropertyChangeListener(new ModelUpdater())
    }
    
    private class ModelUpdater implements PropertyChangeListener {
        void propertyChange(PropertyChangeEvent e) {
            if(e.propertyName == 'contact' || selectedIndex < 0) return
            contacts[selectedIndex][e.propertyName] = e.newValue
        }
    }

    void removeContact(Contact contact) {
        currentContact.contact = null
        ContactPresentationModel toDelete = contacts.find { it.contact == contact }
        if(toDelete != null) contacts.remove(toDelete)
    }
}