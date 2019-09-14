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
 * @version $Id: CmapFormat.java,v 1.4 2004/09/01 09:35:23 deweese Exp $
 * @author <a href="mailto:david@steadystate.co.uk">David Schweinsberg</a>
 */
public abstract class CmapFormat {

  protected int format;
  protected int length;
  protected int version;

  protected CmapFormat(RandomAccessFileEmulator raf) throws IOException {
    length = raf.readUnsignedShort();
    version = raf.readUnsignedShort();
  }

  protected static CmapFormat create(int format, RandomAccessFileEmulator raf)
    throws IOException {
    switch (format) {
      case 0:
        return new CmapFormat0(raf);
      case 2:
        return new CmapFormat2(raf);
      case 4:
        return new CmapFormat4(raf);
      case 6:
        return new CmapFormat6(raf);
    }
    return null;
  }

  public int getFormat() {
    return format;
  }

  public int getLength() {
    return length;
  }

  public int getVersion() {
    return version;
  }

  public abstract int mapCharCode(int charCode);

  public abstract int getFirst();

  public abstract int getLast();

  @Override
  public String toString() {
    return new StringBuilder()
      .append("format: ")
      .append(format)
      .append(", length: ")
      .append(length)
      .append(", version: ")
      .append(version).toString();
  }
}
