/*

   Copyright 2001  The Apache Software Foundation 

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

import java.io.IOException;
import org.apache.batik.svggen.font.RandomAccessFileEmulator;

/**
 * Coverage Index (GlyphID) = StartCoverageIndex + GlyphID - Start GlyphID
 *
 * @author <a href="mailto:david@steadystate.co.uk">David Schweinsberg</a>
 * @version $Id: RangeRecord.java,v 1.3 2004/08/18 07:15:22 vhardy Exp $
 */
public class RangeRecord {

  private final int start;
  private final int end;
  private final int startCoverageIndex;

  /**
   * Creates new RangeRecord
   *
   * @param raf
   * @throws java.io.IOException
   */
  public RangeRecord(RandomAccessFileEmulator raf) throws IOException {
    start = raf.readUnsignedShort();
    end = raf.readUnsignedShort();
    startCoverageIndex = raf.readUnsignedShort();
  }

  public boolean isInRange(int glyphId) {
    return (start <= glyphId && glyphId <= end);
  }

  public int getCoverageIndex(int glyphId) {
    if (isInRange(glyphId)) {
      return startCoverageIndex + glyphId - start;
    }
    return -1;
  }

}
