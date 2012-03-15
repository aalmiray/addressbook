package addressbook

class StorageService {
    List<Contact> load() {
        withSql { dsName, sql ->
            List tmpList = []
            sql.eachRow('SELECT * FROM contacts') { rs ->
                tmpList << new Contact(
                    id:       rs.id,
                    name:     rs.name,
                    lastname: rs.lastname,
                    address:  rs.address,
                    company:  rs.company,
                    email:    rs.email
                )
            }
            tmpList
        }
    }

    void store(Contact contact) {
        if(contact.id < 1) {
            // save
            withSql { dsName, sql ->
                contact.id = (sql.firstRow('select max(id) max from contacts').max as long) + 1
                List params = [contact.id]
                for(property in Contact.PROPERTIES) {
                    params << contact[property]
                }
                String columnNames = 'id, ' + Contact.PROPERTIES.join(', ')
                String placeHolders = (['?'] * (Contact.PROPERTIES.size() + 1)).join(',')
                sql.execute('insert into contacts ('+ columnNames +') values ('+ placeHolders +')', params)
            }
        } else {
            // update
            withSql { dsName, sql ->
                List params = []
                for(property in Contact.PROPERTIES) {
                    params << contact[property]
                }
                params << contact.id
                String clauses = Contact.PROPERTIES.collect([]) { prop ->
                    "$prop = ?"
                }.join(', ')
                sql.execute('update contacts set '+ clauses +' where id = ?', params)
            }
        }
    }
    
    void remove(Contact contact) {
        withSql { dsName, sql ->
            sql.execute('delete from contacts where id = ?', [contact.id])
        }
    }
    
    void dump() {
        withSql { dsName, sql ->
            sql.eachRow('SELECT * FROM contacts') { rs ->
                println rs
            }        
        }
    }
}