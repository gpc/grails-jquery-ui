grails.project.work.dir = 'target'

grails.project.dependency.resolver = 'maven'
grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		mavenLocal()
		grailsCentral()
		mavenCentral()
	}

	plugins {

		runtime ':jquery:1.11.1'

		build ':release:3.1.1', ':rest-client-builder:2.1.1', {
			export = false
		}
	}
}
