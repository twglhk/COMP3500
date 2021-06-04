package academy.pocu.comp3500.lab5;

import javax.crypto.Cipher;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;

public class Bank {
    private HashMap<byte[], Long> userAccount;

    public Bank(byte[][] pubKeys, final long[] amounts) {
        userAccount = new HashMap();

        for (int i = 0; i < pubKeys.length; ++i) {
            userAccount.put(pubKeys[i], amounts[i]);
        }
    }

    public long getBalance(final byte[] pubKey) {
        return userAccount.get(pubKey);
    }

    public boolean transfer(final byte[] from, byte[] to, final long amount, final byte[] signature) {
        if (!userAccount.containsKey(from) || !userAccount.containsKey(to))
            return false;

        try {
            // signature 복호화 진행 (공개키 사용)
            Cipher cipher = Cipher.getInstance("RSA");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(from);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] extractedFromToAmount = cipher.update(signature);   // SHA-256 해시 추출
            System.out.println("Extracted : " + extractedFromToAmount);
            System.out.println("Extracted encoded : " + Base64.getEncoder().encodeToString(extractedFromToAmount));

            // [from, to, amount] 해시값 변환
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            var fromToAmount = new byte[from.length + to.length + Long.BYTES];
            var amountBytes =  longToBytes(amount);

            System.arraycopy(from, 0, fromToAmount, 0, from.length);
            System.arraycopy(to, 0, fromToAmount, from.length, to.length);
            System.arraycopy(amountBytes, 0, fromToAmount, from.length + to.length, Long.BYTES);
            md.update(fromToAmount);
            var receivedFromToAmount = md.digest(); // SHA-256 해시 추출
            System.out.println("Received encoded : " + receivedFromToAmount);
            System.out.println("Received encoded : " + Base64.getEncoder().encodeToString(receivedFromToAmount));

            // 두 해시 값이 일치하는지 비교
            if (extractedFromToAmount.equals(receivedFromToAmount)) {
                var fromBalance = getBalance(from);
                var toBalance = getBalance(to);

                // 잔액이 부족하다면 거래 X
                if (fromBalance - amount < 0)
                    return false;

                // 거래 진행
                userAccount.put(from, fromBalance - amount);
                userAccount.put(to, toBalance + amount);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    public byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getLong();
    }
}