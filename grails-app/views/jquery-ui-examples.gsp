<%--
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 /
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>jQuery UI Grails Plugin Demo</title>
  <link type="text/css" rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"/>
  <g:javascript library="jquery"/>
  <jqueryui:resources />
</head>
<body>
<div class="body">
</div>
<h1>Dialog</h1>
<h2>Auto opened dialog with hide-trigger only</h2>
<p>
<i>No triggers ...</i>
<jqueryui:dialog id="dialog1" title="auto opened modal dialog title" autoOpen="true" modal="true">
	<span>auto opened modal dialog content</span>
</jqueryui:dialog>
</p>

<h2>Link click triggered dialog</h2>
<p>
<jqueryui:dialog id="dialog2" title="Link click triggered dialog title" triggers="[show:[type:'link', text:'Press to show dialog', on:'click'], hide:[type:'link', text:'Press to hide dialog', on:'click']]">
	<span>Link click triggered dialog content</span>
</jqueryui:dialog>
</p>

<h2>Link mouseover triggered dialog</h2>
<p>
<jqueryui:dialog id="dialog3" title="Link mouseover triggered dialog title" autoOpen="false" triggers="[show:[type:'link', text:'Mouseover to show dialog', on:'mouseover'], hide:[type:'link', text:'Mouseover to hide dialog', on:'mouseover']]">
	<span>Link mouseover triggered dialog content</span>
</jqueryui:dialog>
</p>

<h2>Existing element click triggered dialog</h2>
<p>
<ul>
  <li id="show_dialog4">Click to show dialog</li>
  <li id="hide_dialog4">Click to hide dialog</li>
</ul>
<jqueryui:dialog id="dialog4" title="Existing element click triggered dialog title" triggers="[show:[id:'show_dialog4', on:'click'], hide:[id:'hide_dialog4', on:'click']]">
	<span>Existing element click triggered dialog content</span>
</jqueryui:dialog>
</p>

<h2>Button click triggered dialog</h2>
<p>
  <jqueryui:dialog id="dialog5" title="Button click triggered dialog title" triggers="[show:[type:'button', text:'Press to show dialog', on:'click'], hide:[type:'button', text:'Press to hide dialog', on:'click']]">
	<span>Button click triggered dialog content</span>
  </jqueryui:dialog>
</p>

<h2>Button mouseover triggered dialog</h2>
<p>
  <jqueryui:dialog id="dialog6" title="Button mouseover triggered dialog title" triggers="[show:[type:'button', text:'Mouseover to show dialog', on:'mouseover'], hide:[type:'button', text:'Mouseover to hide dialog', on:'mouseover']]">
	<span>Button mouseover triggered dialog content</span>
  </jqueryui:dialog>
</p>

<h2>Configuriation pass-through</h2>
<p>
<jqueryui:dialog id="dialog7" foo="'bar'">
	<span>Dialog configuration pass-through</span>
</jqueryui:dialog>
There are no triggers for this dialog - just to test configuration pass-through.
</p>

<h2>Buttons</h2>
<p>
<jqueryui:dialog id="dialog8" title="Dialog with buttons title" buttons="[ OK:'function() { $(this).dialog(\'close\'); }', HELP:'function() { alert(\'Sample help text\'); }' ]" triggers="[show:[type:'link', text:'Click to show dialog', on:'click']]">
	<span>Dialog with buttons content</span>
</jqueryui:dialog>
</p>

<h2>Remote content loading</h2>
<p>
<jqueryui:dialog id="dialog9" title="Dialog using remote content loading" triggers="[show:[type:'link', text:'Click to show dialog', on:'click']]" remoteContentUrl="dialogContents.html">
	<span>Loading ...</span>
</jqueryui:dialog>
</p>

</body>
</html>
