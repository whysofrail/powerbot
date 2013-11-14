package com.nkn;

/**
 * @Author : NKN
 */
public class Globals {
    public static volatile String status; //Volatile because I plan to add an antiban class that operates on a separate thread.  I can remove it for now if you'd like.
    public static int clayMined;
}
