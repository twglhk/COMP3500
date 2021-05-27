package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.CRC32;

public final class Cracker {
    private User[] userTable;
    private RainbowTableSet rainbowTableSet;

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        String myHash = null;
        var passwordBytes = password.getBytes(StandardCharsets.UTF_8);

        for (int i = 0; i < userTable.length; ++i) {
            if (userTable[i].getEmail() == email) {
                myHash = userTable[i].getPasswordHash();
                break;
            }
        }

        // CRC32
        CRC32 crc32 = new CRC32();
        crc32.update(passwordBytes);
        var crcHash = crc32.getValue();
        if (myHash.equals(String.valueOf(crcHash))) {
            rainbowTableSet = RainbowTableSet.CRC32;
            return;
        }

        // MD2
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            md.update(passwordBytes);
            var byteData = md.digest();
            var encodedData = Base64.getEncoder().encodeToString(byteData);
            if (myHash.equals(encodedData)) {
                rainbowTableSet = RainbowTableSet.MD2;
                return;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // MD5
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordBytes);
            var byteData = md.digest();
            var encodedData = Base64.getEncoder().encodeToString(byteData);
            if (myHash.equals(encodedData)) {
                rainbowTableSet = RainbowTableSet.MD5;
                return;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // SHA1
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(passwordBytes);
            var byteData = md.digest();
            var encodedData = Base64.getEncoder().encodeToString(byteData);
            if (myHash.equals(encodedData)) {
                rainbowTableSet = RainbowTableSet.SHA1;
                return;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // SHA256
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(passwordBytes);
            var byteData = md.digest();
            var encodedData = Base64.getEncoder().encodeToString(byteData);
            if (myHash.equals(encodedData)) {
                rainbowTableSet = RainbowTableSet.SHA256;
                return;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    public String[] run(final RainbowTable[] rainbowTables) {
        String[] result = new String[userTable.length];

        if (rainbowTables == null)
            return result;

        switch (rainbowTableSet) {
            case CRC32:
                if (rainbowTables[0] != null) {
                    setStringFromRainbowTable(result, rainbowTables[0]);
                }
                break;
            case MD2:
                if (rainbowTables[1] != null) {
                    setStringFromRainbowTable(result, rainbowTables[1]);
                }
            case MD5:
                if (rainbowTables[2] != null) {
                    setStringFromRainbowTable(result, rainbowTables[2]);
                }
                break;
            case SHA1:
                if (rainbowTables[3] != null) {
                    setStringFromRainbowTable(result, rainbowTables[3]);
                }
                break;
            case SHA256:
                if (rainbowTables[4] != null) {
                    setStringFromRainbowTable(result, rainbowTables[4]);
                }
                break;
        }

        return result;
    }

    private void setStringFromRainbowTable(final String[] result, final RainbowTable rainbowTable) {
        for (int i = 0; i < userTable.length; ++i) {
            if (rainbowTable.contains(userTable[i].getPasswordHash()))
                result[i] = rainbowTable.get(userTable[i].getPasswordHash());
        }
    }

    public enum RainbowTableSet {
        CRC32,
        MD2,
        MD5,
        SHA1,
        SHA256
    }
}