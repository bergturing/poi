/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package org.apache.poi.poifs.filesystem;

import org.apache.poi.poifs.nio.FileBackedDataSource;
import org.apache.poi.util.Beta;
import org.apache.poi.util.TempFile;

import java.io.File;
import java.io.IOException;

/**
 * An experimental POIFSFileSystem to support the encryption of large files
 *
 * @since 4.1.1
 */
@Beta
public class TempFilePOIFSFileSystem extends POIFSFileSystem {
    File tempFile;

    protected void createNewDataSource() {
        try {
            tempFile = TempFile.createTempFile("poifs", ".tmp");
            _data = new FileBackedDataSource(tempFile, false);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create data source", e);
        }
    }

    public void close() throws IOException {
        if (tempFile != null) tempFile.delete();
        super.close();
    }

}
