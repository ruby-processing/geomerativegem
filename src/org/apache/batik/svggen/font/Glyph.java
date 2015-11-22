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
package org.apache.batik.svggen.font;

import org.apache.batik.svggen.font.table.GlyfDescript;
import org.apache.batik.svggen.font.table.GlyphDescription;

/**
 * An individual glyph within a font.
 * @version $Id: Glyph.java,v 1.4 2004/08/18 07:15:18 vhardy Exp $
 * @author <a href="mailto:david@steadystate.co.uk">David Schweinsberg</a>
 */
public class Glyph {

    protected short leftSideBearing;
    protected int advanceWidth;
    private Point[] points;

    public Glyph(GlyphDescription gd, short lsb, int advance) {
        leftSideBearing = lsb;
        advanceWidth = advance;
        describe(gd);
    }

    public int getAdvanceWidth() {
        return advanceWidth;
    }

    public short getLeftSideBearing() {
        return leftSideBearing;
    }

    public Point getPoint(int i) {
        return points[i];
    }

    public int getPointCount() {
        return points.length;
    }

    /**
     * Resets the glyph to the TrueType table settings
     */
    public void reset() {
    }

    /**
     * @param factor a float value
     */
    public void scale(float factor) {
	if(points!=null){
            for (Point point : points) {
                point.x *= factor;
                point.y *= -factor;
            }
	}
	leftSideBearing = (short)((float)leftSideBearing * factor);
	advanceWidth = (int)((float)advanceWidth * factor);
    }

    /**
     * @param factor a 16.16 fixed value
     */
    /*
    public void scale(int factor) {
        for (int i = 0; i < points.length; i++) {
            //points[i].x = ( points[i].x * factor ) >> 6;
            //points[i].y = ( points[i].y * factor ) >> 6;
            points[i].x = ((points[i].x<<10) * factor) >> 26;
            points[i].y = ((points[i].y<<10) * factor) >> 26;
        }
        leftSideBearing = (short)(( leftSideBearing * factor) >> 6);
        advanceWidth = (advanceWidth * factor) >> 6;
    }
    */
    /**
     * Set the points of a glyph from the GlyphDescription
     */
    private void describe(GlyphDescription gd) {
        int endPtIndex = 0;
        points = new Point[gd.getPointCount() + 2];
        for (int i = 0; i < gd.getPointCount(); i++) {
            boolean endPt = gd.getEndPtOfContours(endPtIndex) == i;
            if (endPt) {
                endPtIndex++;
            }
            points[i] = new Point(
                    gd.getXCoordinate(i),
                    gd.getYCoordinate(i),
                    (gd.getFlags(i) & GlyfDescript.onCurve) != 0,
                    endPt);
        }

        // Append the origin and advanceWidth points (n & n+1)
        points[gd.getPointCount()] = new Point(0, 0, true, true);
        points[gd.getPointCount()+1] = new Point(advanceWidth, 0, true, true);
    }
}
