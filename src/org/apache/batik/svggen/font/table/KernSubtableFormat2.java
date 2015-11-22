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
 * @version $Id: KernSubtableFormat2.java,v 1.3 2004/08/18 07:15:21 vhardy Exp $
 */
public class KernSubtableFormat2 extends KernSubtable {

    private final int rowWidth;
    private final int leftClassTable;
    private final int rightClassTable;
    private final int array;

    /** Creates new KernSubtableFormat2
     * @param raf
     * @throws java.io.IOException
     */
    protected KernSubtableFormat2(RandomAccessFileEmulator raf) throws IOException {
        rowWidth = raf.readUnsignedShort();
        leftClassTable = raf.readUnsignedShort();
        rightClassTable = raf.readUnsignedShort();
        array = raf.readUnsignedShort();
    }

    @Override
    public int getKerningPairCount() {
        return 0;
    }

    @Override
    public KerningPair getKerningPair(int i) {
        return null;
    }

}
