# coding: utf-8
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'geomerative/version'

Gem::Specification.new do |spec|
  spec.name = 'geomerative'
  spec.version = Geomerative::VERSION
  spec.has_rdoc = true
  spec.extra_rdoc_files = %w{README.md LICENSE.md}
  spec.summary = %q{Updated geomerative library for JRubyArt}
  spec.description =<<-EOS
  Geomerative java library wrapped in a rubygem. Updated to use String switch
  etc available since jdk8.
  EOS
  spec.licenses = %w{LGPL-3.0}
  spec.authors = %w{Ricard\ Marxer Martin\ Prout}
  spec.email = 'martin_p@lineone.net'
  spec.homepage = 'https://github.com/ruby-processing/geomerativegem'
  spec.files = `git ls-files -z`.split("\x0").reject { |f| f.match(%r{^(test|spec|features)/}) }
  spec.files << 'lib/geomerative.jar'
  spec.require_paths = ['lib']
  spec.add_dependency 'jruby_art', '~> 1.0' 
  spec.add_development_dependency "rake", "~> 10.0"
  spec.add_development_dependency "minitest", "~> 5.8"
  spec.platform = 'java'
  spec.requirements << 'A decent graphics card'
  spec.requirements << 'java runtime >= 1.8+'
  spec.requirements << 'processing = 3.0.1+'
  spec.requirements << 'maven = 3.3.3'
  spec.requirements << 'jruby_art = 1.0+'
end
