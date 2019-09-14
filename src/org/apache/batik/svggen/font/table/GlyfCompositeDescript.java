/*

   Copyright 2001,2003  The Apache Software Foundation 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.batik.svggen.font.table;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Glyph description for composite glyphs. Composite glyphs are made up of one
 * or more simple glyphs, usually with some sort of transformation applied to
 * each.
 *
 * @version $Id: GlyfCompositeDescript.java,v 1.5 2004/08/18 07:15:20 vhardy Exp
 * $
 * @author <a href="mailto:david@steadystate.co.uk">David Schweinsberg</a>
 */
public class GlyfCompositeDescript extends GlyfDescript {

  private final ArrayList<GlyfCompositeComp> components;

  public GlyfCompositeDescript(GlyfTable parentTable,
    ByteArrayInputStream bais) {
    super(parentTable, (short) -1, bais);
    this.components = new ArrayList<>();

    // Get all of the composite components
    GlyfCompositeComp comp;
    int firstIndex = 0;
    int firstContour = 0;
    do {
      comp = new GlyfCompositeComp(firstIndex, firstContour, bais);
      components.add(comp);

      GlyphDescription desc;
      desc = parentTable.getDescription(comp.getGlyphIndex());
      if (desc != null) {
        firstIndex += desc.getPointCount();
        firstContour += desc.getContourCount();
      }
    } while ((comp.getFlags() & GlyfCompositeComp.MORE_COMPONENTS) != 0);

    // Are there hinting intructions to read?
    if ((comp.getFlags() & GlyfCompositeComp.WE_HAVE_INSTRUCTIONS) != 0) {
      readInstructions(bais, (bais.read() << 8 | bais.read()));
    }
  }

  @Override
  public int getEndPtOfContours(int i) {
    GlyfCompositeComp c = getCompositeCompEndPt(i);
    if (c != null) {
      GlyphDescription gd = parentTable.getDescription(c.getGlyphIndex());
      return gd.getEndPtOfContours(i - c.getFirstContour()) + c.getFirstIndex();
    }
    return 0;
  }

  @Override
  public byte getFlags(int i) {
    GlyfCompositeComp c = getCompositeComp(i);
    if (c != null) {
      GlyphDescription gd = parentTable.getDescription(c.getGlyphIndex());
      return gd.getFlags(i - c.getFirstIndex());
    }
    return 0;
  }

  @Override
  public short getXCoordinate(int i) {
    GlyfCompositeComp c = getCompositeComp(i);
    if (c != null) {
      GlyphDescription gd = parentTable.getDescription(c.getGlyphIndex());
      int n = i - c.getFirstIndex();
      int x = gd.getXCoordinate(n);
      int y = gd.getYCoordinate(n);
      short x1 = (short) c.scaleX(x, y);
      x1 += c.getXTranslate();
      return x1;
    }
    return 0;
  }

  @Override
  public short getYCoordinate(int i) {
    GlyfCompositeComp c = getCompositeComp(i);
    if (c != null) {
      GlyphDescription gd = parentTable.getDescription(c.getGlyphIndex());
      int n = i - c.getFirstIndex();
      int x = gd.getXCoordinate(n);
      int y = gd.getYCoordinate(n);
      short y1 = (short) c.scaleY(x, y);
      y1 += c.getYTranslate();
      return y1;
    }
    return 0;
  }

  @Override
  public boolean isComposite() {
    return true;
  }

  @Override
  public int getPointCount() {
    GlyfCompositeComp c = (GlyfCompositeComp) components.get(components.size() - 1);
    return c.getFirstIndex() + parentTable.getDescription(c.getGlyphIndex()).getPointCount();
  }

  @Override
  public int getContourCount() {
    GlyfCompositeComp c = (GlyfCompositeComp) components.get(components.size() - 1);
    return c.getFirstContour() + parentTable.getDescription(c.getGlyphIndex()).getContourCount();
  }

  public int getComponentIndex(int i) {
    return ((GlyfCompositeComp) components.get(i)).getFirstIndex();
  }

  public int getComponentCount() {
    return components.size();
  }

  protected GlyfCompositeComp getCompositeComp(int i) {
    GlyfCompositeComp c;
    for (Object component : components) {
      c = (GlyfCompositeComp) component;
      GlyphDescription gd = parentTable.getDescription(c.getGlyphIndex());
      if (c.getFirstIndex() <= i && i < (c.getFirstIndex() + gd.getPointCount())) {
        return c;
      }
    }
    return null;
  }

  protected GlyfCompositeComp getCompositeCompEndPt(int i) {
    GlyfCompositeComp c;
    for (Object component : components) {
      c = (GlyfCompositeComp) component;
      GlyphDescription gd = parentTable.getDescription(c.getGlyphIndex());
      if (c.getFirstContour() <= i && i < (c.getFirstContour() + gd.getContourCount())) {
        return c;
      }
    }
    return null;
  }
}
