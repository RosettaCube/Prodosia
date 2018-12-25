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

package com.Bluefix.Prodosia.Data.DataType.Comments;

import com.Bluefix.Prodosia.Business.Imgur.ImgurApi.IImgurManager;
import com.Bluefix.Prodosia.Data.DataHandler.LocalStorageHandler;
import com.github.kskelm.baringo.model.Comment;
import com.github.kskelm.baringo.util.BaringoApiException;
import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;

public class SimpleCommentRequest implements ICommentRequest
{
    private IImgurManager _imgurManager;
    private LocalStorageHandler<SimpleCommentRequest> _simpleCommentRequestStorage;

    private long id;
    private String imgurId;
    private Comment parent;
    private long parentId;
    private LinkedList<String> comments;
    private String dbString;

    /**
     * Create a simple comment request providing both the parent id and imgur Id
     *
     * @param imgurId  The imgur id of the post
     * @param parentId The parent id of the comment to reply to.
     * @param comments The comments to be posted.
     */
    private SimpleCommentRequest(@NotNull IImgurManager imgurManager,
                                 @NotNull LocalStorageHandler<SimpleCommentRequest> simpleCommentRequestStorage,
                                 long id,
                                 String imgurId,
                                 long parentId,
                                 LinkedList<String> comments)
    {
        // check if the request is valid.
        if ((imgurId == null || imgurId.trim().isEmpty()) && parentId < 0)
            throw new IllegalArgumentException("Must provide either the imgur id or the parent id");

        // store the dependencies
        _imgurManager = imgurManager;
        _simpleCommentRequestStorage = simpleCommentRequestStorage;

        this.imgurId = imgurId;
        this.parentId = parentId;
        this.comments = comments;
        this.id = id;
    }

    /**
     * Create a simple comment request providing both the parent id and imgur Id
     *
     * @param imgurId  The imgur id of the post
     * @param parentId The parent id of the comment to reply to.
     * @param comments The comments to be posted.
     */
    public SimpleCommentRequest(@NotNull IImgurManager imgurManager,
                                @NotNull LocalStorageHandler<SimpleCommentRequest> simpleCommentRequestStorage,
                                String imgurId,
                                long parentId,
                                LinkedList<String> comments)
    {
        // check if the request is valid.
        if ((imgurId == null || imgurId.trim().isEmpty()) && parentId < 0)
            throw new IllegalArgumentException("Must provide either the imgur id or the parent id");

        // store the dependencies
        _imgurManager = imgurManager;
        _simpleCommentRequestStorage = simpleCommentRequestStorage;

        this.imgurId = imgurId;
        this.parentId = parentId;
        this.comments = comments;
        this.id = -1;
    }

    public SimpleCommentRequest(@NotNull IImgurManager imgurManager,
                                @NotNull LocalStorageHandler<SimpleCommentRequest> simpleCommentRequestStorage,
                                String imgurId,
                                LinkedList<String> comments)
    {
        if (imgurId == null || imgurId.trim().isEmpty())
            throw new IllegalArgumentException("Must provide a valid imgur id");

        // store the dependencies
        _imgurManager = imgurManager;
        _simpleCommentRequestStorage = simpleCommentRequestStorage;

        this.imgurId = imgurId;
        this.comments = comments;
        this.id = -1;
    }

    public SimpleCommentRequest(@NotNull IImgurManager imgurManager,
                                @NotNull LocalStorageHandler<SimpleCommentRequest> simpleCommentRequestStorage,
                                long parentId,
                                LinkedList<String> comments)
    {
        if (parentId < 0)
            throw new IllegalArgumentException("Must provide a valid parent id");

        // store the dependencies
        _imgurManager = imgurManager;
        _simpleCommentRequestStorage = simpleCommentRequestStorage;

        this.imgurId = "";
        this.parentId = parentId;
        this.comments = comments;
        this.id = -1;
    }

    public SimpleCommentRequest(@NotNull IImgurManager imgurManager,
                                @NotNull LocalStorageHandler<SimpleCommentRequest> simpleCommentRequestStorage,
                                String imgurId,
                                String... comments)
    {
        if (imgurId == null || imgurId.trim().isEmpty())
            throw new IllegalArgumentException("Must provide a valid imgur id");

        // store the dependencies
        _imgurManager = imgurManager;
        _simpleCommentRequestStorage = simpleCommentRequestStorage;

        this.imgurId = imgurId;
        this.comments = new LinkedList<>();

        for (String c : comments)
        {
            this.comments.add(c);
        }

        this.id = -1;
    }

    public SimpleCommentRequest(@NotNull IImgurManager imgurManager,
                                @NotNull LocalStorageHandler<SimpleCommentRequest> simpleCommentRequestStorage,
                                long parentId,
                                String... comments)
    {
        if (parentId < 0)
            throw new IllegalArgumentException("Must provide a valid parent id");

        // store the dependencies
        _imgurManager = imgurManager;
        _simpleCommentRequestStorage = simpleCommentRequestStorage;

        this.imgurId = "";
        this.parentId = parentId;
        this.comments = new LinkedList<>();

        for (String c : comments)
        {
            this.comments.add(c);
        }

        this.id = -1;
    }

    public static SimpleCommentRequest parse(@NotNull IImgurManager imgurManager,
                                             @NotNull LocalStorageHandler<SimpleCommentRequest> simpleCommentRequestStorage,
                                             long id,
                                             String imgurId,
                                             long parentId,
                                             String data)
    {
        // split the data lines.
        String[] split = data.split(" ; ");

        LinkedList<String> items = new LinkedList<>();

        for (String s : split)
        {
            items.add(s.replace(";;", ";"));
        }

        return new SimpleCommentRequest(imgurManager, simpleCommentRequestStorage, id, imgurId, parentId, items);
    }

    /**
     * Retrieve the imgur-id that should be replied to.
     *
     * @return
     */
    @Override
    public String getImgurId()
    {
        return this.imgurId;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Retrieve the parent-id that should be replied to. Return -1 to indicate there is no existing
     * parent comment.
     *
     * @return
     */
    @Override
    public Comment getParent() throws BaringoApiException, IOException, URISyntaxException
    {
        if (this.parentId < 0)
            return null;

        if (this.parent == null)
        {
            this.parent = _imgurManager.getClient().commentService().getComment(this.parentId);
        }

        return this.parent;
    }

    public long getParentId()
    {
        if (this.parent != null)
            return this.parent.getId();

        return this.parentId;
    }

    /**
     * Retrieve all comments that should be executed by this tag request.
     *
     * @return
     */
    @Override
    public LinkedList<String> getComments()
    {
        return comments;
    }

    /**
     * Indicate whether the entry deep-equals the other request.
     *
     * @param cq
     * @return
     */
    @Override
    public boolean deepEquals(ICommentRequest cq)
    {
        return this.equals(cq);
    }

    /**
     * Remove the item from the storage.
     */
    @Override
    public void complete()
    {
        try
        {
            _simpleCommentRequestStorage.remove(this);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (BaringoApiException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleCommentRequest that = (SimpleCommentRequest) o;
        return parentId == that.parentId &&
                Objects.equals(imgurId, that.imgurId) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(imgurId, parentId, comments);
    }

    /**
     * Give a compact single-line string for storage in the database.
     * Use `SimpleCommentRequest.parse` to revert this.
     *
     * @return
     */
    public String dbParseComments()
    {
        // if there were no comments, return empty.
        if (this.comments == null || this.comments.isEmpty())
            return "";

        if (dbString == null)
        {

            StringBuilder output = new StringBuilder();

            for (String s : this.comments)
            {
                output.append(s.replace(";", ";;") + " ; ");
            }

            // complete the last separator
            if (this.comments.size() > 0)
                output.setLength(output.length() - 3);

            dbString = output.toString();
        }

        return dbString;
    }
}