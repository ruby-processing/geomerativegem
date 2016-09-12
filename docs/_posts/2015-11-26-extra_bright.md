---
layout: post
title:  "Another tutorial example"
date:   2015-11-26 13:48:13
---

### Extra Bright


{% highlight ruby %}
# --------- GEOMERATIVE EXAMPLES ---------------
# //////////////////////////////////////////////
# Title   :   TypoGeo_ExtraBright
# Date    :   31/08/2011
# Version :   v0.5
#
# Interactive work which you need to play with.
#
# Code adapted from an original idea by Stéphane Buellet
# http://www.chevalvert.fr/
#
# Licensed under GNU General Public License (GPL) version 3.
# http://www.gnu.org/licenses/gpl.html
#
# A series of tutorials for using the Geomerative Library
# developed by Ricard Marxer.
# http://www.ricardmarxer.com/geomerative/
#
# More info on these tutorials and workshops at :
# www.freeartbureau.org/blog
# translated for JRubyArt by Martin Prout
require 'geomerative'
require_relative 'font_agent'

attr_reader :my_agents, :x_incr, :y_incr

def settings
  size(900, 350)
end

def setup
  sketch_title 'Extra Bright'
  background(0)
  my_text = 'EXTRA BRIGHT'
  @x_incr = 0.000005
  @y_incr = 0.000008
  RG.init(self)
  my_font = RFont.new(data_path('FreeSans.ttf'), 97, CENTER)
  RCommand.set_segment_length(1)
  RCommand.set_segmentator(RCommand::UNIFORMLENGTH)
  @my_agents = my_font.to_group(my_text).get_points.map do |point|
    FontAgent.new(
      loc: Vec2D.new(point.x, point.y),
      increment: Vec2D.new(x_incr, y_incr)
    )
  end
end

def draw
  translate(width / 2, height / 2)
  background(0)
  my_agents.each do |point|
    m_point = point.motion
    xr = (((100 / m_point) * 2) + mouse_x) - width / 2
    yr = (((100 / m_point) * 2) + mouse_y) - height / 2
    point.display(xr: xr, yr: yr, m_point: m_point)
  end
end
{% endhighlight %}

### FontAgent class
{% highlight ruby %}
# FontAgent class handles motion and display
class FontAgent
  include Processing::Proxy # gives java 'inner class like' access to App
  attr_reader :loc, :offset, :increment

  def initialize(loc:, increment:)
    @loc = loc.copy
    @offset = Vec2D.new
    @increment = increment
  end

  def motion
    @offset += increment
    loc.dist(Vec2D.new(noise(offset.x) * width, noise(offset.y) * height))
  end

  def display(xr:, yr:, m_point:)
    no_stroke
    fill(255, 73)
    dia = (150 / m_point) * 5
    # to get weird non-deterministic behaviour of original, created by use of
    # negative inputs to a random range surely not intended, use original:-
    # ellipse(loc.x + random(-xr, xr), loc.y + random(-yr, yr), dia, dia)
    xr *= -1 if xr < 0 # guards against an invalid hi..low range
    yr *= -1 if yr < 0
    ellipse(loc.x + rand(-xr..xr), loc.y + rand(-yr..yr), dia, dia)
  end
end
{% endhighlight %}

![bright.png]({{ site.github.url }}/assets/bright.png)
