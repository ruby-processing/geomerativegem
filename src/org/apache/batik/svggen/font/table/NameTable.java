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
 * @version $Id: NameTable.java,v 1.3 2004/08/18 07:15:22 vhardy Exp $
 * @author <a href="mailto:david@steadystate.co.uk">David Schweinsberg</a>
 */
public class NameTable implements Table {

  private final short formatSelector;
  private final short numberOfNameRecords;
  private final short stringStorageOffset;
  private final NameRecord[] records;

  protected NameTable(DirectoryEntry de, RandomAccessFileEmulator raf) throws IOException {
    raf.seek(de.getOffset());
    formatSelector = raf.readShort();
    numberOfNameRecords = raf.readShort();
    stringStorageOffset = raf.readShort();
    records = new NameRecord[numberOfNameRecords];

    // Load the records, which contain the encoding information and string offsets
    for (int i = 0; i < numberOfNameRecords; i++) {
      records[i] = new NameRecord(raf);
    }

    // Now load the strings
    for (int i = 0; i < numberOfNameRecords; i++) {
      records[i].loadString(raf, de.getOffset() + stringStorageOffset);
    }
  }

  public String getRecord(short nameId) {

    // Search for the first instance of this name ID
    for (int i = 0; i < numberOfNameRecords; i++) {
      if (records[i].getNameId() == nameId) {
        return records[i].getRecordString();
      }
    }
    return "";
  }

  @Override
  public int getType() {
    return NAME;
  }
}
