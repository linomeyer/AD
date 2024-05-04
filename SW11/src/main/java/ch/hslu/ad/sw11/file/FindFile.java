/*
 * Copyright 2024 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.ad.sw11.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

/**
 * Codevorlage für eine Dateisuche.
 */
public final class FindFile {

    private static final Logger LOG = LoggerFactory.getLogger(FindFile.class);

    /**
     * Sucht ein File in einem Verzeichnis.
     *
     * @param name Name des Files.
     * @param dir  Verzeichnis.
     * @return
     */
    public static void findFile(final String name, final File dir) {
        final File[] list = dir.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isDirectory()) {
                    findFile(name, file);
                } else if (file.getName().contains(name)) {
                    LOG.debug("Found file: {}", file.getAbsolutePath());
                }
            }
        }
    }

    public static String findFileParallel(final String name, final File dir) {
        try (final ForkJoinPool pool = new ForkJoinPool()) {
            FindFileTask findFileTask = new FindFileTask(name, dir);
            return pool.invoke(findFileTask);
        }
    }
}
