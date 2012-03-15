import groovy.sql.Sql


class BootstrapGsql {
    def init = { String dataSourceName = 'default', Sql sql ->
        def contacts = sql.dataSet('contacts')
        contacts.add(
            id: 1,
            name: 'Andres',
            lastname: 'Almiray',
            address: 'Kirschgartenstrasse 5 CH-4051 Switzerland',
            company: 'Canno Engineering AG',
            email: 'andres.almiray@canoo.com'
        )
    }

    def destroy = { String dataSourceName = 'default', Sql sql ->
    }
} 
