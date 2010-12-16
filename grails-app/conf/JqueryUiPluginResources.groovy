// Resource declarations for Resources plugin
// This is a bit ugly, we'll find a way to make this better in future
def appCtx = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext
def plugin = appCtx.pluginManager.getGrailsPlugin('jquery-ui')
def jqver = plugin.instance.JQUERYUI_VERSION

modules = {
    'jquery-ui' {
        dependsOn 'jquery'
        
        resource id:'theme',
            url:[ plugin: 'jqueryUi', dir: 'jquery-ui/themes/ui-lightness',
                  file:'jquery-ui-'+jqver+'.custom.css'], 
            attrs:[media:'screen, projection']
            
        resource id:'js', url:[plugin: 'jqueryUi', dir:'jquery-ui/js', file:"jquery-ui-${jqver}.custom.min.js"],
            nominify: true
    }

    'jquery-ui-dev' {
        dependsOn 'jquery'

        resource id:'theme',
            url:[ plugin: 'jqueryUi', dir: 'jquery-ui/themes/ui-lightness',
                  file:'jquery-ui-'+jqver+'.custom.css'], 
            attrs:[media:'screen, projection']
            
        resource id:'js', url:[plugin: 'jqueryUi', dir:'jquery-ui/js', file:"jquery-ui-${jqver}.custom.js"]
    }
}