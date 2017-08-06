package io.twasyl.jstackfx.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PidFactory {

    public static List<String> listPids() {
        try {
            Process jps = new ProcessBuilder("jps").redirectErrorStream(true).start();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(jps.getInputStream()))) {
                String line = null;
                List<String> pids = new ArrayList<>();
                while ((line = in.readLine()) != null) {
                    if (!line.matches("^[0-9]+\\s+Jps\\s*.*$")) {
                        pids.add(line);
                    }
                }
                return pids;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
