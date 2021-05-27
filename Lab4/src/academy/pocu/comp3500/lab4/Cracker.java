package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public final class Cracker {
    private User[] userTable;
    private RainbowTableSet rainbowTableSet;

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        String myHash = null;

        for (int i = 0; i < userTable.length; ++i) {
            if (userTable[i].getEmail() == email) {
                myHash = userTable[i].getPasswordHash();
                break;
            }
        }

        if (myHash.length() == 9) {
            rainbowTableSet = RainbowTableSet.CRC32;
        } else {
            var hastUTF8text = myHash.getBytes(StandardCharsets.UTF_8);
            Base64.Decoder decoder = Base64.getDecoder();
            var decodedBytes = decoder.decode(hastUTF8text);
            switch (decodedBytes.length) {
                case 16:
                    rainbowTableSet = RainbowTableSet.MD;
                    break;
                case 20:
                    rainbowTableSet = RainbowTableSet.SHA1;
                    break;
                case 32:
                    rainbowTableSet = RainbowTableSet.SHA256;
                    break;
            }
        }
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        String[] result = new String[userTable.length];

        if (rainbowTables == null)
            return result;

        for (int i = 0; i < userTable.length; ++i) {
            switch (rainbowTableSet) {
                case CRC32:
                    if (rainbowTables[0] != null) {
                        if (rainbowTables[0].contains(userTable[i].getPasswordHash()))
                            result[i] = rainbowTables[0].get(userTable[i].getPasswordHash());
                    }
                    break;
                case MD:
                    if (rainbowTables[1] != null) {
                        if (rainbowTables[1].contains(userTable[i].getPasswordHash()))
                            result[i] = rainbowTables[1].get(userTable[i].getPasswordHash());
                    }
                    if (rainbowTables[2] != null) {
                        if (rainbowTables[2].contains(userTable[i].getPasswordHash()))
                            result[i] = rainbowTables[2].get(userTable[i].getPasswordHash());
                    }
                    break;
                case SHA1:
                    if (rainbowTables[3] != null) {
                        if (rainbowTables[3].contains(userTable[i].getPasswordHash()))
                            result[i] = rainbowTables[3].get(userTable[i].getPasswordHash());
                    }
                    break;
                case SHA256:
                    if (rainbowTables[4] != null) {
                        if (rainbowTables[4].contains(userTable[i].getPasswordHash()))
                            result[i] = rainbowTables[4].get(userTable[i].getPasswordHash());
                    }
                    break;
            }
        }

        return result;
    }

    public enum RainbowTableSet {
        CRC32,
        MD,
        SHA1,
        SHA256
    }
}