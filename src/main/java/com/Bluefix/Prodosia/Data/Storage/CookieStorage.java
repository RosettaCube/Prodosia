/*
 * Copyright (c) 2018 RoseLaLuna
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.Bluefix.Prodosia.Data.Storage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage class for an imgur account.
 */
public class CookieStorage implements ICookieStorage
{
    private String _cookieName;
    private static final String fileExtension = ".txt";
    private static final String tokenName = "REFRESH";


    public CookieStorage(String cookieName)
    {
        _cookieName = cookieName;
    }


    /**
     * Cookie storage uses the directory that the user runs the application from.
     * @return The full path to the cookie file.
     */
    private String getFilePath()
    {
        return System.getProperty("user.dir") + "/" + _cookieName + fileExtension;
    }


    /**
     * Retrieves the refresh-token if it exists.
     * @return The refresh-token if it exists, or null otherwise
     */
    public String getRefreshToken()
    {
        ArrayList<DataStorage.Item> items = DataStorage.readItems(getFilePath());

        // if the file didn't exist, return null
        if (items == null)
            return null;

        for (DataStorage.Item i : items)
        {
            if (tokenName.equals(i.getName()))
                return i.getData();
        }

        // if we could not find the refresh token, return null;
        return null;
    }


    /**
     * Set the refresh token.
     */
    public void setRefreshToken(String token) throws IOException
    {
        // if the refresh token was null, delete the cookie file.
        if (token == null)
        {
            DataStorage.deleteItem(getFilePath());
            return;
        }

        ArrayList<DataStorage.Item> items = new ArrayList<>();
        items.add(new DataStorage.Item(tokenName, token));

        DataStorage.storeItems(getFilePath(), items, storageMessage());
    }

    private static String storageMessage()
    {
        return
                "This document contains a cookie for the imgur API.\n" +
                "With a proper functioning cookie you won't need to log in again each time\n" +
                "you open the application.\n" +
                "if you are having issues with the API, this cookie can be safely deleted.\n" +
                "After deleting the cookie, the application should be restarted. Login will be prompted again.\n\n";
    }



}















