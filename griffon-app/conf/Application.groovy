application {
    title = 'Addressbook'
    startupGroups = ['addressbook']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "exception"
    'exception' {
        model      = 'addressbook.ExceptionModel'
        view       = 'addressbook.ExceptionView'
        controller = 'addressbook.ExceptionController'
    }

    // MVC Group for "addressbook"
    'addressbook' {
        model      = 'addressbook.AddressbookModel'
        view       = 'addressbook.AddressbookView'
        controller = 'addressbook.AddressbookController'
    }
}
