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
import org.apache.batik.svggen.font.*;

/**
 *
 * @author <a href="mailto:david@steadystate.co.uk">David Schweinsberg</a>
 * @version $Id: KernSubtableFormat0.java,v 1.3 2004/08/18 07:15:21 vhardy Exp $
 */
public class KernSubtableFormat0 extends KernSubtable {

  private final int nPairs;
  private final int searchRange;
  private final int entrySelector;
  private final int rangeShift;
  private final KerningPair[] kerningPairs;

  /**
   * Creates new KernSubtableFormat0
   *
   * @param raf
   * @throws java.io.IOException
   */
  protected KernSubtableFormat0(RandomAccessFileEmulator raf) throws IOException {
    nPairs = raf.readUnsignedShort();
    searchRange = raf.readUnsignedShort();
    entrySelector = raf.readUnsignedShort();
    rangeShift = raf.readUnsignedShort();
    kerningPairs = new KerningPair[nPairs];
    for (int i = 0; i < nPairs; i++) {
      kerningPairs[i] = new KerningPair(raf);
    }
  }

  @Override
  public int getKerningPairCount() {
    return nPairs;
  }

  /**
   *
   * @param i
   * @return
   */
  @Override
  public KerningPair getKerningPair(int i) {
    return kerningPairs[i];
  }

}
