---
layout: post
title:  "A first Sketch"
date:   2015-11-24 07:34:13
categories: geomerativegem update
---

### Requirements

Requires JRubyArt-1.3.1+ (hence jruby-9.0.8.0+)
or propane-2.3.2+

### Install the generative gem

```bash
jruby -S gem install geomerative # safe way
gem install geomerative # using rvm rbenv to use jruby instead of MRI
```

Create basic sketch

```bash
mkdir 'fred'
mkdir 'fred/data'
cp '/usr/share/fonts/TTF/FreeMono.ttf fred/data' # other fonts are available
# '/usr/share/fonts/TTF/LiberationMono-Bold.ttf' Mac and Windows paths differ
cd fred
k9 create fred 600 400
```

Edit sketch as follows:-

```ruby
require 'geomerative'

# Declare the objects we are going to use, so that they are accessible from
# setup and from draw (use '@' prefix in declare)
attr_reader :grp

def setup
  sketch_title 'Fred'
  RG.init(self) # important
  fill(255, 102, 0)
  # you could also use absolute path for fonts, then no need to copy
  @grp = RG.get_text('Hello World!', data_path('FreeMono.ttf'), 72, CENTER)
end

def draw
  background(255)
  translate(width / 2, height / 2)
  grp.draw
end

def settings
  size(600, 400)
end
```

![fred.png]({{site.github.url}}/assets/fred.png)

See more refined examples [here][examples], and even more [here][amon] including rotating text and text obeying physics.

Usage with propane is very similar see [examples here][here]

[examples]:https://github.com/ruby-processing/geomerativegem/tree/master/examples/
[amon]:http://www.creativeapplications.net/processing/generative-typography-processing-tutorial/
[here]:https://github.com/ruby-processing/propane-examples/tree/master/external_library/gem/geomerative/
