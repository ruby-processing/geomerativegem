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
 * @version $Id: Os2Table.java,v 1.3 2004/08/18 07:15:22 vhardy Exp $
 * @author <a href="mailto:david@steadystate.co.uk">David Schweinsberg</a>
 */
public class Os2Table implements Table {

    private final int version;
    private final short xAvgCharWidth;
    private final int usWeightClass;
    private final int usWidthClass;
    private final short fsType;
    private final short ySubscriptXSize;
    private final short ySubscriptYSize;
    private final short ySubscriptXOffset;
    private final short ySubscriptYOffset;
    private final short ySuperscriptXSize;
    private final short ySuperscriptYSize;
    private final short ySuperscriptXOffset;
    private final short ySuperscriptYOffset;
    private final short yStrikeoutSize;
    private final short yStrikeoutPosition;
    private final short sFamilyClass;
    private final Panose panose;
    private final int ulUnicodeRange1;
    private final int ulUnicodeRange2;
    private final int ulUnicodeRange3;
    private final int ulUnicodeRange4;
    private final int achVendorID;
    private final short fsSelection;
    private final int usFirstCharIndex;
    private final int usLastCharIndex;
    private final short sTypoAscender;
    private final short sTypoDescender;
    private final short sTypoLineGap;
    private final int usWinAscent;
    private final int usWinDescent;
    private final int ulCodePageRange1;
    private final int ulCodePageRange2;

    protected Os2Table(DirectoryEntry de,RandomAccessFileEmulator raf) throws IOException {
        raf.seek(de.getOffset());
        version = raf.readUnsignedShort();
        xAvgCharWidth = raf.readShort();
        usWeightClass = raf.readUnsignedShort();
        usWidthClass = raf.readUnsignedShort();
        fsType = raf.readShort();
        ySubscriptXSize = raf.readShort();
        ySubscriptYSize = raf.readShort();
        ySubscriptXOffset = raf.readShort();
        ySubscriptYOffset = raf.readShort();
        ySuperscriptXSize = raf.readShort();
        ySuperscriptYSize = raf.readShort();
        ySuperscriptXOffset = raf.readShort();
        ySuperscriptYOffset = raf.readShort();
        yStrikeoutSize = raf.readShort();
        yStrikeoutPosition = raf.readShort();
        sFamilyClass = raf.readShort();
        byte[] buf = new byte[10];
        raf.read(buf);
        panose = new Panose(buf);
        ulUnicodeRange1 = raf.readInt();
        ulUnicodeRange2 = raf.readInt();
        ulUnicodeRange3 = raf.readInt();
        ulUnicodeRange4 = raf.readInt();
        achVendorID = raf.readInt();
        fsSelection = raf.readShort();
        usFirstCharIndex = raf.readUnsignedShort();
        usLastCharIndex = raf.readUnsignedShort();
        sTypoAscender = raf.readShort();
        sTypoDescender = raf.readShort();
        sTypoLineGap = raf.readShort();
        usWinAscent = raf.readUnsignedShort();
        usWinDescent = raf.readUnsignedShort();
        ulCodePageRange1 = raf.readInt();
        ulCodePageRange2 = raf.readInt();
    }

    public int getVersion() {
        return version;
    }

    public short getAvgCharWidth() {
        return xAvgCharWidth;
    }

    public int getWeightClass() {
        return usWeightClass;
    }

    public int getWidthClass() {
        return usWidthClass;
    }

    public short getLicenseType() {
        return fsType;
    }

    public short getSubscriptXSize() {
        return ySubscriptXSize;
    }

    public short getSubscriptYSize() {
        return ySubscriptYSize;
    }

    public short getSubscriptXOffset() {
        return ySubscriptXOffset;
    }

    public short getSubscriptYOffset() {
        return ySubscriptYOffset;
    }

    public short getSuperscriptXSize() {
        return ySuperscriptXSize;
    }

    public short getSuperscriptYSize() {
        return ySuperscriptYSize;
    }

    public short getSuperscriptXOffset() {
        return ySuperscriptXOffset;
    }

    public short getSuperscriptYOffset() {
        return ySuperscriptYOffset;
    }

    public short getStrikeoutSize() {
        return yStrikeoutSize;
    }

    public short getStrikeoutPosition() {
        return yStrikeoutPosition;
    }

    public short getFamilyClass() {
        return sFamilyClass;
    }

    public Panose getPanose() {
        return panose;
    }

    public int getUnicodeRange1() {
        return ulUnicodeRange1;
    }

    public int getUnicodeRange2() {
        return ulUnicodeRange2;
    }

    public int getUnicodeRange3() {
        return ulUnicodeRange3;
    }

    public int getUnicodeRange4() {
        return ulUnicodeRange4;
    }

    public int getVendorID() {
        return achVendorID;
    }

    public short getSelection() {
        return fsSelection;
    }

    public int getFirstCharIndex() {
        return usFirstCharIndex;
    }

    public int getLastCharIndex() {
        return usLastCharIndex;
    }

    public short getTypoAscender() {
        return sTypoAscender;
    }

    public short getTypoDescender() {
        return sTypoDescender;
    }

    public short getTypoLineGap() {
        return sTypoLineGap;
    }

    public int getWinAscent() {
        return usWinAscent;
    }

    public int getWinDescent() {
        return usWinDescent;
    }

    public int getCodePageRange1() {
        return ulCodePageRange1;
    }

    public int getCodePageRange2() {
        return ulCodePageRange2;
    }

    @Override
    public int getType() {
        return OS_2;
    }
}
