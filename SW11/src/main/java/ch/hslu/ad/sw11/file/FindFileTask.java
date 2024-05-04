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

import java.io.File;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Codevorlage zu CountedCompleter für eine Dateisuche.
 */
@SuppressWarnings("serial")
public final class FindFileTask extends CountedCompleter<String> {

    private final String fileName;
    private final File dir;
    private final AtomicReference<String> result;

    /**
     * Erzeugt eine File-Such-Aufgabe.
     *
     * @param fileName Ausdruck der den Filenamen enthält.
     * @param dir      Verzeichnis in dem das File gesucht wird.
     */
    public FindFileTask(String fileName, File dir) {
        this(null, fileName, dir, new AtomicReference<>(null));
    }

    private FindFileTask(final CountedCompleter<?> parent, final String fileName, final File dir,
                         final AtomicReference<String> result) {
        super(parent);
        this.fileName = fileName;
        this.dir = dir;
        this.result = result;
    }

    @Override
    public void compute() {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    new FindFileTask(this, fileName, f, result).fork();
                } else {
                    if (f.getName().contains(fileName) && result.compareAndSet(null, f.getAbsolutePath())) {
                        quietlyCompleteRoot();
                    }
                    break;
                }
            }
        }
        tryComplete();
    }

    @Override
    public String getRawResult() {
        if (result != null) {
            return result.get();
        }
        return null;
    }
}
