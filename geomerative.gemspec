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
  spec.licenses = %w{Apache-2.0 AGPL-3.0}
  spec.authors = %w{Ricard\ Marxer Martin\ Prout}
  spec.email = 'mamba2928@yahoo.co.uk'
  spec.homepage = 'http://ruby-processing.github.io/geomerativegem/'
  spec.files = `git ls-files -z`.split("\x0").reject { |f| f.match(%r{^(test|spec|features)/}) }
  spec.files << 'lib/geomerative.jar'
  spec.require_paths = ['lib']
  spec.add_development_dependency 'rake', '~> 12'
  # spec.add_development_dependency 'maven', '~> 3.3', '>= 3.3.3'
  spec.platform = 'java'
  spec.requirements << 'A decent graphics card'
  spec.requirements << 'java runtime >= 1.8+'
  spec.requirements << 'processing = 3.3.2+'
  spec.requirements << 'jruby_art = 1.2+'
end
