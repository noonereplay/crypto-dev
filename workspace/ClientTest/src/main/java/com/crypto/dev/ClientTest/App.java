package com.crypto.dev.ClientTest;

import com.crypto.dev.bithumb.ClientBitHumb;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ClientBitHumb bithumb = new ClientBitHumb();
        try {
			bithumb.currency("DASH");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
