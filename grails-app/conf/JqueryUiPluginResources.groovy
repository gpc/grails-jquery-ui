// Resource declarations for Resources plugin
// This is a bit ugly, we'll find a way to make this better in future
def appCtx = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext
def plugin = appCtx.pluginManager.getGrailsPlugin('jquery-ui')
def jqver = plugin.instance.JQUERYUI_VERSION

modules = {
    'jquery-theme' {
        resource id:'theme',
            url:[ plugin: 'jqueryUi', dir: 'jquery-ui/themes/ui-lightness',
                  file:'jquery-ui-'+jqver+'.custom.min.css'],
            attrs:[media:'screen, projection']
    }

    'jquery-theme-dev' {
        resource id:'theme',
            url:[ plugin: 'jqueryUi', dir: 'jquery-ui/themes/ui-lightness',
                  file:'jquery-ui-'+jqver+'.custom.css'],
            attrs:[media:'screen, projection']
    }

    'jquery-ui' {
        dependsOn 'jquery', 'jquery-theme'
        
        resource id:'js', url:[plugin: 'jqueryUi', dir:'jquery-ui/js', file:"jquery-ui-${jqver}.custom.min.js"],
            nominify: true, disposition: 'head'
    }

    'jquery-ui-dev' {
        dependsOn 'jquery', 'jquery-theme-dev'

        resource id:'js', url:[plugin: 'jqueryUi', dir:'jquery-ui/js', file:"jquery-ui-${jqver}.custom.js"]
    }

    'jquery-ui-i18n' {
        dependsOn 'jquery'

        resource id: 'js', url: [plugin: 'jqueryUi', dir: 'js/jquery/i18n', file: 'jquery-ui-i18n.js']
    }
}