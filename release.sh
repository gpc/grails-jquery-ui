rm -rf target/release
mkdir target/release
cd target/release
git clone git@github.com:gpc/grails-jquery-ui.git
cd grails-jquery-ui
grails clean
grails compile

#grails publish-plugin --snapshot --stacktrace
grails publish-plugin --stacktrace
