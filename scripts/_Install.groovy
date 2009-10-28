/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
//
//    ant.mkdir(dir:"${basedir}/grails-app/jobs")
//

Ant.property(environment: "env")
grailsHome = Ant.antProject.properties."env.GRAILS_HOME"

includeTargets << grailsScript("Init")
checkVersion()
configureProxy()

String jQueryUIVersion = '1.7.2'

Ant.sequential {
  event("StatusUpdate", ["Downloading jQuery UI ${jQueryUIVersion} into project"])

  String fileToDownload = "jquery-ui-${jQueryUIVersion}.zip"
  String tempInstallDir = "${basedir}/jqueryui-install-temp"

  mkdir(dir: "${tempInstallDir}")
  get(dest: "$tempInstallDir/$fileToDownload",
		  src: "http://jquery-ui.googlecode.com/files/$fileToDownload",
		  verbose: true)

  unzip(dest: "${tempInstallDir}",
		  src: "${tempInstallDir}/${fileToDownload}")

  mkdir(dir: "${basedir}/web-app/js/jqueryui")
  mkdir(dir: "${basedir}/web-app/images/jqueryui")
  mkdir(dir: "${basedir}/web-app/css/jqueryui")
  copy(todir: "${basedir}/web-app/js/jqueryui/") {
	fileset(dir: "${tempInstallDir}/jquery-ui-${jQueryUIVersion}/ui/minified", includes: "jquery-ui.min.js")
  }
  copy(todir: "${basedir}/web-app/css/jqueryui/") {
	fileset(dir: "${tempInstallDir}/jquery-ui-${jQueryUIVersion}/themes/base", includes: "jquery-ui.css")
  }
  File cssFile = new File("${basedir}/web-app/css/jqueryui/jquery-ui.css")
  cssFile.text = cssFile.text.replaceAll('url\\(images/ui-', 'url\\(\\.\\./\\.\\./images/jqueryui/ui-') /* fix path to images in css file */
  copy(todir: "${basedir}/web-app/images/jqueryui") {
	fileset(dir: "${tempInstallDir}/jquery-ui-${jQueryUIVersion}/themes/base/images", includes: "**/**")
  }
  delete(dir:"${tempInstallDir}")
}
event("StatusFinal", ["jQuery ${jQueryUIVersion} installed successfully"])
