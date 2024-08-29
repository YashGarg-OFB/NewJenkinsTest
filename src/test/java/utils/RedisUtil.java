package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RedisUtil {

    public static String getKey(String configName) throws Exception {
        System.out.println("config name is "+configName);
        Host.ServerConfig config = Host.getServerConfig(configName);

        if (config == null) {
            throw new IllegalArgumentException("Invalid config passed for fetching key");
        }

        //get DEVELOPER KEY command
        String key = executeRemoteCommand(config.getServer(), config.getPort(),
            "/data/redis/install/redis-cli get DEVELOPER_KEY_VALUE");

        if (key == null || key.trim().isEmpty()) {
            throw new Exception("Could not fetch a valid key");
        }

        return key.trim();
    }

    private static String executeRemoteCommand(String host, int port, String command) {
        String result = null;

        String sshCommand = String.format("ssh -p %d root@%s '%s'", port, host, command);
        ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", sshCommand);

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            StringBuilder outputBuffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputBuffer.append(line).append("\n");
            }

            StringBuilder errorBuffer = new StringBuilder();
            while ((line = errorReader.readLine()) != null) {
                errorBuffer.append(line).append("\n");
            }

            result = outputBuffer.toString();

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Error executing remote command: " + errorBuffer.toString());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}