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

package jqueryui

/**
 * Taglib for jQuery UI.
 * @author Soenke Sothmann
 */
class JqueryuiTagLib {

  static namespace = "jqueryui"

  /**
   * Renders a jQuery UI dialog.
   */
  Closure dialog = {attrs, body ->
	// TODO Form-support: form="true"
    // TODO Form-support: controller="demo"
    // TODO Form-support: action="acceptForm"
    // TODO Form-support: update="replaceMe"
	// TODO Form-support: support defining event handlers for form submission, especially onSuccess.

	String id = attrs.remove('id')
	String title = attrs.remove('title')
	Map triggers = attrs.remove('triggers')
	String remoteContentUrl = attrs.remove('remoteContentUrl')
	if (!attrs.bgiframe) attrs.bgiframe = 'true'
	if (!attrs.modal) attrs.modal = 'false'
	if (!attrs.autoOpen) attrs.autoOpen = 'false'
	Map buttons = attrs.remove('buttons')
	assert id
	
	if(buttons){
		String buttonsString = '{ '
		List buttonStringList = []
		buttons.each{key, value ->
			buttonStringList << '"' + key + '": ' + value
		}
		buttonsString += buttonStringList.join(', ')
		buttonsString += ' }'
		attrs.buttons = buttonsString
	}
	if(remoteContentUrl){
		assert !attrs.open
		attrs.open = 'function() { $("#' + id + '").load("' + remoteContentUrl + '"); }'
	}

	out << "<div id=\"$id\""
	if(title) {
		out << " title=\"$title\""
	}
	out << '>'
	out << body()
	out << "</div>"
	List keyValuePairs = attrs.collect {key, value ->
	  return "$key: $value"
	}
	String jqueryString = '$(function() {' +
			'$("#' + id + '").dialog({' +
			keyValuePairs.join(', ')
	jqueryString += '  });'
	jqueryString += '  });'
	out << jq.jquery {
	  jqueryString
	}
	if(triggers){
	  renderDialogTriggers(id, triggers, out)
	}
  }

  private void renderDialogTriggers(String dialogId, Map triggers, out) {
	if (triggers.show) {
	  renderDialogShowTrigger(triggers, out, dialogId)
	}
	if (triggers.hide) {
	  renderDialogHideTrigger(triggers, out, dialogId)
	}
  }

  private void renderDialogHideTrigger(Map triggers, out, String dialogId) {
	String triggerId = null
	if (triggers.hide.id) {
	  triggerId = triggers.hide.id
	}
	else if (triggers.hide.type == 'link') {
	  assert triggers.hide.text
	  triggerId = "hideDialog_$dialogId"
	  out << "<a id=\"$triggerId\">$triggers.hide.text</a>&nbsp;"
	}
	else if (triggers.hide.type == 'button') {
		assert triggers.hide.text
		triggerId = "hideDialog_$dialogId"
		out << "<input type=\"button\" id=\"$triggerId\" value=\"$triggers.hide.text\"/>&nbsp;"
	  }
	assert triggerId
	String jqueryTriggerString
	if (triggers.hide.on == 'click') {
	  jqueryTriggerString = '$("#' + triggerId + '").click(function(event) { $("#' + dialogId + '").dialog("close"); });'
	}
	else if (triggers.hide.on == 'mouseover') {
	  jqueryTriggerString = '$("#' + triggerId + '").mouseover(function(event) { $("#' + dialogId + '").dialog("close"); });'
	}
	out << jq.jquery {
	  jqueryTriggerString
	}
  }

  private void renderDialogShowTrigger(Map triggers, out, String dialogId) {
	String triggerId = null
	if (triggers.show.id) {
	  triggerId = triggers.show.id
	}
	else if (triggers.show.type == 'link') {
	  assert triggers.show.text
	  triggerId = "showDialog_$dialogId"
	  out << "<a id=\"$triggerId\">$triggers.show.text</a>&nbsp;"
	}
	else if (triggers.show.type == 'button') {
		assert triggers.show.text
		triggerId = "showDialog_$dialogId"
		out << "<input type=\"button\" id=\"$triggerId\" value=\"$triggers.show.text\"/>&nbsp;"
	  }
	assert triggerId
	String jqueryTriggerString
	if (triggers.show.on == 'click') {
	  jqueryTriggerString = '$("#' + triggerId + '").click(function(event) { $("#' + dialogId + '").dialog("open"); });'
	}
	else if (triggers.show.on == 'mouseover') {
	  jqueryTriggerString = '$("#' + triggerId + '").mouseover(function(event) { $("#' + dialogId + '").dialog("open"); });'
	}
	out << jq.jquery {
	  jqueryTriggerString
	}
  }

  /**
   * Includes jQuery UI Stylesheet and javascript file.
   * Must be used in head-section.
   */
  Closure resources = {
	String pathToJQueryUICss = resource(dir:"css/jqueryui",file:'jquery-ui.css')
	out << "<link rel=\"stylesheet\" href=\"${pathToJQueryUICss}\" />\n"
	String pathToJQueryUIJavascript = resource(dir:"js/jqueryui",file:'jquery-ui.min.js')
	out << "<script type=\"text/javascript\" src=\"${pathToJQueryUIJavascript}\"></script>\n"
  }
}
