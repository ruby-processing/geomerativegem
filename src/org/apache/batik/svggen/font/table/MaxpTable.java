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
 * @version $Id: MaxpTable.java,v 1.3 2004/08/18 07:15:21 vhardy Exp $
 * @author <a href="mailto:david@steadystate.co.uk">David Schweinsberg</a>
 */
public class MaxpTable implements Table {

  private final int versionNumber;
  private final int numGlyphs;
  private final int maxPoints;
  private final int maxContours;
  private final int maxCompositePoints;
  private final int maxCompositeContours;
  private final int maxZones;
  private final int maxTwilightPoints;
  private final int maxStorage;
  private final int maxFunctionDefs;
  private final int maxInstructionDefs;
  private final int maxStackElements;
  private final int maxSizeOfInstructions;
  private final int maxComponentElements;
  private final int maxComponentDepth;

  protected MaxpTable(DirectoryEntry de, RandomAccessFileEmulator raf) throws IOException {
    raf.seek(de.getOffset());
    versionNumber = raf.readInt();
    numGlyphs = raf.readUnsignedShort();
    maxPoints = raf.readUnsignedShort();
    maxContours = raf.readUnsignedShort();
    maxCompositePoints = raf.readUnsignedShort();
    maxCompositeContours = raf.readUnsignedShort();
    maxZones = raf.readUnsignedShort();
    maxTwilightPoints = raf.readUnsignedShort();
    maxStorage = raf.readUnsignedShort();
    maxFunctionDefs = raf.readUnsignedShort();
    maxInstructionDefs = raf.readUnsignedShort();
    maxStackElements = raf.readUnsignedShort();
    maxSizeOfInstructions = raf.readUnsignedShort();
    maxComponentElements = raf.readUnsignedShort();
    maxComponentDepth = raf.readUnsignedShort();
  }

  public int getMaxComponentDepth() {
    return maxComponentDepth;
  }

  public int getMaxComponentElements() {
    return maxComponentElements;
  }

  public int getMaxCompositeContours() {
    return maxCompositeContours;
  }

  public int getMaxCompositePoints() {
    return maxCompositePoints;
  }

  public int getMaxContours() {
    return maxContours;
  }

  public int getMaxFunctionDefs() {
    return maxFunctionDefs;
  }

  public int getMaxInstructionDefs() {
    return maxInstructionDefs;
  }

  public int getMaxPoints() {
    return maxPoints;
  }

  public int getMaxSizeOfInstructions() {
    return maxSizeOfInstructions;
  }

  public int getMaxStackElements() {
    return maxStackElements;
  }

  public int getMaxStorage() {
    return maxStorage;
  }

  public int getMaxTwilightPoints() {
    return maxTwilightPoints;
  }

  public int getMaxZones() {
    return maxZones;
  }

  public int getNumGlyphs() {
    return numGlyphs;
  }

  @Override
  public int getType() {
    return MAXP;
  }
}
