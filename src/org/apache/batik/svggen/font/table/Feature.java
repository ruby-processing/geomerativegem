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
 * @version $Id: Feature.java,v 1.3 2004/08/18 07:15:20 vhardy Exp $
 */
public class Feature {

    private final int featureParams;
    private final int lookupCount;
    private final int[] lookupListIndex;

    /** Creates new Feature
     * @param raf
     * @param offset
     * @throws java.io.IOException */
    protected Feature(RandomAccessFileEmulator raf, int offset) throws IOException {
        raf.seek(offset);
        featureParams = raf.readUnsignedShort();
        lookupCount = raf.readUnsignedShort();
        lookupListIndex = new int[lookupCount];
        for (int i = 0; i < lookupCount; i++) {
            lookupListIndex[i] = raf.readUnsignedShort();
        }
    }

    public int getLookupCount() {
        return lookupCount;
    }

    public int getLookupListIndex(int i) {
        return lookupListIndex[i];
    }

}
