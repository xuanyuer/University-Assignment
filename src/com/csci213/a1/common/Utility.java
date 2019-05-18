package com.csci213.a1.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utility
{
    public static String getHash (String base)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance ("SHA-256");
            byte [] hash = digest.digest (base.getBytes (StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder ();

            for (byte b : hash)
            {
                String hex = Integer.toHexString (0xff & b);
                if (hex.length () == 1) hexString.append ('0');
                hexString.append (hex);
            }

            return hexString.toString ();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace ();
        }

        return "";
    }
}
