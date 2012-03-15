package addressbook

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Contact {
    long id
    String name
    String lastname
    String address
    String company
    String email
    
    String toString() { "$name $lastname : $email" }
    
    static final List<String> PROPERTIES = ['name', 'lastname', 'address', 'company', 'email']
}