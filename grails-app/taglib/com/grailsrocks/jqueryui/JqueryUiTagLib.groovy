package com.grailsrocks.jqueryui

class JqueryUiTagLib {
    static namespace = "jqui"

    static writeAttrs( attrs, output) {
        // Output any remaining user-specified attributes
        attrs.each { k, v ->
           output << k
           output << '="'
           output << v.encodeAsHTML()
           output << '" '    
        }
    }

    static LINK_WRITERS = [
        js: { url, constants, attrs ->
            def o = new StringBuilder()
            o << "<script src=\"${url.encodeAsHTML()}\" "

            // Output info from the mappings
            writeAttrs(constants, o)
            writeAttrs(attrs, o)

            o << '></script>'
            return o    
        },
        
        link: { url, constants, attrs ->
            def o = new StringBuilder()
            o << "<link href=\"${url.encodeAsHTML()}\" "

            // Output info from the mappings
            writeAttrs(constants, o)
            writeAttrs(attrs, o)

            o << '/>'
            return o
        }
    ]
    
    static RESOURCE_MAPPINGS = [
        css:[type:"text/css", rel:'stylesheet'],
        rss:[type:'application/rss+xml', rel:'alternate'], 
        atom:[type:'application/atom+xml', rel:'alternate'], 
        favicon:[type:'image/x-icon', rel:'shortcut icon'],
        appleicon:[type:'image/x-icon', rel:'apple-touch-icon'],
        js:[writer:'js', type:'text/javascript']
    ]

    def resources = { attrs ->
        def theme = attrs.theme ?: 'ui-lightness'
        def ver = '1.8'
        out << resourceLink(plugin: attrs.theme ? null : 'jqueryUi', type:'css', dir:'jquery-ui/themes/'+theme, 
            file:'jquery-ui-'+ver+'.custom.css', media:'screen, projection')
        out << resourceLink(plugin: 'jqueryUi', type:'js', dir:'jquery-ui/js', 
            file:'jquery-ui-'+ver+'.custom.min.js')
    }

    def resourceLink = { attrs ->
        def t = attrs.remove('type')
        def typeInfo = [:] + RESOURCE_MAPPINGS[t]
        if (!typeInfo) {
            throwTagError "Unknown resourceLink type: ${t}"
        }
        def url = g.resource(plugin:attrs.remove('plugin'), dir:attrs.remove('dir'), file:attrs.remove('file'))
        
        def writerName = typeInfo.remove('writer')
        def writer = LINK_WRITERS[writerName ?: 'link']
        out << writer(url, typeInfo, attrs)
    }
}
