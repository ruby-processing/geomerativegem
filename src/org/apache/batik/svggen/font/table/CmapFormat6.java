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
 * @version $Id: CmapFormat6.java,v 1.4 2004/09/01 09:35:23 deweese Exp $
 * @author <a href="mailto:david@steadystate.co.uk">David Schweinsberg</a>
 */
public class CmapFormat6 extends CmapFormat {

  private final short cformat;
  private short clength;
  private short cversion;
  private short firstCode;
  private short entryCount;
  private short[] glyphIdArray;

  protected CmapFormat6(RandomAccessFileEmulator raf) throws IOException {
    super(raf);
    cformat = 6;
  }

  @Override
  public int getFirst() {
    return 0;
  }

  /**
   *
   * @return
   */
  @Override
  public int getLast() {
    return 0;
  }

  /**
   *
   * @param charCode
   * @return
   */
  @Override
  public int mapCharCode(int charCode) {
    return 0;
  }
}
