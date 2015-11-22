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
 * @version $Id: LookupList.java,v 1.3 2004/08/18 07:15:21 vhardy Exp $
 */
public class LookupList {

    private final int lookupCount;
    private final int[] lookupOffsets;
    private final Lookup[] lookups;

    /**
     * Creates new LookupList
     *
     * @param raf
     * @param offset
     * @param factory
     * @throws java.io.IOException
     */
    public LookupList(RandomAccessFileEmulator raf, int offset, LookupSubtableFactory factory)
        throws IOException {
        raf.seek(offset);
        lookupCount = raf.readUnsignedShort();
        lookupOffsets = new int[lookupCount];
        lookups = new Lookup[lookupCount];
        for (int i = 0; i < lookupCount; i++) {
            lookupOffsets[i] = raf.readUnsignedShort();
        }
        for (int i = 0; i < lookupCount; i++) {
            lookups[i] = new Lookup(factory, raf, offset + lookupOffsets[i]);
        }
    }

    public Lookup getLookup(Feature feature, int index) {
        if (feature == null) {
            System.out.println("Feature is null!! Index is " + index + "!!");
            return null;
        }
        if (feature.getLookupCount() > index) {
            int i = feature.getLookupListIndex(index);
            return lookups[i];
        }
        return null;
    }

}
