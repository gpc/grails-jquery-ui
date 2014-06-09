grails.project.work.dir = 'target'

grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsCentral()
		mavenLocal()
		mavenCentral()
	}

	dependencies {
	}

	plugins {

		runtime ':jquery:1.11.1'

		build ':release:3.0.1', ':rest-client-builder:2.0.1', {
			export = false
		}
	}
}
