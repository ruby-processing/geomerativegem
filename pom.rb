project 'geomerative' do

  model_version '4.0.0'
  id 'ruby-processing:geomerative:2.0.0'
  packaging 'jar'

  description 'geomerative-library for JRubyArt'

  organization 'ruby-processing', 'https://ruby-processing.github.io'

  developer 'monkstone' do
    name 'Martin Prout'
    email 'mamba2928@yahoo.co.uk'
    roles 'developer'
  end

  license 'GPL 3', 'http://www.gnu.org/licenses/gpl-3.0-standalone.html'

  issue_management 'https://github.com/ruby-processing/geomerative/issues', 'Github'

  source_control( :url => 'https://github.com/ruby-processing/geomerative',
                  :connection => 'scm:git:git://github.com/ruby-processing/geomerative.git',
                  :developer_connection => 'scm:git:git@github.com:ruby-processing/geomerative.git' )

  properties( 'maven.compiler.source' => '1.8',
              'project.build.sourceEncoding' => 'UTF-8',
              'maven.compiler.target' => '12',
              'polyglot.dump.pom' => 'pom.xml'
            )

  jar 'org.processing:core:3.3.6'

  plugin( :compiler, '3.8.1',
          'release' =>  '11' )
  plugin( :jar, '3.1.1',
          'archive' => {
            'manifestFile' =>  'MANIFEST.MF'
          } )
  plugin :resources, '2.7'

  build do
    default_goal 'package'
    source_directory 'src'
    final_name 'geomerative'
  end

  reporting do
    plugin( :pmd, '3.12',
      'linkXRef' =>  'true',
      'sourceEncoding' =>  'utf-8',
      'minimumTokens' =>  '100',
      'targetJdk' => '${maven.compiler.target}' )
    plugin( :checkstyle, '3.1.0',
      'configLocation' =>  'config/sun_checks.xml' )
  end
end
