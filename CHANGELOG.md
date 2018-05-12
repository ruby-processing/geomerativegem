### version-1.0.2
Bump versions processing-3.3.7 (changes to smooth) , jruby-9.1.17.0

### version-1.0.1
Bump versions processing-3.3.6, jruby-9.1.15.0

### version-1.0.0
Bump versions processing-3.3.4, jruby-9.1.12.0 about time we bumped to a full release version number

### version-0.4.3
Bump versions processing-3.3.2 removed JRubyArt dependency since can be used with propane as well

### version-0.4.2
Bump versions processing-3.3, jruby-9.1.8.0

### version-0.4.1
No particular reason but java, jruby and processing have all been updated since last release

### version-0.4.0
Somewhat arbitarily require JRubyArt-1.2+, but idea is get everyone on same page

### version-0.3.1
Submit for travis build

### version-0.3
Further mods to java code, mainly in this case removing PConstants indirection, which is probably harmful (downside if people expect to use the RG prefix it won't work). Still need to run with --nojruby flag, generally a permissions thing.
