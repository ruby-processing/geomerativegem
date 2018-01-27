KLASSES = %w[
  FastRClip RClip RClosest RCommand RContour RFont RG RGeomElem RGroup RMatrix
  RMesh RPath RPoint RPolygon RRectangle RSVG RShape RStrip RStyle
].freeze

if RUBY_PLATFORM == 'java'
  require_relative 'geomerative.jar'
  def import_class_list(list, string)
    list.each { |klass| java_import format(string, klass) }
  end
  geom_format = 'geomerative.%s'
  import_class_list(KLASSES, geom_format)
end
