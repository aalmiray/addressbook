package addressbook

application(title: 'Addressbook',
  pack: true,
  resizable: false,
  locationByPlatform:true,
  iconImage: imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]) {
    menuBar {
        menu('Contacts') {
            controller.griffonClass.actionNames.each { name ->
                menuItem(getVariable(name))
            }            
        }
    }
    migLayout(layoutConstraints: 'fill')
    list(model: eventListModel(source: model.contacts), constraints: 'west, w 180!',
         border: titledBorder(title: 'Contacts'),
         selectionMode: ListSelectionModel.SINGLE_SELECTION,
         keyReleased: { e ->  // enter/return key
             if (e.keyCode != KeyEvent.VK_ENTER) return
             int index = e.source.selectedIndex
             if (index > -1) model.selectedIndex = index
         },
         mouseClicked: { e -> // double click
             if (e.clickCount != 2) return
             int index = e.source.locationToIndex(e.point)
             if (index > -1) model.selectedIndex = index
         })
    panel(constraints: 'center', border: titledBorder(title: 'Contact')) {
        migLayout(layoutConstraints: 'fill')
        for(propName in Contact.PROPERTIES) {
            label(text: GriffonNameUtils.getNaturalName(propName) + ':', constraints: 'right')
            textField(columns: 30, constraints: 'grow, wrap',
                text: bind(propName, source: model.currentContact, mutual: true))
        }
    }
    panel(constraints: 'east', border: titledBorder(title: 'Actions')) {
        migLayout()
        controller.griffonClass.actionNames.each { name ->
            button(getVariable(name), constraints: 'growx, wrap')
        }
    }
}