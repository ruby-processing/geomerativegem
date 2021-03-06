/*

   Copyright 2001-2003  The Apache Software Foundation 

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

import java.io.IOException;

import org.apache.batik.svggen.font.table.CmapTable;
import org.apache.batik.svggen.font.table.GlyfTable;
import org.apache.batik.svggen.font.table.HeadTable;
import org.apache.batik.svggen.font.table.HheaTable;
import org.apache.batik.svggen.font.table.HmtxTable;
import org.apache.batik.svggen.font.table.LocaTable;
import org.apache.batik.svggen.font.table.MaxpTable;
import org.apache.batik.svggen.font.table.NameTable;
import org.apache.batik.svggen.font.table.Os2Table;
import org.apache.batik.svggen.font.table.PostTable;
import org.apache.batik.svggen.font.table.Table;
import org.apache.batik.svggen.font.table.TableDirectory;
import org.apache.batik.svggen.font.table.TableFactory;

/**
 * The TrueType font.
 *
 * @version $Id: Font.java,v 1.6 2004/08/18 07:15:18 vhardy Exp $
 * @author <a href="mailto:david@steadystate.co.uk">David Schweinsberg</a>
 */
public class Font {

  private byte[] bs;
//    private Interpreter interp = null;
//    private Parser parser = null;
  private TableDirectory tableDirectory = null;
  private Table[] tables;
  private Os2Table os2;
  private CmapTable cmap;
  private GlyfTable glyf;
  private HeadTable head;
  private HheaTable hhea;
  private HmtxTable hmtx;
  private LocaTable loca;
  private MaxpTable maxp;
  private NameTable name;
  private PostTable post;

  /**
   * Constructor
   */
  public Font() {
  }

  public Table getTable(int tableType) {
    for (Table table : tables) {
      if ((table != null) && (table.getType() == tableType)) {
        return table;
      }
    }
    return null;
  }

  public Os2Table getOS2Table() {
    return os2;
  }

  public CmapTable getCmapTable() {
    return cmap;
  }

  public HeadTable getHeadTable() {
    return head;
  }

  public HheaTable getHheaTable() {
    return hhea;
  }

  public HmtxTable getHmtxTable() {
    return hmtx;
  }

  public LocaTable getLocaTable() {
    return loca;
  }

  public MaxpTable getMaxpTable() {
    return maxp;
  }

  public NameTable getNameTable() {
    return name;
  }

  public PostTable getPostTable() {
    return post;
  }

  public int getAscent() {
    return hhea.getAscender();
  }

  public int getDescent() {
    return hhea.getDescender();
  }

  public int getNumGlyphs() {
    return maxp.getNumGlyphs();
  }

  public Glyph getGlyph(int i) {
    return (glyf.getDescription(i) != null)
      ? new Glyph(
        glyf.getDescription(i),
        hmtx.getLeftSideBearing(i),
        hmtx.getAdvanceWidth(i))
      : null;
  }

  public TableDirectory getTableDirectory() {
    return tableDirectory;
  }

  /**
   * @param fontInBytes
   */
  protected void read(byte[] fontInBytes) {
    bs = fontInBytes;
    try {
      try (RandomAccessFileEmulator raf = new RandomAccessFileEmulator(bs, "r")) {
        tableDirectory = new TableDirectory(raf);
        tables = new Table[tableDirectory.getNumTables()];

        // Load each of the tables
        for (int i = 0; i < tableDirectory.getNumTables(); i++) {
          tables[i] = TableFactory.create(tableDirectory.getEntry(i), raf);
        }
      }

      // Get references to commonly used tables
      os2 = (Os2Table) getTable(Table.OS_2);
      cmap = (CmapTable) getTable(Table.CMAP);
      glyf = (GlyfTable) getTable(Table.GLYF);
      head = (HeadTable) getTable(Table.HEAD);
      hhea = (HheaTable) getTable(Table.HHEA);
      hmtx = (HmtxTable) getTable(Table.HMTX);
      loca = (LocaTable) getTable(Table.LOCA);
      maxp = (MaxpTable) getTable(Table.MAXP);
      name = (NameTable) getTable(Table.NAME);
      post = (PostTable) getTable(Table.POST);

      // Initialize the tables that require it
      hmtx.init(hhea.getNumberOfHMetrics(),
        maxp.getNumGlyphs() - hhea.getNumberOfHMetrics());
      loca.init(maxp.getNumGlyphs(), head.getIndexToLocFormat() == 0);
      glyf.init(maxp.getNumGlyphs(), loca);

    } catch (IOException e) {
    }
  }

  public static Font create() {
    return new Font();
  }

  /**
   * @param fontInBytes
   * @return
   */
  public static Font create(byte[] fontInBytes) {
    Font f = new Font();
    f.read(fontInBytes);
    return f;
  }
}
