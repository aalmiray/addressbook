package addressbook

import groovy.beans.Bindable
import griffon.transform.PropertyListener

@PropertyListener(propertyUpdater)
class ContactPresentationModel {
    // attributes
    @Bindable String name
    @Bindable String lastname
    @Bindable String address
    @Bindable String company
    @Bindable String email
    
    // model reference
    @Bindable Contact contact = new Contact()
    
    private propertyUpdater = { e ->
        if(e.propertyName == 'contact') {
            for(property in Contact.PROPERTIES) {
                delegate[property] = e.newValue != null ? e.newValue[property] : null
            }
        }
    }

    String toString() { "$name $lastname" }

    void updateContact() {
        if(contact) {
            for(property in Contact.PROPERTIES) {
                contact[property] = this[property]
            }
        }
    }
}