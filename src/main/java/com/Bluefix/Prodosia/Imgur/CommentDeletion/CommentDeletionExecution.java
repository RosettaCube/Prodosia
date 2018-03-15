/*
 * Copyright (c) 2018 J.S. Boellaard
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

package com.Bluefix.Prodosia.Imgur.CommentDeletion;

import com.Bluefix.Prodosia.Imgur.ImgurApi.ApiDistribution;
import com.Bluefix.Prodosia.Module.ImgurIntervalRunner;

/**
 * Module class for deleting the items provided in `CommentDeletionStorage`
 */
public class CommentDeletionExecution extends ImgurIntervalRunner
{
    //region Singleton and Constructor

    private static CommentDeletionExecution me;

    /**
     * Retrieve the Comment Deletion Module object.
     * @return The Comment Deletion Module object.
     */
    public static CommentDeletionExecution handler()
    {
        if (me == null)
            me = new CommentDeletionExecution();

        return me;
    }

    /**
     * Create a new Module object for the Comment Deletion funtionality.
     */
    private CommentDeletionExecution()
    {
        super(ApiDistribution.DeletionModule);
    }

    //endregion








    @Override
    public void run()
    {

    }

    /**
     * Indicate the maximum amount of GET requests expected to be used during the next cycle.
     *
     * @return The maximum amount of GET requests.
     */
    @Override
    protected int projectedRequests()
    {
        return 0;
    }
}
