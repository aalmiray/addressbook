package addressbook

panel(id: 'content') {
    migLayout(layoutConstraints: 'fill')
    label('An unxpected error occurred', constraints: 'wrap')
    scrollPane(constraints: 'w 600, h 600, grow, wrap') {
        textArea(text: bind{ model.message },
            caretPosition: bind(source: model, 'message', converter: {0i}))
    }
    button(hideAction, constraints: 'tag ok')
    
    keyStrokeAction(component: current,
        keyStroke: 'ESCAPE',
        condition: 'in focused window',
        action: hideAction)
}
