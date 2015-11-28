# --------- GEOMERATIVE EXAMPLES ---------------
# //////////////////////////////////////////////
# Title   :   TypoGeo_Deform
# Date    :   31/08/2011
# Version :   v0.5
#
# This sketch deforms the text using noise as the underlying
# algorithm. mouseX & mouseY movement will change the amount
# & intensity of the noise values.
# Key 'f' = Switches animation on/off
# Key '+'  & '-' = Changes the diameter of our ellipse.
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
#
require 'geomerative'
require_relative 'font_agent'

attr_reader :myFont, :myGroup, :myPoints, :myText
attr_reader :myAgents, :step, :stopAnime

def settings
  size(800, 350)
  smooth
end

def setup
  sketch_title 'Bubbles'
  background(0)
  @step = 3
  @myText = 'BUBBLES'
  RG.init(self)
  @myFont = RFont.new('FreeSans.ttf', 113, CENTER)
  @stopAnime = false
  RCommand.setSegmentLength(10)
  RCommand.setSegmentator(RCommand::UNIFORMLENGTH)
  @myPoints = myFont.toGroup(myText).getPoints  
  @myAgents = myPoints.map { |point| FontAgent.new(location: Vec2D.new(point.x, point.y)) }
end


def draw
  translate(400, 205)
  background(0)
  fill(255)
  myAgents.each do |point|
    point.display(step: step)
    point.motion
  end
end

def key_pressed
  case key
  when 'f', 'F'
    @stopAnime = !stopAnime
    stopAnime ? no_loop : loop
  when '+'
    @step += 1
  when '-'
    @step -= 1
  end
end
