/*

   Copyright 1999-2003  The Apache Software Foundation 

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
 * @version $Id: TableFactory.java,v 1.3 2004/10/30 18:38:06 deweese Exp $
 * @author <a href="mailto:david@steadystate.co.uk">David Schweinsberg</a>
 */
public class TableFactory {

  public static Table create(DirectoryEntry de, RandomAccessFileEmulator raf) throws IOException {
    Table t = null;
    switch (de.getTag()) {
      case Table.BASE:
        break;
      case Table.CFF:
        break;
      case Table.DSIG:
        break;
      case Table.EBDT:
        break;
      case Table.EBLC:
        break;
      case Table.EBSC:
        break;
      case Table.GDEF:
        break;
      case Table.GPOS:
        t = new GposTable(de, raf);
        break;
      case Table.GSUB:
        t = new GsubTable(de, raf);
        break;
      case Table.JSTF:
        break;
      case Table.LTSH:
        break;
      case Table.MMFX:
        break;
      case Table.MMSD:
        break;
      case Table.OS_2:
        t = new Os2Table(de, raf);
        break;
      case Table.PCLT:
        break;
      case Table.VDMX:
        break;
      case Table.CMAP:
        t = new CmapTable(de, raf);
        break;
      case Table.CVT:
        t = new CvtTable(de, raf);
        break;
      case Table.FPGM:
        t = new FpgmTable(de, raf);
        break;
      case Table.FVAR:
        break;
      case Table.GASP:
        break;
      case Table.GLYF:
        t = new GlyfTable(de, raf);
        break;
      case Table.HDMX:
        break;
      case Table.HEAD:
        t = new HeadTable(de, raf);
        break;
      case Table.HHEA:
        t = new HheaTable(de, raf);
        break;
      case Table.HMTX:
        t = new HmtxTable(de, raf);
        break;
      case Table.KERN:
        t = new KernTable(de, raf);
        break;
      case Table.LOCA:
        t = new LocaTable(de, raf);
        break;
      case Table.MAXP:
        t = new MaxpTable(de, raf);
        break;
      case Table.NAME:
        t = new NameTable(de, raf);
        break;
      case Table.PREP:
        t = new PrepTable(de, raf);
        break;
      case Table.POST:
        t = new PostTable(de, raf);
        break;
      case Table.VHEA:
        break;
      case Table.VMTX:
        break;
    }
    return t;
  }
}
