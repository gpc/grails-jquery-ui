import grails.util.Holders
// Resource declarations for Resources plugin
// This is a bit ugly, we'll find a way to make this better in future
def grailsPluginManager = Holders.getPluginManager()
def plugin = grailsPluginManager.getGrailsPlugin('jquery-ui')
def jqver = plugin.instance.JQUERYUI_VERSION

modules = {
    'jquery-theme' {
        resource id: 'theme',
                 url: [plugin: 'jqueryUi', dir: 'jquery-ui/themes/ui-lightness',
                       file: 'jquery-ui-' + jqver + '.custom.css'],
                       attrs: [media: 'screen, projection']
    }

    'jquery-ui' {
        dependsOn 'jquery', 'jquery-theme'

        resource id: 'js', url: [plugin: 'jqueryUi', dir: 'jquery-ui/js', file: "jquery-ui-${jqver}.custom.min.js"],
                 nominify: true, disposition: 'head'
    }

    'jquery-ui-dev' {
        dependsOn 'jquery', 'jquery-theme'

        resource id: 'js', url:[plugin: 'jqueryUi', dir:'jquery-ui/js', file:"jquery-ui-${jqver}.custom.js"]
    }
}
