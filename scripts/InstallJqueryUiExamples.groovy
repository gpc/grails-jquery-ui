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

includeTargets << grailsScript("Init")

target(main: "The description of the script goes here!") {
	copy(file:"${jqueryUiPluginDir}/grails-app/views/jquery-ui-examples.gsp", todir:"${basedir}/web-app", overwrite:"true")
	println '''
Example page successfully installed. You can now start your app and point your browser to /jquery-ui-examples.gsp
'''
}

setDefaultTarget(main)
