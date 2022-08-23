package com.twilio.pwned;

import com.github.nbaars.pwnedpasswords4j.client.PwnedPasswordClient;
import com.github.nbaars.pwnedpasswords4j.client.PwnedPasswordChecker;
import okhttp3.OkHttpClient;

public class Login
{
    public static void main( String[] args )
    {
        PwnedPasswordClient client = new PwnedPasswordClient(new OkHttpClient(), "https://api.pwnedpasswords.com/range", "");
        PwnedPasswordChecker checker = new PwnedPasswordChecker(client);
        boolean result = checker.check("password1");

        System.out.println( result );

        System.exit(0);
    }
}