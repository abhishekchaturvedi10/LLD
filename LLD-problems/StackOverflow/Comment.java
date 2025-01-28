package StackOverflow;

import StackOverflow.Vote.DownVote;
import StackOverflow.Vote.Upvote;
import StackOverflow.Vote.Vote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {
    private String commentID;
    private String commentText;
    private User user;
    private Date creationTime;
    private List<Upvote> upVotes;
    private List<DownVote> downVotes;

    public Comment(String commentID, String commentText, User user, Date creationTime) {
        this.commentID = commentID;
        this.commentText = commentText;
        this.user = user;
        this.creationTime = creationTime;
        this.upVotes = new ArrayList<>();
        this.downVotes = new ArrayList<>();
    }
}
