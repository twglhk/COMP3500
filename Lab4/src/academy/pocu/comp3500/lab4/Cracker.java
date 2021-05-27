package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import java.util.zip.CRC32;
import java.util.zip.CRC32C;

public final class Cracker {
    private User[] userTable;
    private String email;
    private String password;

    public Cracker(User[] userTable, String email, String password) {
        this.userTable = userTable;
        this.email = email;
        this.password = password;
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        String[] result = new String[userTable.length];

        // CRC32
        {
            for(int i = 0; i < userTable.length; ++i) {
                if (rainbowTables[0].contains(userTable[i].getPasswordHash()))
                    result[i] = rainbowTables[0].get(userTable[i].getPasswordHash());
            }
        }

        // MD2
        {
            for(int i = 0; i < userTable.length; ++i) {
                if (rainbowTables[1].contains(userTable[i].getPasswordHash()))
                    result[i] = rainbowTables[1].get(userTable[i].getPasswordHash());
            }
        }

        // MD5
        {
            for(int i = 0; i < userTable.length; ++i) {
                if (rainbowTables[2].contains(userTable[i].getPasswordHash()))
                    result[i] = rainbowTables[2].get(userTable[i].getPasswordHash());
            }
        }

        // SHA1
        {
            for(int i = 0; i < userTable.length; ++i) {
                if (rainbowTables[3].contains(userTable[i].getPasswordHash()))
                    result[i] = rainbowTables[3].get(userTable[i].getPasswordHash());
            }
        }

        // SHA256
        {
            for(int i = 0; i < userTable.length; ++i) {
                if (rainbowTables[4].contains(userTable[i].getPasswordHash()))
                    result[i] = rainbowTables[4].get(userTable[i].getPasswordHash());
            }
        }

        return result;
    }
}