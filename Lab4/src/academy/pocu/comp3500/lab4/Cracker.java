package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.dsig.DigestMethod;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameterGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.CRC32;
import java.lang.String;
import java.lang.Object;

public final class Cracker {
    private User[] userTable;
    private String password;
    private String email;
    private MessageDigest md;

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        this.email = email;
        this.password = password;

        try {
            if (md == null)
                md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        String[] result = new String[userTable.length];

        if (rainbowTables == null)
            return result;

        String myHash = null;
        var passwordBytes = password.getBytes(StandardCharsets.UTF_8);

        for (int i = 0; i < userTable.length; ++i) {
            if (userTable[i].getEmail().equals(email)) {
                myHash = userTable[i].getPasswordHash();
                break;
            }
        }

        long startTime = System.nanoTime();
        long estimatedTime;

        // SHA256
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //System.out.println(System.nanoTime() - startTime);
            md.update(passwordBytes);
            var byteData = md.digest();
            var encodedData = Base64.getEncoder().encodeToString(byteData);
            if (myHash.equals(encodedData)) {
                if (rainbowTables[4] != null) {
                    setStringFromRainbowTable(result, rainbowTables[4]);
                    return result;
                }
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
                if (rainbowTables[3] != null) {
                    setStringFromRainbowTable(result, rainbowTables[3]);
                    return result;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // MD5
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(passwordBytes);
            var byteData = md.digest();
            var encodedData = Base64.getEncoder().encodeToString(byteData);
            if (myHash.equals(encodedData)) {
                if (rainbowTables[2] != null) {
                    setStringFromRainbowTable(result, rainbowTables[2]);
                    return result;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // MD2
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            md.update(passwordBytes);
            var byteData = md.digest();
            var encodedData = Base64.getEncoder().encodeToString(byteData);
            if (myHash.equals(encodedData)) {
                if (rainbowTables[1] != null) {
                    setStringFromRainbowTable(result, rainbowTables[1]);
                    return result;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        {
            // CRC32
            CRC32 crc32 = new CRC32();
            crc32.update(passwordBytes);
            var crcHash = crc32.getValue();
            if (myHash.equals(String.valueOf(crcHash))) {
                if (rainbowTables[0] != null) {
                    setStringFromRainbowTable(result, rainbowTables[0]);
                    return result;
                }
            }
        }

        return result;
    }

    private void setStringFromRainbowTable(final String[] result, final RainbowTable rainbowTable) {
        for (int i = 0; i < userTable.length; ++i) {
            var userPasswordHash = userTable[i].getPasswordHash();
            if (rainbowTable.contains(userPasswordHash))
                result[i] = rainbowTable.get(userPasswordHash);
        }
    }
}