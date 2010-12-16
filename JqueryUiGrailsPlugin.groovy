class JqueryUiGrailsPlugin {
    // the plugin version
    def version = "1.8.7"

    // @todo This MUST be updated when you update the version of jquery ui
    static JQUERYUI_VERSION = "1.8.7"

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.2 > *"
    // the other plugins this plugin depends on
    def dependsOn = [jquery:'1.4.4 > *']
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "Marc Palmer"
    def authorEmail = "marc@grailsrocks.com"
    def title = "jQuery UI resources"
    def description = '''\\
Simply supplies jQuery UI resources, depends on jQuery plugin. Use this plugin to avoid resource duplication and conflicts.
'''

    def documentation = "http://grails.org/plugin/jquery-ui"
}
