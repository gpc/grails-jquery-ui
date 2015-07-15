// Resource declarations for Resources plugin

modules = {
	'jquery-ui-base-css' {
		resource url: [plugin: 'jqueryUi', dir: 'jquery-ui/css',
			file: 'jquery-ui.css'],	attrs: [media: 'screen, projection']
		resource url: [plugin: 'jqueryUi', dir: 'jquery-ui/css',
			file: 'jquery-ui.structure.css'], attrs: [media: 'screen, projection']
	}

	'jquery-theme' {
		dependsOn 'jquery-ui-base-css'

		resource id: 'theme', url: [plugin: 'jqueryUi', dir: 'jquery-ui/css',
			file: 'jquery-ui.ui-lightness.theme.css'], attrs: [media: 'screen, projection']
    }

	'jquery-ui' {
		dependsOn 'jquery', 'jquery-theme'

		resource id: 'js', url: [plugin: 'jqueryUi', dir: 'jquery-ui/js', file: "jquery-ui.min.js"],
		         nominify: true, disposition: 'head'
	}

	'jquery-ui-dev' {
		dependsOn 'jquery-dev', 'jquery-theme'

		resource id: 'js', url:[plugin: 'jqueryUi', dir:'jquery-ui/js', file:"jquery-ui.js"]
	}
}
